import {RouterModule, Routes} from "@angular/router";
import {NgModule} from "@angular/core";
import {DashboardComponent} from "./dashboard/dashboard.component";
import {IssueComponent} from "./issue/issue.component";
import {ProjectComponent} from "./project/project.component";

const routes: Routes = [
    {
        path: '',
        data: {
            title: 'Dashboard'
        },
        children: [
            {
                path: 'project',
                component: ProjectComponent,
                data: {
                    title: 'project'
                }
            },
            {
                path: 'dashboard',
                component: DashboardComponent,
                data: {
                    title: 'dashboard'
                }
            },
            {
                path: 'issue',
                component: IssueComponent,
                data: {
                    title: 'issue'
                }
            },
        ]
    },
    ]
@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class AdminRouting {
}
