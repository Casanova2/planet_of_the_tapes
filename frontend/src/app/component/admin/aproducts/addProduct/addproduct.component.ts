import { Component } from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import { Product, ProductService } from '../../../../service/product.service';
import {Subject} from 'rxjs/Subject';
import {debounceTime} from 'rxjs/operator/debounceTime';
import {NgbAlertConfig} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-addproduct',
  templateUrl: 'addproduct.component.html',
  providers: [NgbAlertConfig]
})

export class AddProductComponent {
    //alerts
  private _success = new Subject<string>();
  staticAlertClosed = false;
  successMessage: string;

  private product: Product;
  newProduct: boolean;
  
  constructor(private service: ProductService, activatedRoute: ActivatedRoute, private router: Router) {
    const id = activatedRoute.snapshot.params['id'];
    this.newProduct = true;
}
  ngOnInit():void {
  //alerts
      setTimeout(() => this.staticAlertClosed = true, 8000);
      this._success.subscribe((message) => this.successMessage = message);
      debounceTime.call(this._success, 8000).subscribe(() => this.successMessage = null);
    //--------//
  }

  
  
    addProduct(name:string, description:string,type:string,genre:string,stock:number,pbuy:number,prent:number,score:number,trailer:string,director:string,cast:string,year:number) {    
      //let newproduct :Product;
     // newproduct={name:name,description:description,type:type,genre:genre,stock:stock,pbuy:pbuy,prent:prent,score:score,trailer:trailer,director:director,cast:cast,year:year};
        this.service.createProduct(name,description,type,genre,stock,pbuy,prent,score,trailer,director,cast,year).subscribe(
            product => { this._success.next(`${new Date()} - 'Product added successfully.'`);},
            error => console.log(error)
        );
    }

    cancel() {
        window.history.back();
    }
  }