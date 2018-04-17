import { Component, OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {Product} from '../../../service/product.service';
import {STATUS_NO_CONTENT} from '../../../util';
import { POrder, OrderService } from '../../../service/order.service';

@Component({
  selector: 'app-orders',
  templateUrl: 'aorders.component.html'
})

export class AdminOrdersComponent implements OnInit{

    orders: POrder[];
    products: Product[];

  constructor(private service: OrderService, private activatedRoute: ActivatedRoute) {
  }

    // tslint:disable-next-line:use-life-cycle-interface
    ngOnInit() {
        this.service.getOrders().subscribe(
        orders => this.orders = orders,
        error => console.log(error)
      );

    }
    
  }