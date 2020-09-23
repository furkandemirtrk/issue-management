import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MobileHeaderComponent } from './mobile-header.component';



@NgModule({
    declarations: [MobileHeaderComponent],
    exports: [
        MobileHeaderComponent
    ],
    imports: [
        CommonModule
    ]
})
export class MobileHeaderModule { }
