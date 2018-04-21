import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { DomSanitizer } from '@angular/platform-browser';
import { Product, ProductService } from '../../../../service/product.service';
import {Subject} from 'rxjs/Subject';
import {debounceTime} from 'rxjs/operator/debounceTime';
import {NgbAlertConfig} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-editProduct',
  templateUrl: './edit.component.html',
  providers: [NgbAlertConfig]
})
export class AdminEditProductComponent {
   //alerts
  private _success = new Subject<string>();
  staticAlertClosed = false;
  successMessage: string;

  product: Product;

  constructor(private router: Router,
    private activatedRoute: ActivatedRoute, private service: ProductService) {
  }

  ngOnInit():void {
    //alerts
      setTimeout(() => this.staticAlertClosed = true, 5000);
      this._success.subscribe((message) => this.successMessage = message);
      debounceTime.call(this._success, 5000).subscribe(() => this.successMessage = null);
    //--------//
    this.service.getProduct(this.activatedRoute.snapshot.params['id']).subscribe(
      product => { 
       this.product = product;
     },
     error => console.log(error)
   );
  }

  updateProduct() {    

        this.service.updateProduct(this.product).subscribe(
            response => { 
              this._success.next(`${new Date()} - 'Product edited successfully.'`);
              this.router.navigate(['/admin/products/edit',this.product.id]);
            },
            error => console.log(error)
        );
      }
}
