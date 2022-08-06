import { NgModule }          from '@angular/core';
import {
  RouterModule,
  Routes
}                            from '@angular/router';
import { Error4xxComponent } from '@modules/errors/error-4xx.component';
import { HerosComponent }    from '@modules/heros/heros.component';
import { SignInComponent }   from '@modules/system/auth/sign-in.component';

const routes: Routes = [
  {
    path: '',
    component: HerosComponent,
  },
  {
    path: 'sign-in',
    component: SignInComponent,
  },
  {
    path: '**',
    component: Error4xxComponent,
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
