import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {AppLayoutComponent} from "./_layout/app-layout/app-layout.component";

const routes: Routes = [
    {
        path: '',
        component: AppLayoutComponent,
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
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}