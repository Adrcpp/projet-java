import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { AuthService } from './AuthService';
import { Observable, pipe, throwError } from 'rxjs';
import { map, catchError } from 'rxjs/operators';


import { AppSettings } from '../appSettings';


@Injectable()
export class ApiService {

    constructor(private _http: HttpClient, private oauthService: AuthService) {}

    private extractData(res: Response) {
        let body = res;
        return body || { };
      }

    post(url: string, data: any): Observable<any> {
        return this._http.post(AppSettings.API_ENDPOINT + url, this.getOptions(), data)           
        .pipe(
            map(this.extractData)
        );
    }

    get(url : string): Observable<any> {
        return this._http.get(AppSettings.API_ENDPOINT + url, this.getOptions())
        .pipe(
            map(this.extractData),
            catchError(this.handleError)

        );
    }

    isAdmin(): Observable<boolean> {
              return this._http.get(AppSettings.API_ENDPOINT + 'app/api/users', this.getOptions())
        .pipe(
            map(data => { return true; }),
            catchError(err => { return false; })
        );
    }

    private getOptions() {
        var headers = new HttpHeaders({
            'Content-type': 'application/json; charset=utf-8',
            'Authorization': 'Bearer ' + this.oauthService.getAccessToken()
        });

        var options = { headers: headers };
        return options;
    }

    private handleError(error: HttpErrorResponse) {
        if (error.status == 401) {
            return throwError('Unauthorize');
        }
      };
}