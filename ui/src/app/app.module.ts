import { NgModule }         from '@angular/core';
import { BrowserModule }    from '@angular/platform-browser';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule }  from '@angular/material/button';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule }    from '@angular/material/icon';
import { MatListModule }    from '@angular/material/list';
import { LayoutModule }     from '@angular/cdk/layout';

import { AppRoutingModule }        from '@app/app-routing.module';
import { AppComponent }            from '@app/app.component';
import { LoginComponent }          from '@app/system/auth/login.component';
import { RolesComponent }          from '@app/modules/users/roles/roles.component';
import { AccountsComponent }       from '@app/modules/users/accounts/accounts.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NavComponent }            from '@app/layout/nav.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RolesComponent,
    AccountsComponent,
    NavComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    LayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
