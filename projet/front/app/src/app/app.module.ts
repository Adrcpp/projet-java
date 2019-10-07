import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { MapComponent } from './map/map.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { OAuthModule } from 'angular-oauth2-oidc';
import { AuthService } from './service/AuthService';
import { AdminComponent } from './admin/admin.component';
import { AdminDashboardComponent } from './admin/admin-dashboard/admin-dashboard.component';
import { ApiService } from './service/ApiService';
import { UserEditComponent } from './admin/user-edit/user-edit.componnent';


@NgModule({
    declarations: [
        AppComponent,
        LoginComponent,
        MapComponent,
        AdminComponent,
        AdminDashboardComponent,
        UserEditComponent
    ],
    imports: [
        HttpClientModule,
        OAuthModule.forRoot(),
        BrowserModule,
        AppRoutingModule,
        ReactiveFormsModule,
        FormsModule
    ],
    providers: [AuthService, ApiService],
    bootstrap: [AppComponent]
})

export class AppModule { }
