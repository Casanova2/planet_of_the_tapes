import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import { ActivatedRoute} from '@angular/router';
import {User, UserService } from '../../../../service/user.service';
import {Subject} from 'rxjs/Subject';
import {debounceTime} from 'rxjs/operator/debounceTime';
import {NgbAlertConfig} from '@ng-bootstrap/ng-bootstrap';


@Component({
  selector: 'app-myProfile',
  templateUrl: 'profile.component.html',
  providers: [NgbAlertConfig]
})
export class ProfileComponent implements OnInit {
  //alerts
  private _success = new Subject<string>();
  staticAlertClosed = false;
  successMessage: string;

  user:User;

  constructor(private router: Router,
  private uservice: UserService, private activatedRoute: ActivatedRoute) { 

  }
	ngOnInit() {
  //alerts
   setTimeout(() => this.staticAlertClosed = true, 5000);
   this._success.subscribe((message) => this.successMessage = message);
   debounceTime.call(this._success, 5000).subscribe(() => this.successMessage = null);
  //--------//
  this.uservice.getUser().subscribe(
    user => { 
     this.user = user;
   },
   error => console.log(error)
 );

}

editProfile() {    
  this.uservice.updateUser(this.user).subscribe(
        user => {this._success.next(`${new Date()} - 'Your perfil has been updated successfully.'`);},
        error => console.log(error)
    );
}


}