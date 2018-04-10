import {Component} from '@angular/core';
import {Router} from '@angular/router';
import {LoginService} from '../../service/login.service';

import {User} from '../../model/user.model';

@Component({
  // tslint:disable-next-line:component-selector
  selector: 'login',
  templateUrl: 'login.component.html'
})
export class LoginComponent {

  constructor(public loginService: LoginService) { }

  logIn(event: any, user: string, pass: string) {

    event.preventDefault();

    this.loginService.logIn(user, pass).subscribe(
      u => console.log(u),
      error => alert('Invalid user or password')
    );
  }

  logOut() {
    this.loginService.logOut().subscribe(
      response => { },
      error => console.log('Error when trying to log out: ' + error)
    );
  }

}
