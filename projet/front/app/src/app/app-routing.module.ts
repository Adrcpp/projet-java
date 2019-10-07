import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { AppComponent } from './app.component';
import { MapComponent } from './map/map.component';
import { AuthGuard } from './auth/auth.guard';
import { AdminComponent } from './admin/admin.component';
import { AdminDashboardComponent } from './admin//admin-dashboard/admin-dashboard.component';
import { UserEditComponent } from './admin/user-edit/user-edit.componnent';

const routes: Routes = [
    { path: '',             component: AppComponent },
    { path: 'login',        component: LoginComponent },
    { path: 'map',          component: MapComponent },
    
    {
        path: 'admin',
        component: AdminComponent,
        canActivate: [AuthGuard],
        children: [
            {
                path: '',
                children: [
                    { path: 'users',        component: AdminDashboardComponent },
                    { path: 'user/:id',     component: UserEditComponent },
                ],
            }
        ]
    }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})

export class AppRoutingModule { }
