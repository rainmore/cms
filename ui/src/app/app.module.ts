import { BrowserModule }    from '@angular/platform-browser';
import { NgModule }         from '@angular/core';
import { AppRoutingModule } from '@app/app-routing.module';
import { AppComponent }     from '@app/app.component';
import { LoginComponent }   from '@app/modules/auth';

@NgModule({
    declarations: [
        AppComponent,
        LoginComponent
    ],
    imports: [
        BrowserModule,
        AppRoutingModule
    ],
    providers: [],
    bootstrap: [LoginComponent]
})
export class AppModule {
}
