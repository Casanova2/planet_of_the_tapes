import {Component} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import { YoutubePlayerModule } from 'ng2-youtube-player';

@Component({
  templateUrl: 'about.component.html'
})

export class AboutComponent {
  lat: number = 40.3359104;
  lng: number = -3.8790321;
  //youtube
  player: YT.Player;
  private id: string = 'ZWJH7JQCjLM';
 
    savePlayer (player) {
    this.player = player;
    console.log('player instance', player)
    }
  onStateChange(event){
    console.log('player state', event.data);
  }

  constructor(private router: Router, activatedRoute: ActivatedRoute) { }

}
