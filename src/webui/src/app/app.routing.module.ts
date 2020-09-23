import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {DashboardComponent} from "./admin/dashboard/dashboard.component";
import {IssueComponent} from "./admin/issue/issue.component";
import {ProjectComponent} from "./admin/project/project.component";

const routes: Routes = [
    {
        path: '',
        component: DashboardComponent,
        data: {
            title: 'Home'
        },
        children: [
            {
                path: 'admin',
                loadChildren: () => import('./admin/admin.module').then(m => m.AdminModule)
            },
        ]
    },
    {
        path: 'dashboard',
        component: DashboardComponent,
        data: {
            title: 'Dashboard'
        },
        children: [
            {
                path: 'admin',
                loadChildren: () => import('./admin/admin.module').then(m => m.AdminModule)
            },
        ]
    },
    {
        path: 'issue',
        component: IssueComponent,
        data: {
            title: 'Issue'
        },
        children: [
            {
                path: 'admin',
                loadChildren: () => import('./admin/admin.module').then(m => m.AdminModule)
            },
        ]
    },
    {
        path: 'project',
        component: ProjectComponent,
        data: {
            title: 'Project'
        },
        children: [
            {
                path: 'admin',
                loadChildren: () => import('./admin/admin.module').then(m => m.AdminModule)
            },
        ]
    },
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}