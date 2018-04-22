import { Component, OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import { User, UserService } from '../../../service/user.service';
import {STATUS_NO_CONTENT} from '../../../util';
import {Subject} from 'rxjs/Subject';
import {debounceTime} from 'rxjs/operator/debounceTime';
import {NgbAlertConfig} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-adminusers',
  templateUrl: 'ausers.component.html',
  providers: [NgbAlertConfig]
})

export class AdminUsersComponent implements OnInit{
 //alerts
  private _success = new Subject<string>();
  staticAlertClosed = false;
  successMessage: string;

  users: User[];
  message: string;
  constructor(private service: UserService, private activatedRoute: ActivatedRoute) {
  }

    ngOnInit() {
       //alerts
       setTimeout(() => this.staticAlertClosed = true, 5000);
       this._success.subscribe((message) => this.successMessage = message);
       debounceTime.call(this._success, 5000).subscribe(() => this.successMessage = null);
    //--------//
        this.service.getUsers().subscribe(
        users => this.users = users,
        error => console.log(error)
      );
    }

    deleteUser(id: number) {
      this.service.removeUser(id).subscribe(
        response => {
          this._success.next(`${new Date()} - 'User deleted successfully.'`);
          this.service.getUsers().subscribe(
            users => this.users = users,
            error => console.log(error)
          );
        },
        error => {
          this.message = 'Not found.'
        }
      );
    }  
  }
