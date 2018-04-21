import { Component, OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import { Product, ProductService } from '../../../service/product.service';
import { DomSanitizer } from '@angular/platform-browser';
import {STATUS_NO_CONTENT, PRODUCTS_IMG_URL} from "../../../util";
import {POrder, OrderService} from '../../../service/order.service';

@Component({
  selector: 'app-product',
  templateUrl: 'product.component.html'
})
export class ProductComponent implements OnInit {

  private product: Product;
  img_url: string;
  private movieP :boolean;
  private orders: POrder [];
  private order: POrder;

  constructor(private router: Router, private service: ProductService, private sanitizer: DomSanitizer, private activatedRoute: ActivatedRoute, 
    private oService: OrderService) {
    
      this.img_url = PRODUCTS_IMG_URL;
  }

  ngOnInit() {
    this.service.getProduct(this.activatedRoute.snapshot.params['id']).subscribe(
      product => { 
       this.product = product; 
       console.log(this.product.name)
     },
     error => console.log(error)
   );

    this.oService.getUOrders().subscribe(
      orders => {
        this.orders = orders;
      },
      error => console.log(error)
    );


  }

  addToCart(){
    
        this.oService.createOrder(this.activatedRoute.snapshot.params['id']).subscribe(
          response => {  this.router.navigate(['/cart']);},
          error => console.log(error) 
        );
      }
      
  updateCart(){

    for (let order of this.orders){
    this.oService.getUOrder(order.id);
      if (order.state == "progress"){
        this.oService.addProductToOrder(order, this.activatedRoute.snapshot.params['id']).subscribe(
          response => {  this.router.navigate(['/cart']);},
          error => console.log(error) 
        );
      } else if (order.state == "completed"){
        this.addToCart();
      }
    }
}
}