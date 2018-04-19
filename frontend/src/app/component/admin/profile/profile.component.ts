import { Component, OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import { User, UserService } from '../../../service/user.service';
import { STATUS_NO_CONTENT } from '../../../util';
import { SessionService } from '../../../service/session.service';

@Component({
  selector: 'app-profile',
  templateUrl: 'profile.component.html'
})

export class ProfileComponent implements OnInit{

  user: User;
    
  constructor(private userService: UserService, private sessionService: SessionService, private activatedRoute: ActivatedRoute) {
  }

    ngOnInit() {
        this.user = this.userService.getUserCompleted();
        
        /*this.userService.getUser(this.sessionService.user.id).subscribe(
            user => this.user = user,
            error => console.log(error)
        );*/
    }

  }
