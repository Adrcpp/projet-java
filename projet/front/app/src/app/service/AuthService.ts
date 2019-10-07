import { Injectable } from '@angular/core';
import { OAuthService } from 'angular-oauth2-oidc';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable } from 'rxjs';

import { AppSettings } from '../appSettings';

@Injectable()
export class AuthService {

    constructor(private _http: HttpClient, private oauthService: OAuthService) {
        
        this.oauthService.useHttpBasicAuth = true;
        this.oauthService.requireHttps = false;
        this.oauthService.tokenEndpoint = AppSettings.API_ENDPOINT + 'app/oauth/token';
        this.oauthService.clientId = "testjwtclientid";
        this.oauthService.dummyClientSecret = "XY7kmzoNzl100";
        this.oauthService.scope = "read write";
    }

    obtainAccessToken(username: string, password: string) {

        this.oauthService.fetchTokenUsingPasswordFlow(username, password).then((resp) => {
        });
    }

    getAccessToken(){
        return this.oauthService.getAccessToken();
    }

    isLoggedIn() {
        if (this.oauthService.getAccessToken() === null) {
            return false;
        }
        return true;
    }

    logout() {
        this.oauthService.logOut();
        location.reload();
    }
}