import { Component, OnInit, OnDestroy, NgZone, signal, WritableSignal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BankService } from '../../services/bank.grpc.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './dashboard.html',
  styleUrls: ['./dashboard.scss'],
})
export class Dashboard implements OnInit, OnDestroy {
  bankName = 'Mi Banco';
  public totalBalance: WritableSignal<number> = signal(0);

  private totalSub?: Subscription;

  constructor(
    private bankService: BankService,
    private zone: NgZone,
  ) {}

  async ngOnInit() {
    try {
  // fetch current total
  const initial = await this.bankService.getBankTotal();
  this.totalBalance.set(initial);

      // subscribe to updates (ensure UI updates happen inside Angular zone)
      this.totalSub = this.bankService.streamBankTotal().subscribe({
        next: (val: number) => this.zone.run(() => this.totalBalance.set(val)),
        error: (err) => console.error('bank total stream error', err),
      });
    } catch (e) {
      console.error('dashboard init error', e);
    }
  }

  ngOnDestroy() {
    this.totalSub?.unsubscribe();
  }

  formatCurrency(amount: number) {
    return new Intl.NumberFormat('es-ES', { style: 'currency', currency: 'EUR' }).format(amount);
  }
}
