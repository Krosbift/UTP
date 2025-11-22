import { Injectable } from '@angular/core';
import { AccountServiceClient } from '../grpc/BankServiceClientPb';
import { Account, CreateAccountRequest, CreateAccountResponse, GetAccountByUserRequest, GetAccountByUserResponse, ListAccountsRequest, ListAccountsResponse } from '../grpc/bank_pb';

@Injectable({ providedIn: 'root' })
export class AccountService {
  private readonly client: AccountServiceClient = new AccountServiceClient('http://localhost:8081', null, null);

  public createAccount(userId: number): Promise<Account | null> {
    const req = new CreateAccountRequest();
    req.setUserId(userId);

    return new Promise((resolve, reject) => {
      this.client.createAccount(req, {}, (err, resp: CreateAccountResponse) => {
        if (err) return reject(err);
        const account = resp.getAccount();
        resolve(account ? account : null);
      });
    });
  }

  public listAccounts(): Promise<Account[]> {
    const req = new ListAccountsRequest();

    return new Promise((resolve, reject) => {
      this.client.listAccounts(req, {}, (err, resp: ListAccountsResponse) => {
        if (err) return reject(err);
        const accounts = resp.getAccountsList();
        resolve(accounts.length > 0 ? accounts : []);
      });
    });
  }

  public getAccountByUser(userId: number): Promise<Account | null> {
    const req = new GetAccountByUserRequest();
    req.setUserId(userId);

    return new Promise((resolve, reject) => {
      this.client.getAccountByUser(req, {}, (err, resp: GetAccountByUserResponse) => {
        if (err) return reject(err);
        const account = resp.getAccount();
        resolve(account ? account : null);
      });
    });
  }
}
