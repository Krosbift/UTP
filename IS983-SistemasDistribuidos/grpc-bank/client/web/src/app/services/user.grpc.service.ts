import { Injectable } from '@angular/core';
import { UserServiceClient } from '../grpc/BankServiceClientPb';
import { CreateUserRequest, CreateUserResponse, GetUserRequest, GetUserResponse, ListUsersRequest, ListUsersResponse, User } from '../grpc/bank_pb';

@Injectable({ providedIn: 'root' })
export class UserService {
  private readonly client: UserServiceClient = new UserServiceClient('http://localhost:8081', null, null);

  public createUser(email: string, password: string, name: string): Promise<User | null> {
    const req = new CreateUserRequest();
    req.setEmail(email);
    req.setPassword(password);
    req.setName(name);

    return new Promise((resolve, reject) => {
      this.client.createUser(req, {}, (err, resp: CreateUserResponse) => {
        if (err) return reject(err);
        const user = resp.getUser();
        const setUser = user ? user : null;
        resolve(setUser);
      });
    })
  }

  public listUsers(): Promise<User[]> {
    const req = new ListUsersRequest();

    return new Promise((resolve, reject) => {
      this.client.listUsers(req, {}, (err, resp: ListUsersResponse) => {
        if (err) return reject(err);
        const users = resp.getUsersList();
        const setUsers = users.length > 0 ? users : [];
        resolve(setUsers);
      });
    })
  }

  public getUser(userId: number): Promise<User | null> {
    const req = new GetUserRequest();
    req.setId(userId);

    return new Promise((resolve, reject) => {
      this.client.getUser(req, {}, (err, resp: GetUserResponse) => {
        if (err) return reject(err);
        const user = resp.getUser();
        const setUser = user ? user : null;
        resolve(setUser);
      })
    })
  }
}
