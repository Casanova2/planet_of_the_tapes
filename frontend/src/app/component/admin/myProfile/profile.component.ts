import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import { ActivatedRoute} from '@angular/router';
import { Product, ProductService } from '../../../service/product.service';
import { User, UserService } from '../../../service/user.service';

import {SessionService} from '../../../service/session.service';

@Component({
  selector: 'app-myProfile',
  templateUrl: 'profile.component.html'
})
export class ProfileComponent implements OnInit {

  products: Product[];
  lenght:number;
  users:User[];
  user:User;

	constructor(private router: Router,private service: SessionService,private service1: UserService, private activatedRoute: ActivatedRoute) { 

  }
	ngOnInit() {
  this.service1.getUser1().subscribe(
    user => { 
     this.user = user;
   },
   error => console.log(error)
 );

}

updateUser() {    
  //let newproduct :Product;
 // newproduct={name:name,description:description,type:type,genre:genre,stock:stock,pbuy:pbuy,prent:prent,score:score,trailer:trailer,director:director,cast:cast,year:year};
    this.service1.updateUser(this.user).subscribe(
        product => { this.router.navigate(['/admin/dashboard']);},
        error => console.log(error)
    );
}


}