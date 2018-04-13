import { Injectable } from '@angular/core';
import { Headers, Http, Response, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/Rx';

import { PRODUCTS_URL, SINGLEPRODUCT_URL, ALLPRODUCTS_URL } from '../util';

import { Product } from '../model/product.model';

import {Pack} from '../model/pack.model';
import {POrder} from '../model/pOrder.model';

export interface Product {
  id?: number;
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

  authCreds: string;

  constructor(private http: Http) {}

  getProducts() {
    return this.http.get(PRODUCTS_URL, { withCredentials: true })
      .map(response => response.json())
      .catch(error => this.handleError(error));
  }

  setAuthHeaders(authCreds: string) {
    this.authCreds = authCreds;
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
    let url = ALLPRODUCTS_URL;
    return this.http.get(url)
      .map(response => response.json())
      .catch(error => Observable.throw('Server error'));
  }

  getPageProduct(page?: number) {
    const url = (page) ? PRODUCTS_URL + '?page=' + page : PRODUCTS_URL;
    return this.http.get(url)
      .map(response => response.json().content)
      .catch(error => Observable.throw('Server error'));
  }

  searchProducts(name: string, page: number) {
    return this.http.get(PRODUCTS_URL + '?name=' + name + '&page=' + page)
      .map(response => response.json().content)
      .catch(error => Observable.throw('Server error'));
  }

  updateProduct(product: Product) {
    const body = JSON.stringify(product);

    this.authCreds = localStorage.getItem('creds');

    const headers: Headers = new Headers();
    headers.append('Content-Type', 'application/json');
    headers.append('X-Requested-With', 'XMLHttpRequest');
    headers.append('Authorization', 'Basic ' + this.authCreds);

    return this.http.put(PRODUCTS_URL + '/' + product.id, body, { headers: headers })
      .map(response => response.json())
      .catch(error => Observable.throw('Server error'));
  }

  createProduct(product: Product) {
    const body = JSON.stringify(product);

    this.authCreds = localStorage.getItem('creds');

    const headers: Headers = new Headers();
    headers.append('Content-Type', 'application/json');
    headers.append('X-Requested-With', 'XMLHttpRequest');
    headers.append('Authorization', 'Basic ' + this.authCreds);
    return this.http.post(PRODUCTS_URL, body, { headers: headers })
      .map(response => response.json())
      .catch(error => Observable.throw('Server error'));
  }

  updateFile(formData: FormData, id: number) {
    const headers: Headers = new Headers();
    headers.append('Accept', 'application/json');
    headers.append('Authorization', 'Basic ' + this.authCreds);
    return this.http.put(PRODUCTS_URL + '/' + id + '/upload', formData, { headers: headers })
      .map(response => console.log('Success. The file has been successfully added to server directories.'))
      .catch(error => Observable.throw('Server error'));
  }

  deleteProduct(id: number) {
    this.authCreds = localStorage.getItem('creds');

    const headers: Headers = new Headers();
    headers.append('Content-Type', 'application/json');
    headers.append('X-Requested-With', 'XMLHttpRequest');
    headers.append('Authorization', 'Basic ' + this.authCreds);

    return this.http.delete(PRODUCTS_URL + '/' + id, { headers: headers })
      .map(response => response.json())
      .catch(error => Observable.throw('Server error'));
  }

  private handleError(error: any) {
    console.error(error);
    return Observable.throw('Server error (' + error.status + '): ' + error.text());
  }
}
