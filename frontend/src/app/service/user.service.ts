import { Injectable } from '@angular/core';
import { Headers, Http, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';
// tslint:disable-next-line:import-blacklist
import 'rxjs/Rx';
import { USER_URL, BASE_URL } from '../util';

import { User } from '../model/user.model';
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
  avatar?:string;
}


@Injectable()
export class UserService {

  user: User;
  users: User[];
  authCreds: string;

  constructor(private http: Http) {
  }

  setAuthHeaders(authCreds: string) {
    this.authCreds = authCreds;
  }

  getUserCompleted() {
    return this.user;
  }

  getUsers() {
    return this.http.get(USER_URL, { withCredentials: true })
      .map(response => response.json())
      .catch(error => this.handleError(error));
  }

  getUser(id: number) {
    this.authCreds = localStorage.getItem('creds');
    const headers: Headers = new Headers();
    headers.append('Authorization', 'Basic ' + this.authCreds);
    return this.http.get(USER_URL + '/' + id.toString(), { headers: headers })
      .map(response => {
        this.user = response.json();
        return response.json();
      })
      .catch(error => Observable.throw('Server error'));
  }

  updateUser(user: User, current: boolean) {
    this.authCreds = localStorage.getItem('creds');
    const body = JSON.stringify(user);
    const headers: Headers = new Headers();
    headers.append('Content-Type', 'application/json');
    headers.append('X-Requested-With', 'XMLHttpRequest');
    headers.append('Authorization', 'Basic ' + this.authCreds);
    return this.http.put(USER_URL + '/' + user.id, body, { headers: headers })
      .map(response => {
        if (current) {
          this.getUser(user.id).subscribe(
            // tslint:disable-next-line:no-shadowed-variable
            user => this.user = user,
            error => error
          );
        }
        return response.json();
      })
      .catch(error => Observable.throw('Server error'));
  }

  updateFile(formData: FormData, user: User) {
    const headers: Headers = new Headers();
    headers.append('Accept', 'application/json');
    headers.append('Authorization', 'Basic ' + this.authCreds);
    return this.http.put(USER_URL + '/' + user.id + '/upload', formData, { headers: headers })
      .map(response => console.log('Success. The file has been successfully added to server directories.'))
      .catch(error => Observable.throw('Server error'));
  }

  deleteUser(id: number) {
    this.authCreds = localStorage.getItem('creds');
    const headers: Headers = new Headers();
    headers.append('Content-Type', 'application/json');
    headers.append('X-Requested-With', 'XMLHttpRequest');
    headers.append('Authorization', 'Basic ' + this.authCreds);
    return this.http.delete(USER_URL + '/' + id, { headers: headers })
      .map(response => response.json())
      .catch(error => Observable.throw('Server error'));
  }

  createUser(name:string, password:string, dni:string, email:string, telephone:string, address:string) {
    let newuser: User;
    newuser={name:name, passwordHash:password, dni:dni, email:email, telephone:telephone, address:address};
    const headers = new Headers({
      'Content-Type': 'application/json',
      'X-Requested-With': 'XMLHttpRequest'
     // 'Authorization': 'Basic ' + this.authCreds
    });
    const options = new RequestOptions({ withCredentials: true, headers });
      return this.http.post(BASE_URL + 'user', newuser, options)
        .map(response => response.json())
        .catch(error => this.handleError(error));
    }

  private handleError(error: any) {
    console.error(error);
    return Observable.throw('Server error (' + error.status + '): ' + error.text());
  }

}
