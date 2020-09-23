import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {AdminComponent} from './admin.component';
import {DashboardComponent} from "./dashboard/dashboard.component";
import {IssueComponent} from "./issue/issue.component";
import {ProjectComponent} from "./project/project.component";
import {IssueService} from "../services/shared/issue.service";
import {ProjectService} from "../services/shared/project.service";
import {NgxDatatableModule} from "@swimlane/ngx-datatable";


@NgModule({
    declarations: [AdminComponent, DashboardComponent, IssueComponent, ProjectComponent],
    imports: [
        CommonModule,
        NgxDatatableModule
    ],
    providers: [IssueService, ProjectService]
})
export class AdminModule {
}
