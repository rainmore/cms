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
  rowHeightRatio = '2:1';

  form = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required])
  });

  constructor(private breakpointObserver: BreakpointObserver,
              private authenticationService: AuthService) {
  }

  ngOnInit(): void {
    this.breakpointObserver.observe([
      Breakpoints.Handset
    ]).subscribe(result => {
        if (result.matches) {
          this.rowHeightRatio = '0.8:1';
        }
      });
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
