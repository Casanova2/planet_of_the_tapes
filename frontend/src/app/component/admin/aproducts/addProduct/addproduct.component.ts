import { Component } from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import { Product, ProductService } from '../../../../service/product.service';

@Component({
  selector: 'app-addproduct',
  templateUrl: 'addproduct.component.html'
})

export class AddProductComponent {

  product: Product;
  newProduct: boolean;
  
  constructor(private service: ProductService, activatedRoute: ActivatedRoute, private router: Router) {
    
    this.newProduct = false;  
  }

    addProduct() {        
        this.service.createProduct(this.product).subscribe(
            product => {  this.router.navigate(['/admin/products']);},
            error => console.log(error)
        );
        window.history.back();
    }

    cancel() {
        window.history.back();
    }
  }