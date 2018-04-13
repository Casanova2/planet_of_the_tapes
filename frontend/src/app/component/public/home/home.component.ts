import { Component, OnInit } from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {DomSanitizer} from '@angular/platform-browser';

import { Product, ProductService } from '../../../service/product.service';
import {STATUS_NO_CONTENT, PRODUCTS_IMG_URL} from "../../../util";

@Component({
  templateUrl: 'home.component.html'
})

export class HomeComponent implements OnInit { 

	products: Product[];
	img_url: string;

	constructor(private router: Router, private sanitizer: DomSanitizer, private service: ProductService) { 
		this.img_url = PRODUCTS_IMG_URL;
	}

	ngOnInit() {
		this.service.getProducts().subscribe(
		  products => this.products = products,
		  error => console.log(error)
		);
	}

	newBook() {
		this.router.navigate(['/product/new']);
	}
}
