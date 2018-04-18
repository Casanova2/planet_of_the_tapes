import { Component, OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import { Product, ProductService } from '../../../service/product.service';
import {STATUS_NO_CONTENT} from '../../../util';
import {Subject} from 'rxjs/Subject';
import {debounceTime} from 'rxjs/operator/debounceTime';
import {NgbAlertConfig} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-products',
  templateUrl: 'aproducts.component.html',
  providers: [NgbAlertConfig]
})

export class AdminProductsComponent implements OnInit{

  //alerts
  private _success = new Subject<string>();
  staticAlertClosed = false;
  successMessage: string;

  products: Product[];
  message: String;

  constructor(private service: ProductService, private activatedRoute: ActivatedRoute) {
  }

    // tslint:disable-next-line:use-life-cycle-interface
    ngOnInit():void {
       //alerts
        setTimeout(() => this.staticAlertClosed = true, 5000);
        this._success.subscribe((message) => this.successMessage = message);
        debounceTime.call(this._success, 5000).subscribe(() => this.successMessage = null);
      //--------//
        this.service.getProducts().subscribe(
        products => this.products = products,
        error => console.log(error)
      );

    }


    deleteProduct(id: number) {
      this.service.removeProduct(id).subscribe(
        response => {
          this.message = 'Product deleted successfully.';
          this.service.getProducts().subscribe(
            products => this.products = products,
            error => console.log(error)
          );
          this._success.next(`${new Date()} - Message successfully changed.`);
        },
        error => {
          this.message = 'Not found.'
        }
      );
    }  
  }
  
