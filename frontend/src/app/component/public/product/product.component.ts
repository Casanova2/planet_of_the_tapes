import { Component, OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import { Product, ProductService } from '../../../service/product.service';
import { DomSanitizer } from '@angular/platform-browser';
import {STATUS_NO_CONTENT, PRODUCTS_IMG_URL} from "../../../util";

@Component({
  selector: 'app-product',
  templateUrl: 'product.component.html'
})
export class ProductComponent implements OnInit {
  
  product: Product;
  img_url: string;

  constructor(private service: ProductService, private sanitizer: DomSanitizer, private activatedRoute: ActivatedRoute) {
    this.img_url = PRODUCTS_IMG_URL;
  }

  ngOnInit() {
    this.service.getProduct(this.activatedRoute.snapshot.params['id']).subscribe(
      product => { 
       this.product = product; 
       console.log(this.product.name)
     },
     error => console.log(error)
   );
  }
}