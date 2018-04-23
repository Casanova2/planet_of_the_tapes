import { Component, OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {Subject} from 'rxjs/Subject';
import {STATUS_NO_CONTENT} from '../../../util';
import { Pack, PackService } from '../../../service/pack.service';
import { Product, ProductService } from '../../../service/product.service';
import {debounceTime} from 'rxjs/operator/debounceTime';
import {NgbAlertConfig} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-apacks',
  templateUrl: 'apacks.component.html',
  providers: [NgbAlertConfig]
})

export class AdminPacksComponent implements OnInit{
    //alerts
  private _success = new Subject<string>();
  staticAlertClosed = false;
  successMessage: string;
  packs: Pack[];
  message: String;

  constructor(private servicePack: PackService, private serviceProduct: ProductService, private activatedRoute: ActivatedRoute) {
  }

    ngOnInit() {
      //alerts
       setTimeout(() => this.staticAlertClosed = true, 8000);
       this._success.subscribe((message) => this.successMessage = message);
       debounceTime.call(this._success, 8000).subscribe(() => this.successMessage = null);
    //--------//
        this.servicePack.getPacks().subscribe(
        packs => this.packs = packs,
        error => console.log(error)
      );
    }

    deletePack(id: number) {
      this.servicePack.removePack(id).subscribe(
        response => {
          this.servicePack.getPacks().subscribe(
            packs => this.packs = packs,
            error => console.log(error)
          );
          this._success.next(`${new Date()} -Pack removed succesfully`);
        },
        error => {
          this.message = 'Not found.'
        }
      );
    }
  }
  
