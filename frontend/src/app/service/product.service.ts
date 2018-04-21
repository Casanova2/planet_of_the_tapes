import { Injectable } from '@angular/core';
import { Headers, Http, Response, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';
// tslint:disable-next-line:import-blacklist
import 'rxjs/Rx';

import { PRODUCTS_URL, SINGLEPRODUCT_URL, ALLPRODUCTS_URL, ADDPRODUCT_URL } from '../util';
import {BASE_URL} from "../util";
import { Product } from '../model/product.model';

import {Pack} from '../model/pack.model';
import {POrder} from '../model/pOrder.model';

export interface Product {
  id: number;
  name: string;
  description: string;
  type: string;
  genre: string;
  stock: number;
  pbuy: number;
  prent: number;
  score: number;
  trailer: string;
  director: string;
  cast: string;
  year: number;
  urlimg?: any;
  hasPhoto?: boolean;
  packs?: Pack[];
  orders?: POrder[];
}

@Injectable()
export class ProductService {

  constructor(private http: Http) {}

  getProducts() {
    return this.http.get(ALLPRODUCTS_URL, { withCredentials: true })
      .map(response => response.json())
      .catch(error => this.handleError(error));
  }

  getProduct(id: number) {
    return this.http.get(SINGLEPRODUCT_URL + '/' + id)
      .map(response => response.json())
      .catch(error => Observable.throw('Server error'));
  }

  getAllProducts(enlace?: number) {
    const url = PRODUCTS_URL + '?enlace=' + enlace;
    return this.http.get(url)
      .map(response => response.json())
      .catch(error => Observable.throw('Server error'));
  }

  getAllListProducts() {
    const url = ALLPRODUCTS_URL;
    return this.http.get(url)
      .map(response => response.json())
      .catch(error => Observable.throw('Server error'));
  }

  updateProduct(product: Product) {
    const body = JSON.stringify(product);
    const headers: Headers = new Headers();
    headers.append('Content-type', 'application/json');
    headers.append('X-Requested-With', 'XMLHttpRequest');
    const options = new RequestOptions({ withCredentials: true, headers });
    return this.http.put(BASE_URL + 'product/' + product.id, body, options)
      .map(response => response.json())
      .catch(error => Observable.throw('Server error'));
  }

  createProduct(name:string, description:string,type:string,genre:string,stock:number,pbuy:number,prent:number,score:number,trailer:string,director:string,cast:string,year:number) {
    let newproduct:Product;
    newproduct={name:name,description:description,type:type,genre:genre,stock:stock,pbuy:pbuy,prent:prent,score:score,trailer:trailer,director:director,cast:cast,year:year};
    const headers = new Headers({
      'Content-Type': 'application/json',
      'X-Requested-With': 'XMLHttpRequest'
    });
    const options = new RequestOptions({ withCredentials: true, headers });
    console.log(BASE_URL + 'product');
      return this.http.post(BASE_URL + 'product', newproduct, options)
        .map(response => response.json())
        .catch(error => this.handleError(error));
    }

  removeProduct(id: number) {

    const headers = new Headers({
      'Content-Type': 'application/json',
      'X-Requested-With': 'XMLHttpRequest'
    });
    const options = new RequestOptions({ withCredentials: true, headers });

    return this.http.delete(BASE_URL + 'product/' + id, options)
      .map(response => response.json)
      .catch(error => this.handleError(error));
  }

  private handleError(error: any) {
    console.error(error);
    return Observable.throw('Server error (' + error.status + '): ' + error.text());
  }
}
