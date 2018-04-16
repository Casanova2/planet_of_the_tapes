import { Component, OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import { Product, ProductService } from '../../../service/product.service';
import {STATUS_NO_CONTENT} from '../../../util';

@Component({
  selector: 'app-products',
  templateUrl: 'aproducts.component.html'
})

export class AdminProductsComponent implements OnInit{

  products: Product[];
  message: String;

  constructor(private service: ProductService, private activatedRoute: ActivatedRoute) {
  }

    // tslint:disable-next-line:use-life-cycle-interface
    ngOnInit() {
        this.service.getProducts().subscribe(
        products => this.products = products,
        error => console.log(error)
      );

    }


    deleteProduct(id: number) {
      this.service.removeProduct(id).subscribe(
        response => {
          this.message = 'Product deleted successfully.';
        },
        error => {
          this.message = 'Not found.'
        }
      );
    }  
  }
  
