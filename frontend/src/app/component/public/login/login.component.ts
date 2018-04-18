import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { SessionService } from '../../../service/session.service';
import {UserService } from '../../../service/user.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent {

  constructor(public service: SessionService, private activatedRoute: ActivatedRoute) { }

  logIn(event: any, user: string, pass: string) {

    event.preventDefault();

    this.service.logIn(user, pass).subscribe(
      u => console.log(u),
      error => alert('Invalid user or password')
    );
    window.history.back();
  }

  logOut() {
    this.service.logOut().subscribe(
      response => { },
      error => console.log('Error when trying to log out: ' + error)
    );
  }

}
