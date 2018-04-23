import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { CanActivateChild } from '@angular/router';
import { SessionService } from '../../service/session.service';

@Injectable()
export class CanActivateViaAuthGuard implements CanActivateChild {

    constructor(private authService: SessionService, private router: Router) { }

    canActivateChild() {
        // If the user is not logged in we'll send them back to the home page
        if (!this.authService.isUserLogged()) {
            console.log('You are not logged');
            this.router.navigate(['/login']);
            return false;
        }

        return true;
    }
}