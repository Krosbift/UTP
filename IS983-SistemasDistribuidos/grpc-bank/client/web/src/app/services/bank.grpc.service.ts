import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BankServiceClient } from '../grpc/BankServiceClientPb';
import { BankTotal, BankTotalUpdate } from '../grpc/bank_pb';
import * as empty_pb from 'google-protobuf/google/protobuf/empty_pb';

@Injectable({ providedIn: 'root' })
export class BankService {
  private readonly client: BankServiceClient = new BankServiceClient('http://localhost:8081', null, null);

  public getBankTotal(): Promise<number> {
    return new Promise((resolve, reject) => {
      const req = new empty_pb.Empty();
      this.client.getBankTotal(req, {}, (err, resp: BankTotal) => {
        if (err) return reject(err);
        const banktotal = resp.getTotalAmount();
        resolve(banktotal ?? 0);
      });
    });
  }

  public streamBankTotal(): Observable<number> {
    return new Observable<number>((observer) => {
  const req = new empty_pb.Empty();
  const stream: any = this.client.streamBankTotal(req, {});

      stream.on('data', (resp: BankTotalUpdate) => {
        const total = resp.getTotalAmount();
        observer.next(total ?? 0);
      });

      stream.on('error', (err: any) => observer.error(err));
      stream.on('end', () => observer.complete());

      return () => {
        if (stream.cancel) stream.cancel();
      };
    });
  }
}
