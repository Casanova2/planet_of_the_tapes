import { Component } from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import { User, UserService } from '../../../../service/user.service';

@Component({
  selector: 'app-adduser',
  templateUrl: 'adduser.component.html'
})

export class AddUserComponent {

  private user: User;
  
  constructor(private service: UserService, activatedRoute: ActivatedRoute, private router: Router) {}

  
    addUser(name:string, password:string, dni:string, email:string, telephone: number, address:string) {    
    
        this.service.createUser(name, password, dni, email, telephone, address).subscribe(
            user => {  this.router.navigate(['/admin/users']);},
            error => console.log(error)
        );
    }

    cancel() {
        window.history.back();
    }
  }