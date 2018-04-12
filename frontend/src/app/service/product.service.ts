import { Injectable } from '@angular/core';
import { Headers, Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
// tslint:disable-next-line:import-blacklist
import 'rxjs/Rx';
import { PRODUCTS_URL, SINGLEPRODUCT_URL, ALLPRODUCTS_URL } from '../util';

import { Product } from '../model/product.model';

@Injectable()
export class ProductService {

  authCreds: string;

  constructor(private http: Http) {
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
      .map(response => response.json().content)
      .catch(error => Observable.throw('Server error'));
  }

  getAllListProducts() {
    const url = ALLPRODUCTS_URL;
    return this.http.get(url)
      .map(response => response.json())
      .catch(error => Observable.throw('Server error'));
  }

  /*getPageProduct(page?: number) {
    const url = (page) ? PRODUCTS_URL + '?page=' + page : PRODUCTS_URL;
    return this.http.get(url)
      .map(response => response.json().content)
      .catch(error => Observable.throw('Server error'));
  }*/

  /*searchProducts(name: string, page: number) {
    return this.http.get(PRODUCTS_URL + '?name=' + name + '&page=' + page)
      .map(response => response.json().content)
      .catch(error => Observable.throw('Server error'));
  }*/

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
}
