import { Component, OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import { Product } from '../../../model/product.model';
import { ProductService } from '../../../service/product.service';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-movies',
  templateUrl: 'movies.component.html'
})

export class MoviesComponent {

  movies: Product[];

  constructor(private productService: ProductService, private activatedRoute: ActivatedRoute,
    public sanitizer: DomSanitizer) {}

    // tslint:disable-next-line:use-life-cycle-interface
    ngOnInit() {
      this.productService.getAllProducts(this.activatedRoute.snapshot.params[2]).subscribe(

        movies => {
          this.movies = movies;
          console.log(this.movies);
        },
        error => console.log(error)
      );
    }
  }
