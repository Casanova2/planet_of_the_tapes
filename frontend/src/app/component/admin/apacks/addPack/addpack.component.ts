import { Component } from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import { Pack, PackService } from '../../../../service/pack.service';
import { Product, ProductService } from '../../../../service/product.service';

@Component({
  selector: 'app-addpack',
  templateUrl: 'addpack.component.html'
})

export class AdminAddPackComponent {

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
          product => {  this.router.navigate(['/admin/packs']);},
          error => console.log(error)
      );
  }

  cancel() {
      window.history.back();
  }
}