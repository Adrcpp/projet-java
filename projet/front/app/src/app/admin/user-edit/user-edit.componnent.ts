import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators  } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';

import { ApiService } from '../../service/ApiService';

@Component({
  selector: 'user-edit',
  templateUrl: './user-edit.component.html'
})
export class UserEditComponent implements OnInit {

    private roles : [];
    private user  : {};

    userEdit = new FormGroup({
        username: new FormControl(''),
        roles: new FormControl(''),
    });

    constructor(private apiService: ApiService,  private router: ActivatedRoute) { }

    ngOnInit(): void {

        let id = this.router.snapshot.paramMap.get('id');

        this.apiService.get("app/api/roles").subscribe((data: []) => {
            console.log(data);
            this.roles = data;
            this.userEdit.patchValue({roles: data});
        });

        this.apiService.get("app/api/users/" + id ).subscribe((data: {}) => {
            console.log(data);
            this.user = data;
            this.userEdit.patchValue({username: data.username});
        });
    }

    onSubmit() {
        console.warn(this.userEdit.value);
        console.warn(this.userEdit.get('username').value);
        console.warn(this.userEdit.get('roles').value);

        this.user.username = this.userEdit.get('username').value;
        this.user.role = this.roles[this.userEdit.get('roles').value]

        console.warn(this.user);
    }
}