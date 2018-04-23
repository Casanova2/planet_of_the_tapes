import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { DomSanitizer } from '@angular/platform-browser';
import { Pack, PackService } from '../../../../service/pack.service';
import { Product, ProductService } from '../../../../service/product.service';
import {Subject} from 'rxjs/Subject';
import {debounceTime} from 'rxjs/operator/debounceTime';
import {NgbAlertConfig} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-adminEditPack',
  templateUrl: './edit.component.html',
   providers: [NgbAlertConfig]
})
export class AdminEditPackComponent {
  //alerts
  private _success = new Subject<string>();
  staticAlertClosed = false;
  successMessage: string;

  products: Product[];
  pack: Pack;

  constructor(private servicePack: PackService, private serviceProduct: ProductService, private router: Router,
    private activatedRoute: ActivatedRoute, private service: ProductService) {
  }

  ngOnInit() {
    //alerts
    setTimeout(() => this.staticAlertClosed = true, 9000);
    this._success.subscribe((message) => this.successMessage = message);
    debounceTime.call(this._success, 9000).subscribe(() => this.successMessage = null);
  //--------// 

    const id = this.activatedRoute.snapshot.params['id'];
      this.servicePack.getPack(id).subscribe(
      pack => this.pack = pack,
      error => console.log(error)
    );
      this.serviceProduct.getProducts().subscribe(
      products => this.products = products,
      error => console.log(error)
    );
  }

  updatePack(name:string,price:number,p1:number,p2:number,p3:number) {
    this.servicePack.updatePack(this.pack.id,name,price,p1,p2,p3).subscribe(
        product => { this._success.next(`${new Date()} - 'Pack edited successfully.'`);},
        error => console.log(error)
    );
  }

}
