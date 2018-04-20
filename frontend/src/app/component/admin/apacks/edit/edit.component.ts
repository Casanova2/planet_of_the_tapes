import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { DomSanitizer } from '@angular/platform-browser';
import { Product, ProductService } from '../../../../service/product.service';

@Component({
  selector: 'app-editProduct',
  templateUrl: './edit.component.html'
})
export class AdminEditProductComponent {

  product: Product;

  constructor(private router: Router,
    private activatedRoute: ActivatedRoute, private service: ProductService) {
  }

  ngOnInit() {
    this.service.getProduct(this.activatedRoute.snapshot.params['id']).subscribe(
      product => { 
       this.product = product;
     },
     error => console.log(error)
   );
  }

  updateProduct() {    
      //let newproduct :Product;
     // newproduct={name:name,description:description,type:type,genre:genre,stock:stock,pbuy:pbuy,prent:prent,score:score,trailer:trailer,director:director,cast:cast,year:year};
        this.service.updateProduct(this.product).subscribe(
            product => {  this.router.navigate(['/admin/products']);},
            error => console.log(error)
        );
    }

}
