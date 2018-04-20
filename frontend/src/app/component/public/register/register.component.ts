import { Component } from '@angular/core';
import { Router ,ActivatedRoute} from '@angular/router';
import { SessionService } from '../../../service/session.service';

@Component({
  selector:'app-register',
  templateUrl: 'register.component.html'
})

export class RegisterComponent {

  constructor(private sessionService: SessionService, private activatedRoute: ActivatedRoute,private router: Router) {
  }

  register(name:string, passwordHash: string, dni: string, email: string, telephone: number, address:string) {
    this.sessionService.register(name, passwordHash, dni, email, telephone, address).subscribe(
      user => { this.router.navigate(['home']); },
      error => console.log("Fail trying to register new account.")
    );
  }
}
