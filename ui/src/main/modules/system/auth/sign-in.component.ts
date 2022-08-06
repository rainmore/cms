import {
  BreakpointObserver,
  Breakpoints
}                                from '@angular/cdk/layout';
import {
  Component,
  OnInit
}                                from '@angular/core';
import {
  FormControl,
  FormGroup,
  Validators
}                         from '@angular/forms';
import { AuthService }    from '@services/system/auth/auth.service';
import { Authentication } from '@entities/system/auth/authentication';

@Component({
  selector: 'app-auth-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.scss']
})
export class SignInComponent implements OnInit {
  hide = true;

  form = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required])
  });

  constructor(private breakpointObserver: BreakpointObserver,
              private authenticationService: AuthService) {
  }

  ngOnInit(): void {
  }

  handleLogin(): void {
    // this.authenticationService.authenticate(new Authentication(this.email.getRawValue().toString(), this.password.getRawValue().toString()))
  }

  getErrorMessage() {
    if (this.form.controls.email.hasError('required')) {
      return 'You must enter a value';
    }

    return this.form.controls.email.hasError('email') ? 'Not a valid email' : '';
  }

  submit(): void {
    console.log('11111');
  }
}
