import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { DomSanitizer } from '@angular/platform-browser';
import { Pack, PackService } from '../../../../service/pack.service';
import { Product, ProductService } from '../../../../service/product.service';

@Component({
  selector: 'app-adminEditPack',
  templateUrl: './edit.component.html'
})
export class AdminEditPackComponent {

  products: Product[];
  pack: Pack;

  constructor(private servicePack: PackService, private serviceProduct: ProductService, private router: Router,
    private activatedRoute: ActivatedRoute, private service: ProductService) {
  }

  ngOnInit() {
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
        product => {  this.router.navigate(['/admin/packs']);},
        error => console.log(error)
    );
  }

}
