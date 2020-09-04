import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AppLayoutComponent } from './app-layout.component';
import {MobileHeaderModule} from "../mobile-header/mobile-header.module";
import {SidebarModule} from "../sidebar/sidebar.module";
import {HeaderModule} from "../header/header.module";
import {RouterModule} from "@angular/router";
import {FooterModule} from "../footer/footer.module";



@NgModule({
    declarations: [AppLayoutComponent],
    exports: [
        AppLayoutComponent
    ],
    imports: [
        CommonModule,
        MobileHeaderModule,
        SidebarModule,
        HeaderModule,
        RouterModule,
        FooterModule
    ]
})
export class AppLayoutModule { }
