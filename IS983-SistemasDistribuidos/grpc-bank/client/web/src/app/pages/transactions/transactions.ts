
import { Component, OnInit, OnDestroy, NgZone, signal, computed, WritableSignal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { TransactionService } from '../../services/transaction.grpc.service';
import { AccountService } from '../../services/account.grpc.service';
import { AuthService } from '../../services/auth.grpc.service';
import { Subscription, interval, from } from 'rxjs';
import { startWith, switchMap } from 'rxjs/operators';
import { UserService } from '../../services/user.grpc.service';

@Component({
  selector: 'app-transactions',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './transactions.html',
  styleUrls: ['./transactions.scss'],
})
export class Transactions implements OnInit, OnDestroy {
  // reactive signals for reliable template updates
  public transactions: WritableSignal<any[]> = signal<any[]>([]);
  public total = computed(() => this.transactions().reduce((acc, tx: any) => {
    const amt = (tx && typeof tx.getAmount === 'function') ? tx.getAmount() : (tx?.amount ?? 0);
    return acc + Number(amt || 0);
  }, 0));
  public loading: WritableSignal<boolean> = signal(true);
  public userName: WritableSignal<string | null> = signal(null);

  // form
  amountValue = 0;
  private txSub?: Subscription;
  public accountId: WritableSignal<number | null> = signal(null);

  constructor(
    private txService: TransactionService,
    private accountService: AccountService,
    private users: UserService,
    private zone: NgZone,
  ) {}

  async ngOnInit() {
    try {
      const userId = localStorage.getItem('id');
      const user = await this.users.getUser(Number(userId));
      if (user) {
        this.userName.set((user as any).getName ? (user as any).getName() : (user as any).name ?? null);
      }

      if (!Number(userId)) {
        this.loading.set(false);
        return;
      }

      const account = await this.accountService.getAccountByUser(Number(userId));
      if (!account) {
        this.loading.set(false);
        return;
      }

      // account proto may expose getId()
      let acctId = null;
      if (account) {
        acctId = account.getId();
      }
      if (acctId == null) {
        this.loading.set(false);
        return;
      }

      this.accountId.set(acctId);
      this.loading.set(false);
      // load current transactions and then poll periodically
      this.txSub = interval(5000).pipe(
        startWith(0),
        switchMap(() => from(this.txService.listTransactions(acctId)))
      ).subscribe({
        next: (txs: any[]) => {
          this.zone.run(() => {
            this.transactions.set(txs || []);
          });
        },
        error: (err) => console.error('tx poll error', err),
      });
    } catch (err) {
      console.error(err);
    } finally {
      this.loading.set(false);
    }
  }

  ngOnDestroy() {
    this.txSub?.unsubscribe();
  }

  recalculateTotal() {
    // total is a computed signal; return current value if caller needs it
    return this.total();
  }

  formatCurrency(amount: number) {
    return new Intl.NumberFormat('es-ES', { style: 'currency', currency: 'EUR' }).format(amount);
  }

  isDeposit(tx: any) {
    const amt = (tx.getAmount) ? tx.getAmount() : (tx.amount ?? 0);
    return Number(amt) > 0;
  }

  private async createTx(amount: number, type: number) {
    if (!this.accountId() && this.accountId() !== 0) return;
    try {
      // create on backend
      const acctId = this.accountId();
      const tx = await this.txService.createTransaction(acctId as number, amount, type);
        if (tx) {
          // if backend doesn't provide createdAt, set a client-side timestamp so list shows something
          try {
            const hasCreated = tx && typeof (tx as any).getCreatedAt === 'function' && (tx as any).getCreatedAt();
            if (!hasCreated) (tx as any)._clientCreatedAt = Date.now();
          } catch (e) {
            (tx as any)._clientCreatedAt = Date.now();
          }

          // push to top and rely on signals to update template
          this.zone.run(() => {
            this.transactions.update(curr => [tx, ...curr]);
          });
        }
    } catch (e) {
      console.error('create tx error', e);
    }
  }

  public async onDeposit() {
    if (!this.amountValue || this.amountValue <= 0) return;
    // transactionType: 0 = deposit (adjust if your proto uses different enum)
    // send positive amount and deposit type (0)
    await this.createTx(Math.abs(this.amountValue), 1);
    this.amountValue = 0;
  }

  public async onWithdrawal() {
    if (!this.amountValue || this.amountValue <= 0) return;
    // for withdrawal send negative amount or a withdrawal type; here send negative amount and type 1
    // send positive amount and withdraw type (1)
    await this.createTx(Math.abs(this.amountValue), 2);
    this.amountValue = 0;
  }
}
