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
import { HeroDetailComponent }     from '@pages/heros/hero-detail.component';

import { HerosComponent }      from '@pages/heros/heros.component';
import { MessagesComponent }   from '@pages/heros/messages.component';
import { NavigationComponent } from '@pages/layout/navigation.component';
import { ToolBarComponent }    from '@pages/layout/tool-bar.component';
import { SignInComponent }     from '@pages/system/auth/sign-in.component';
import { AccountsComponent }   from '@pages/users/accounts/accounts.component';
import { RolesComponent }      from '@pages/users/roles/roles.component';

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
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
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
