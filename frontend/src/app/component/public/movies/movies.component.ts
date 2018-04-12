import { Component, OnInit, DoCheck } from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import { Product } from '../../../model/product.model';
import { ProductService } from '../../../service/product.service';

@Component({
  selector: 'app-movies',
  templateUrl: 'movies.component.html'
})

export class MoviesComponent {

  movies: Product[];
  img_url: string;
  moreBooksActive: boolean;
  error: String;

  constructor(private productService: ProductService) {
    this.movies = [];
    // this.img_url = BOOKS_IMG_URL;

    this.addMovies(true);
  }

  addMovies(userReq: boolean) {
    this.productService.getAllProducts(2).subscribe(
      movies => {
        if (Object.keys(movies).length === 0) {
          this.moreBooksActive = false;
        } /*else if (userReq) {
          this.moreMoviesActive = true;
          this.moviesPage++;
          this.movies = this.movies.concat(movies);
          this.downloadImages(this.movies);
          this.addMovies(false);*/
        },
      error => console.log('Fail trying to get movies.')
    );

  }

  /*downloadImages(resources: Resource[]) {
    console.log('Downloading images from server...');
    for (let resource of resources) {
      this.fileService.getResourceFile(resource.id).subscribe(
        data => {
          let dataRecieved: string[] = data.split('"');
          resource.image = 'data:image/png;base64,' + dataRecieved[3];
        },
        error => console.log('Fail adding resource ' + resource.title + 'image.')
      );
    }
  }*/

}
