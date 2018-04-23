import { Component, OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import { Product, ProductService } from '../../../service/product.service';
import { DomSanitizer } from '@angular/platform-browser';
import {STATUS_NO_CONTENT, PRODUCTS_IMG_URL} from '../../../util';

@Component({
  selector: 'app-videogames',
  templateUrl: 'videogames.component.html'
})

export class VideoGamesComponent {

  products: Product[];
  img_url: string;

  constructor(private service: ProductService, private sanitizer: DomSanitizer, private activatedRoute: ActivatedRoute) {
    this.img_url = PRODUCTS_IMG_URL;
  }

    // tslint:disable-next-line:use-life-cycle-interface
    ngOnInit() {
        this.service.getAllProducts(3).subscribe(
        products => this.products = products,
        error => console.log(error)
      );

    }
  }
