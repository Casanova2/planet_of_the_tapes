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
import {Subject} from 'rxjs/Subject';
import {debounceTime} from 'rxjs/operator/debounceTime';
import {NgbAlertConfig} from '@ng-bootstrap/ng-bootstrap';

import { PRODUCTS_URL, SINGLEPRODUCT_URL, ALLPRODUCTS_URL, ADDPRODUCT_URL, CHECKOUT_URL, UORDERS_URL, ORDER_URL } from '../../../util';


@Component({
  selector: 'app-cart',
  templateUrl: 'cart.component.html',
  providers: [NgbAlertConfig]
})

export class CartComponent implements OnInit{
  //alerts
  private _success = new Subject<string>();
  staticAlertClosed = false;
  successMessage: string;

  products: Product[];
  img_url: string;
  orders: POrder[];

  constructor(private router: Router, private orderservice: OrderService, public sanitizer: DomSanitizer, private http: Http, private activatedRoute: ActivatedRoute) {
    this.img_url = PRODUCTS_IMG_URL;
  }

    ngOnInit() {
         //alerts
        setTimeout(() => this.staticAlertClosed = true, 9000);
        this._success.subscribe((message) => this.successMessage = message);
        debounceTime.call(this._success, 9000).subscribe(() => this.successMessage = null);
        //--------//
        this.orderservice.getUOrders().subscribe(
        orders => this.orders = orders,
        error => console.log(error)
      );
    }

    checkOut() {
      for (let order of this.orders){
      this.orderservice.checkOut(order).subscribe(
        response => {this._success.next(`${new Date()} - 'Your order has been completed successfully.'`);},
        error => console.log(error) 
        );
      }
    }

    
  }
