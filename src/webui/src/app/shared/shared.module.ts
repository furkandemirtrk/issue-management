import { NgModule } from '@angular/core';
import {TranslateModule} from "@ngx-translate/core";
import { ConfirmationComponent } from './confirmation/confirmation.component';
import {CommonModule} from "@angular/common";
import {BsModalRef, ModalModule} from "ngx-bootstrap/modal";
import {FormsModule} from "@angular/forms";



@NgModule({
    imports: [
        CommonModule,
        FormsModule
    ],
    providers: [BsModalRef],
    declarations: [ConfirmationComponent],
    entryComponents: [
    ],
    exports: [
        TranslateModule,
        ModalModule,
        ConfirmationComponent
    ]

})
export class SharedModule { }
