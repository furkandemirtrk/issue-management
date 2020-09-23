import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app.routing.module';
import {AppComponent} from './app.component';
import {AppLayoutModule} from "./_layout/app-layout/app-layout.module";
import {ApiService} from "./services/api.service";
import {HttpClientModule} from "@angular/common/http";
import {ButtonModule} from "primeng/button";
import {TableModule} from "primeng/table";
import {SidebarModule} from "./_layout/sidebar/sidebar.module";
import {HeaderModule} from "./_layout/header/header.module";
import {RouterModule} from "@angular/router";
import {FooterModule} from "./_layout/footer/footer.module";
import {MobileHeaderModule} from "./_layout/mobile-header/mobile-header.module";
import {NgxDatatableModule} from "@swimlane/ngx-datatable";

@NgModule({
    declarations: [
        AppComponent,
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        AppLayoutModule,
        HttpClientModule,
        MobileHeaderModule,
        SidebarModule,
        HeaderModule,
        RouterModule,
        FooterModule,
        NgxDatatableModule
    ],
    providers: [ApiService],
    bootstrap: [AppComponent]
})
export class AppModule {
}
