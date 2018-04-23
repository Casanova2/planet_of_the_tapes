import { Component, OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {Product} from '../../../service/product.service';
import {STATUS_NO_CONTENT} from '../../../util';
import { POrder, OrderService } from '../../../service/order.service';
import { SessionService } from '../../../service/session.service';

@Component({
  selector: 'app-orders',
  templateUrl: 'aorders.component.html'
})

export class AdminOrdersComponent implements OnInit{

    orders: POrder[];
    products: Product[];
    uorders: POrder[];

  constructor(private service: OrderService, private activatedRoute: ActivatedRoute, public session: SessionService) {
  }

    // tslint:disable-next-line:use-life-cycle-interface
    ngOnInit() {
        this.service.getOrders().subscribe(
        orders => this.orders = orders,
        error => console.log(error)
      );

        this.service.getUOrders().subscribe(
          uorders => this.uorders = uorders,
          error => console.log(error)
        );

    }
    
  }