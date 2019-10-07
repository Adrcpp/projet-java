import { Component } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { AuthService } from '../service/AuthService';

@Component({
    selector: 'app-reactive-favorite-color',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})

export class LoginComponent {

    constructor(private authService: AuthService) { }

    loginForm = new FormGroup({
        username: new FormControl(''),
        password: new FormControl(''),
    });

    onSubmit() {
        console.warn(this.loginForm.value);
        this.authService.obtainAccessToken(
            this.loginForm.get('username').value, 
            this.loginForm.get('password').value
        );
    }
}