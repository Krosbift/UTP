import { Injectable, signal, Signal } from '@angular/core';
import { AuthServiceClient } from '../grpc/BankServiceClientPb';
import { LoginRequest, LoginResponse, User } from '../grpc/bank_pb';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private readonly client: AuthServiceClient = new AuthServiceClient('http://localhost:8081', null, null);
  protected currentUser = signal<User | null>(null);

  public login(email: string, password: string): Promise<User | null> {
    const req = new LoginRequest();
    req.setEmail(email);
    req.setPassword(password);

    return new Promise((resolve, reject) => {
      this.client.login(req, {}, (err, resp: LoginResponse) => {
        if (err) return reject(err);
        const user = resp.getUser();
        const setUser = user ? user : null;
        this.currentUser.set(setUser);
        if (setUser) localStorage.setItem('id', String(setUser.getId()))
        resolve(setUser);
      });
    });
  }

  public logout() {
    this.currentUser.set(null);
  }

  public isLoggedIn(): boolean {
    return !!this.currentUser();
  }

  public getUserId(): number | null {
    const user = this.currentUser();
    if (!user) return null;
    const id = user.getId();
    return id ?? null;
  }

  public getCurrentUserSignal(): Signal<User | null> {
    return this.currentUser;
  }
}
