import { Injectable }     from '@angular/core';
import { Authentication } from '@entities/system/auth/authentication';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private authentication?: Authentication;

  private validAuthentication = new Authentication('a@test.com', 'password');

  logout() {
    this.authentication = undefined;
  }

  authenticate(authentication: Authentication) {
    if (authentication.password === this.validAuthentication.password &&
      authentication.username === this.validAuthentication.username) {
      this.authentication = authentication;
    }
    else {
      this.logout()
    }
  }

  isAuthenticated(): boolean {
    return typeof this.authentication !== 'undefined';
  }

}
