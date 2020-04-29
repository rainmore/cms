import {NgModule}              from '@angular/core';
import {CommonModule}          from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { ListComponent }       from '@app/modules/users/roles/list.component';

@NgModule({
    declarations: [ListComponent],
    imports: [
        CommonModule,
        ReactiveFormsModule
    ],
})
export class RolesModule {
}
