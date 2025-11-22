import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TransactionServiceClient } from '../grpc/BankServiceClientPb';
import { AccountRequest, CreateTransactionRequest, CreateTransactionResponse, ListTransactionsRequest, ListTransactionsResponse, Transaction } from '../grpc/bank_pb';
import * as wrappers_pb from 'google-protobuf/google/protobuf/wrappers_pb';

@Injectable({ providedIn: 'root' })
export class TransactionService {
  private readonly client: TransactionServiceClient = new TransactionServiceClient('http://localhost:8081', null, null);

  public createTransaction(accountId: number, amount: number, transactionType: number): Promise<Transaction | null> {
    const req = new CreateTransactionRequest();
    req.setAccountId(accountId);
    req.setAmount(amount);
    req.setTransactionType(transactionType);

    return new Promise((resolve, reject) => {
      this.client.createTransaction(req, {}, (err, resp: CreateTransactionResponse) => {
        if (err) return reject(err);
        const transaction = resp.getTransaction();
        resolve(transaction ? transaction : null);
      });
    });
  }

  public listTransactions(accountId?: number): Promise<Transaction[]> {
    const req = new ListTransactionsRequest();

    if (accountId !== undefined) {
      const v = new (wrappers_pb as any).UInt64Value();
      (v as any).setValue(Number(accountId));
      req.setAccountId(v);
    }

    return new Promise((resolve, reject) => {
      this.client.listTransactions(req, {}, (err, resp: ListTransactionsResponse) => {
        if (err) return reject(err);
        const txs = resp.getTransactionsList();
        resolve(txs.length > 0 ? txs : []);
      });
    });
  }

  public streamAccountTransactions(accountId: number): Observable<Transaction> {
    return new Observable<Transaction>((observer) => {
      const req = new AccountRequest();
      req.setAccountId(accountId);

      const stream: any = this.client.streamAccountTransactions(req, {});

      stream.on('data', (resp: Transaction) => observer.next(resp));
      stream.on('error', (err: any) => observer.error(err));
      stream.on('end', () => observer.complete());

      return () => {
        if (stream.cancel) stream.cancel();
      };
    });
  }
}
