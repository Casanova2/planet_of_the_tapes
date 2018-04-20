import { Component, OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import { Headers, Http, Response, RequestOptions } from '@angular/http';

import { Product, ProductService } from '../../../service/product.service';
import { DomSanitizer } from '@angular/platform-browser';
import {STATUS_NO_CONTENT, PRODUCTS_IMG_URL, BASE_URL} from '../../../util';
import { POrder, OrderService} from '../../../service//order.service';

import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
// tslint:disable-next-line:import-blacklist
import 'rxjs/Rx';

import { PRODUCTS_URL, SINGLEPRODUCT_URL, ALLPRODUCTS_URL, ADDPRODUCT_URL, CHECKOUT_URL, UORDERS_URL, ORDER_URL } from '../../../util';


@Component({
  selector: 'app-cart',
  templateUrl: 'cart.component.html'
})

export class CartComponent implements OnInit{

  products: Product[];
  img_url: string;
  orders: POrder[];

  constructor(private router: Router, private orderservice: OrderService, public sanitizer: DomSanitizer, private http: Http, private activatedRoute: ActivatedRoute) {
    this.img_url = PRODUCTS_IMG_URL;
  }

    ngOnInit() {
        this.orderservice.getUOrders().subscribe(
        orders => this.orders = orders,
        error => console.log(error)
      );
    }

    checkOut() {
      for (let order of this.orders){
      this.orderservice.checkOut(order).subscribe(
        response => {  this.router.navigate(['/']);},
        error => console.log(error) 
        );
      }
    }

    
  }
