import { Component, OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import { Headers, Http, Response, RequestOptions } from '@angular/http';

import { Product, ProductService } from '../../../service/product.service';
import { DomSanitizer } from '@angular/platform-browser';
import {STATUS_NO_CONTENT, PRODUCTS_IMG_URL, BASE_URL} from '../../../util';
import { POrder, OrderService, User } from '../../../service//order.service';

import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
// tslint:disable-next-line:import-blacklist
import 'rxjs/Rx';

import { PRODUCTS_URL, SINGLEPRODUCT_URL, ALLPRODUCTS_URL, ADDPRODUCT_URL, CHECKOUT_URL, UORDERS_URL, ORDER_URL } from '../../../util';


@Component({
  selector: 'app-cart',
  templateUrl: 'cart.component.html'
})


export class CartComponent {


  user: User;
  products: Product[];
  img_url: string;
  orders: POrder[];

  constructor(private orderservice: OrderService, public sanitizer: DomSanitizer, private http: Http, private activatedRoute: ActivatedRoute) {
    this.img_url = PRODUCTS_IMG_URL;
  }

    // tslint:disable-next-line:use-life-cycle-interface
    ngOnInit() {
        this.orderservice.getUOrders().subscribe(
        orders => this.orders = orders,
        error => console.log(error)
      );
    }
    checkOut(id: number) {
      return this.http.put(CHECKOUT_URL + id, { withCredentials: true })
        .map(response => response.json())
        .catch(error => this.handleError(error));
    }
    private handleError(error: any) {
      console.error(error);
      return Observable.throw('Server error (' + error.status + '): ' + error.text());
    }
  }
