import { CommonModule }      from '@angular/common';
import { NgModule }          from '@angular/core';
import { Error4xxComponent } from '@modules/errors/error-4xx.component';


@NgModule({
  declarations: [
    Error4xxComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [
    Error4xxComponent,
  ],

})
export class ErrorsModule {
}
