import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app.routing.module';
import {AppComponent} from './app.component';
import {ApiService} from "./services/api.service";
import {HTTP_INTERCEPTORS, HttpClient, HttpClientModule} from "@angular/common/http";
import {SidebarModule} from "./_layout/sidebar/sidebar.module";
import {HeaderModule} from "./_layout/header/header.module";
import {RouterModule} from "@angular/router";
import {FooterModule} from "./_layout/footer/footer.module";
import {MobileHeaderModule} from "./_layout/mobile-header/mobile-header.module";
import {NgxDatatableModule} from "@swimlane/ngx-datatable";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {TranslateLoader, TranslateModule} from "@ngx-translate/core";
import {TranslateHttpLoader} from "@ngx-translate/http-loader";
import {AppLayoutComponent} from "./_layout/app-layout/app-layout.component";
import {ModalModule} from "ngx-bootstrap/modal";
import {ConfirmationComponent} from "./shared/confirmation/confirmation.component";
import {JwtInterceptor} from "./security/jwt.interceptor.";
import {AuthGuard} from "./security/auth-guard";
import {AuthenticationService} from "./security/authentication.service";
import {AuthenticationInterceptor} from "./security/authentication.interceptor";
import { LoginComponent } from './login/login.component';

export const createTranslateLoader = (http: HttpClient) => {
    return new TranslateHttpLoader(http, './assets/i18n/','.json');
}

const APP_CONTAINERS = [
    AppLayoutComponent
];

@NgModule({
    declarations: [
        AppComponent,
        ...APP_CONTAINERS,
        LoginComponent
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        MobileHeaderModule,
        SidebarModule,
        HeaderModule,
        RouterModule,
        FooterModule,
        NgxDatatableModule,
        FormsModule,
        ReactiveFormsModule,
        TranslateModule.forRoot({
            loader:{
                provide: TranslateLoader,
                useFactory: createTranslateLoader,
                deps: [HttpClient]
            }
        }),
        BrowserModule,
        ModalModule.forRoot()
    ],
    providers: [ApiService , AuthGuard, AuthenticationService,
        {provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true},
        {provide: HTTP_INTERCEPTORS, useClass: AuthenticationInterceptor, multi: true},],
    bootstrap: [AppComponent],
    entryComponents: [ConfirmationComponent]
})
export class AppModule {
}
