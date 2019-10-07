import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService } from 'src/app/service/ApiService';

@Component({
    selector: 'app-admin-dashboard',
    templateUrl: './admin-dashboard.component.html',
    styleUrls: ['./admin-dashboard.component.css']
})
export class AdminDashboardComponent implements OnInit {

    private data;

    constructor(private apiService: ApiService, private router: Router) { }

    ngOnInit(): void {
        this.data = [];
        this.apiService.get("app/api/users").subscribe((data: {}) => {
            console.log(data);
            this.data = data;
        });
    }

    edit(id) {
        console.log("Edit clicked on " + id);
        this.router.navigate(['/admin/user', id]);
    }

    delete(id) {
        console.log("Delete clicked on " + id)
        // this.rest.deleteProduct(id)
        //   .subscribe(res => {
        //       this.getProducts();
        //     }, (err) => {
        //       console.log(err);
        //     }
        //   );
    }
}