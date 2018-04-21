import { Component, OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {Subject} from 'rxjs/Subject';
import {STATUS_NO_CONTENT} from '../../../util';
import { Pack, PackService } from '../../../service/pack.service';
import { Product, ProductService } from '../../../service/product.service';

@Component({
  selector: 'app-apacks',
  templateUrl: 'apacks.component.html'
})

export class AdminPacksComponent implements OnInit{

  private _success = new Subject<string>();
  packs: Pack[];
  message: String;

  constructor(private servicePack: PackService, private serviceProduct: ProductService, private activatedRoute: ActivatedRoute) {
  }

    ngOnInit() {
        this.servicePack.getPacks().subscribe(
        packs => this.packs = packs,
        error => console.log(error)
      );
    }

    deletePack(id: number) {
      this.servicePack.removePack(id).subscribe(
        response => {
          this.message = 'Product deleted successfully.';
          this.servicePack.getPacks().subscribe(
            packs => this.packs = packs,
            error => console.log(error)
          );
          this._success.next(`${new Date()} - Message successfully changed.`);
        },
        error => {
          this.message = 'Not found.'
        }
      );
    }
  }
  
