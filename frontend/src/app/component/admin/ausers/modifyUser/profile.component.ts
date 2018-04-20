import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import { ActivatedRoute} from '@angular/router';
import {User, UserService } from '../../../../service/user.service';


@Component({
  selector: 'app-myProfile',
  templateUrl: 'profile.component.html'
})
export class ProfileComponent implements OnInit {

  user:User;

  constructor(private router: Router,
  private uservice: UserService, private activatedRoute: ActivatedRoute) { 

  }
	ngOnInit() {
  this.uservice.getUser().subscribe(
    user => { 
     this.user = user;
   },
   error => console.log(error)
 );

}

editProfile() {    
  this.uservice.updateUser(this.user).subscribe(
        user => { this.router.navigate(['/admin/dashboard']);},
        error => console.log(error)
    );
}


}