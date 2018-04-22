import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import { ActivatedRoute} from '@angular/router';
import { Product, ProductService } from '../../../service/product.service';
import { User, UserService } from '../../../service/user.service';

import {SessionService} from '../../../service/session.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: 'dashboard.component.html'
})
export class AdminDashboardComponent implements OnInit {

  products: Product[];
  lenght:number;
  users:User[];
  user:User;

	constructor( public session: SessionService,private router: Router,private service: ProductService,private service1: UserService, private activatedRoute: ActivatedRoute) { 

  }
	ngOnInit() {
    this.service.getProducts().subscribe(
    products => this.products = products,
    error => console.log(error),
   );
   this.service1.getUsers().subscribe(
    users => this.users = users,
    error => console.log(error)
   );
 

}

}


