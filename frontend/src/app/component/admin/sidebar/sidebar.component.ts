import {Component, OnInit} from '@angular/core';
import {DomSanitizer} from '@angular/platform-browser';
import { SessionService } from '../../../service/session.service';
import {User} from '../../../model/user.model';

/*import {FileService} from '../../../service/file.service';*/
import {UserService} from '../../../service/user.service';

@Component({
  selector: 'app-admin-sidebar',
  templateUrl: 'sidebar.component.html'
})
export class AdminSidebarComponent implements OnInit {

  indexActive: boolean;
  resourcesActive: boolean;
  loansActive: boolean;
  finesActive: boolean;
  usersActive: boolean;
  user: User;
  userImage: any;
  url:any;
  

  constructor(private sanitizer: DomSanitizer, private userService: UserService,public service: SessionService) { }  
  ngOnInit() {
    this.update('index');
    this.user=this.service.getUser();
    /*this.fileService.getUserFile(Number(localStorage.getItem('id'))).subscribe(
      data => {
        let dataRecieved: string[] = data.split('"');
        this.userImage = 'data:image/png;base64,' + dataRecieved[3];
      },
      error => console.log("Fail trying to charge " + this.user.name + " image.")
    );*/
    url=> this.url= "../../../../assets/img/admin/avatar/asier.png";
  }

  update(page: string) {
    switch (page) {
      case 'index':
        this.indexActive = true;
        this.resourcesActive = false;
        this.loansActive = false;
        this.finesActive = false;
        this.usersActive = false;
        break;
      case 'resources':
        this.indexActive = false;
        this.resourcesActive = true;
        this.loansActive = false;
        this.finesActive = false;
        this.usersActive = false;
        break;
      case 'loans':
        this.indexActive = false;
        this.resourcesActive = false;
        this.loansActive = true;
        this.finesActive = false;
        this.usersActive = false;
        break;
      case 'fines':
        this.indexActive = false;
        this.resourcesActive = false;
        this.loansActive = false;
        this.finesActive = true;
        this.usersActive = false;
        break;
      case 'users':
        this.indexActive = false;
        this.resourcesActive = false;
        this.loansActive = false;
        this.finesActive = false;
        this.usersActive = true;
        break;
    }
  }
}
