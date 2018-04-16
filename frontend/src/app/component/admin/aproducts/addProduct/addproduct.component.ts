import { Component } from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import { Product, ProductService } from '../../../../service/product.service';

@Component({
  selector: 'app-addproduct',
  templateUrl: 'addproduct.component.html'
})

export class AddProductComponent {

  private product: Product;
  newProduct: boolean;
  
  constructor(private service: ProductService, activatedRoute: ActivatedRoute, private router: Router) {
    const id = activatedRoute.snapshot.params['id'];
    this.newProduct = true;
}

  
  
    addProduct(name:string, description:string,type:string,genre:string,stock:number,pbuy:number,prent:number,score:number,trailer:string,director:string,cast:string,year:number) {    
      //let newproduct :Product;
     // newproduct={name:name,description:description,type:type,genre:genre,stock:stock,pbuy:pbuy,prent:prent,score:score,trailer:trailer,director:director,cast:cast,year:year};
        this.service.createProduct(name,description,type,genre,stock,pbuy,prent,score,trailer,director,cast,year).subscribe(
            product => {  this.router.navigate(['/admin/products']);},
            error => console.log(error)
        );
        window.history.back();
    }

    cancel() {
        window.history.back();
    }
  }