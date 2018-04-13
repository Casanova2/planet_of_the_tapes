import { Component, OnInit } from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {DomSanitizer} from '@angular/platform-browser';

import { Product, ProductService } from '../../../service/product.service';
import {STATUS_NO_CONTENT, PRODUCTS_IMG_URL} from "../../../util";

@Component({
  templateUrl: 'home.component.html'
})

export class HomeComponent implements OnInit { 

	series: Product[];
	movies: Product[];
	videogames: Product[];
	img_url: string;

	constructor(private router: Router, private sanitizer: DomSanitizer, private service: ProductService) { 
		this.img_url = PRODUCTS_IMG_URL;
	}

	ngOnInit() {
		this.service.getAllProducts(1).subscribe(
		  series => this.series = series,
		  error => console.log(error)
		);
		this.service.getAllProducts(2).subscribe(
		  movies => this.movies = movies,
		  error => console.log(error)
		);
		this.service.getAllProducts(3).subscribe(
		  videogames => this.videogames = videogames,
		  error => console.log(error)
		);
	}

	newProduct() {
		this.router.navigate(['/product/new']);
	}
}
