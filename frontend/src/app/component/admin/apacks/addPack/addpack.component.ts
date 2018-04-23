import { Component } from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import { Pack, PackService } from '../../../../service/pack.service';
import { Product, ProductService } from '../../../../service/product.service';
import {Subject} from 'rxjs/Subject';
import {debounceTime} from 'rxjs/operator/debounceTime';
import {NgbAlertConfig} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-addpack',
  templateUrl: 'addpack.component.html',
  providers: [NgbAlertConfig]
})

export class AdminAddPackComponent {
  //alerts
  private _success = new Subject<string>();
  staticAlertClosed = false;
  successMessage: string;
 
  packs: Pack[];
  newPack : Boolean;
  pack: Pack;
  products: Product[];
  newProduct: boolean;
  
  constructor(private servicePack: PackService, private serviceProduct: ProductService, activatedRoute: ActivatedRoute, private router: Router) {
    const id = activatedRoute.snapshot.params['id'];
    this.newProduct = true;
    if (id) {
      servicePack.getPack(id).subscribe(
        pack => this.pack = pack,
        error => console.error(error)
      );
      this.newPack = false;
    } else {
      this.pack = { name: '', price: 0 };
      this.newPack = true;
    }
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
      this.serviceProduct.getProducts().subscribe(
      products => this.products = products,
      error => console.log(error)
    );
  }
  
  addPack1() {
      this.servicePack.createPack1(this.pack).subscribe(
          product => {  this.router.navigate(['/admin/packs']);},
          error => console.log(error)
      );
  }

  addPack2(name:string,price:number,p1:number,p2:number,p3:number) {
      this.servicePack.createPack2(name,price,p1,p2,p3).subscribe(
          product => { this._success.next(`${new Date()} - 'Pack added successfully.'`);},
          error => console.log(error)
      );
  }

  cancel() {
      window.history.back();
  }
}