import {Injectable, OnDestroy} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {BASE_URL} from "../util";
import {User} from '../model/user.model';
import {UserService} from '../service/user.service';
import { Http, RequestOptions, Headers } from '@angular/http';
import 'rxjs/Rx';
import { POrder } from '../model/pOrder.model';

export interface User {
  id?: number;
  name: string;
  passwordHash?: string;
  dni: string;
  email: string;
  telephone: string;
  viewTelephone?: boolean;
  address?: string;
  roles?: string[];
  hasPhoto?: boolean;
  orders?: POrder[];
}

@Injectable()
export class SessionService implements OnDestroy {

  user: User;
  authCreds: string;
  isLogged = false;
  isAdmin = false;

  ngOnDestroy() {
    console.log('localStorage called from ngOnDestroy');
    localStorage.clear();
  }


  constructor(private http: Http, private userService: UserService ){
    this.reqIsLogged();
  }

reqIsLogged() {

    const headers = new Headers({
        'X-Requested-With': 'XMLHttpRequest'
    });

    const options = new RequestOptions({ withCredentials: true, headers });

    this.http.get(BASE_URL + 'logIn', options).subscribe(
        response => this.processLogInResponse(response),
        error => {
            if (error.status !== 401) {
                console.error('Error when asking if logged: ' +
                    JSON.stringify(error));
            }
        }
    );
}

private processLogInResponse(response) {
    this.isLogged = true;
    this.user = response.json();
    this.isAdmin = this.user.roles.indexOf('ROLE_ADMIN') !== -1;
}

isUserLogged(){
    return this.isLogged;
}
logIn(user: string, pass: string) {

    const userPass = user + ':' + pass;

    const headers = new Headers({
        'Authorization': 'Basic ' + this.utf8_to_b64(userPass),
        'X-Requested-With': 'XMLHttpRequest'
    });

    const options = new RequestOptions({ withCredentials: true, headers });

    return this.http.get(BASE_URL + 'logIn', options).map(
        response => {
            this.processLogInResponse(response);
            this.isLogged = true;
            return this.user;
        }
    );
}

logOut() {

    return this.http.get(BASE_URL + 'logOut', { withCredentials: true }).map(
        response => {
            this.isLogged = false;
            this.isAdmin = false;
            return response;
        }
    );
}

utf8_to_b64(str) {
return btoa(encodeURIComponent(str).replace(/%([0-9A-F]{2})/g, function (match, p1) {
    return String.fromCharCode(<any>'0x' + p1);
}));
}

getUser(){
    return this.user;
}


checkCredentials() {
    return (localStorage.getItem("user") !== null);
}

register(name:string, passwordHash: string, dni: string, email: string, telephone: number, address:string){
    let newUser: User;
    newUser = {name: name, passwordHash: passwordHash, dni: dni, email: email, telephone: telephone, address: address};
    return this.http.post(BASE_URL + 'register/add', newUser);
}

}
