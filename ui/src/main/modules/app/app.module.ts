import { LayoutModule }            from '@angular/cdk/layout';
import { NgModule }                from '@angular/core';
import { FlexLayoutModule }        from '@angular/flex-layout';
import {
  FormsModule,
  ReactiveFormsModule
} from '@angular/forms';
import { MatButtonModule }         from '@angular/material/button';
import { MatCardModule }           from '@angular/material/card';
import { MatFormFieldModule }      from '@angular/material/form-field';
import { MatGridListModule }       from '@angular/material/grid-list';
import { MatIconModule }           from '@angular/material/icon';
import { MatInputModule }          from '@angular/material/input';
import { MatListModule }           from '@angular/material/list';
import { MatMenuModule }           from '@angular/material/menu';
import { MatSidenavModule }        from '@angular/material/sidenav';
import { MatToolbarModule }        from '@angular/material/toolbar';
import { BrowserModule }           from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule }            from '@angular/router';
import { Error4xxComponent }       from '@modules/errors/error-4xx.component';
import { ErrorsModule }            from '@modules/errors/module';
import { HeroDetailComponent }     from '@modules/heros/hero-detail.component';

import { HerosComponent }      from '@modules/heros/heros.component';
import { MessagesComponent }   from '@modules/heros/messages.component';
import { NavigationComponent } from '@modules/layout/navigation.component';
import { ToolBarComponent }    from '@modules/layout/tool-bar.component';
import { SignInComponent }     from '@modules/system/auth/sign-in.component';
import { AccountsComponent }   from '@modules/users/accounts/accounts.component';
import { RolesComponent }      from '@modules/users/roles/roles.component';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent }     from './app.component';

@NgModule({
  declarations: [
    AppComponent,
    ToolBarComponent,
    SignInComponent,
    RolesComponent,
    AccountsComponent,
    NavigationComponent,
    HerosComponent,
    HeroDetailComponent,
    MessagesComponent
  ],
  imports: [
    AppRoutingModule,
    BrowserModule,
    FormsModule,
    ErrorsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    LayoutModule,
    FlexLayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    MatGridListModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatMenuModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
