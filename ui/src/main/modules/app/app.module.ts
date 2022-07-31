import { NgModule }         from '@angular/core';
import { BrowserModule }    from '@angular/platform-browser';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule }  from '@angular/material/button';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule }    from '@angular/material/icon';
import { MatListModule }    from '@angular/material/list';
import { LayoutModule }     from '@angular/cdk/layout';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatGridListModule }       from '@angular/material/grid-list';
import { MatCardModule }           from '@angular/material/card';
import { MatMenuModule }           from '@angular/material/menu';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule }        from '@modules/app/app-routing.module';
import { AppComponent }            from '@modules/app/app.component';
import { LoginComponent }          from '@modules/system/auth/login.component';
import { RolesComponent }          from '@modules/users/roles/roles.component';
import { AccountsComponent }       from '@modules/users/accounts/accounts.component';
import { NavigationComponent }     from '@modules/layout/navigation.component';

import { HerosComponent }          from '@modules/heros/heros.component';
import { HeroDetailComponent } from '@modules/heros/hero-detail.component';
import { MessagesComponent } from '@modules/heros/messages.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RolesComponent,
    AccountsComponent,
    NavigationComponent,
    HerosComponent,
    HeroDetailComponent,
    MessagesComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    LayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    MatGridListModule,
    MatCardModule,
    MatMenuModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
