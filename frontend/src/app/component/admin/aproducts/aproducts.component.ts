import { Component, OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import { Product, ProductService } from '../../../service/product.service';
import {STATUS_NO_CONTENT} from "../../../util";

@Component({
  selector: 'app-adminproducts',
  templateUrl: 'aproducts.component.html'
})

export class AdminProductsComponent {

  products: Product[];

  constructor(private service: ProductService, private activatedRoute: ActivatedRoute) {
  }

    ngOnInit() {
        this.service.getProducts().subscribe(
        products => this.products = products,
        error => console.log(error)
      );
      
    }
  }