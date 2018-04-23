import { Component } from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import { User, UserService } from '../../../../service/user.service';
import {Subject} from 'rxjs/Subject';
import {debounceTime} from 'rxjs/operator/debounceTime';
import {NgbAlertConfig} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-adduser',
  templateUrl: 'adduser.component.html',
  providers: [NgbAlertConfig]
})

export class AddUserComponent {
  //alerts
  private _success = new Subject<string>();
  staticAlertClosed = false;
  successMessage: string;

  private user: User;
  
  constructor(private service: UserService, activatedRoute: ActivatedRoute, private router: Router) {}
  ngOnInit():void {
    //alerts
      setTimeout(() => this.staticAlertClosed = true, 5000);
      this._success.subscribe((message) => this.successMessage = message);
      debounceTime.call(this._success, 5000).subscribe(() => this.successMessage = null);
    //--------//
  }
    addUser(name:string, password:string, dni:string, email:string, telephone: string, address:string) {    
    
        this.service.createUser(name, password, dni, email, telephone, address).subscribe(
            user => { this._success.next(`${new Date()} - 'User added successfully.'`);},
            error => console.log(error)
        );
    }

    cancel() {
        window.history.back();
    }
  }