import { Component, OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import { User, UserService } from '../../../service/user.service';
import {STATUS_NO_CONTENT} from '../../../util';

@Component({
  selector: 'app-adminusers',
  templateUrl: 'ausers.component.html'
})

export class AdminUsersComponent implements OnInit{

  users: User[];
  message: string;
  constructor(private service: UserService, private activatedRoute: ActivatedRoute) {
  }

    ngOnInit() {
        this.service.getUsers().subscribe(
        users => this.users = users,
        error => console.log(error)
      );
    }

    deleteUser(id: number) {
      this.service.removeUser(id).subscribe(
        response => {
          this.message = 'User deleted successfully.';
        },
        error => {
          this.message = 'Not found.'
        }
      );
    }  
  }
