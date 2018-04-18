import {Component} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';

@Component({
  templateUrl: 'about.component.html'
})

export class AboutComponent {
  lat: number = 40.3359104;
  lng: number = -3.8790321;

  constructor(private router: Router, activatedRoute: ActivatedRoute) { }

}
