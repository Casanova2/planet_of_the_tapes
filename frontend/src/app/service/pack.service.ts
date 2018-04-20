import { Injectable } from '@angular/core';
import { Headers, Http, Response, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';
// tslint:disable-next-line:import-blacklist
import 'rxjs/Rx';

import { PACK_URL, PACKS_URL, PRODUCTS_URL, SINGLEPRODUCT_URL, ALLPRODUCTS_URL, ADDPRODUCT_URL } from '../util';
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

export interface Pack {
  id?: number;
  name: string;
  price: number;
  products?: Product[];
}

@Injectable()
export class PackService {

  authCreds: string;

  products: any[] = [];
  curUser: any = this.products[0]; // first will be selected by default by browser

  constructor(private http: Http) {}

  getPack(id: number) {
    return this.http.get(PACKS_URL + id, { withCredentials: true })
      .map(response => response.json())
      .catch(error => this.handleError(error));
  }

  getPacks() {
    return this.http.get(PACKS_URL, { withCredentials: true })
      .map(response => response.json())
      .catch(error => this.handleError(error));
  }

  setNewProduct(id: any): void {
      console.log(id);
      // Match the selected ID with the ID's in array
      this.curUser = this.products.filter(value => value.id === parseInt(id));
      console.log(this.curUser);
  }

  /*updateProduct(product: Product) {
    const body = JSON.stringify(product);
    const headers: Headers = new Headers();
    headers.append('Content-type', 'application/json');
    headers.append('X-Requested-With', 'XMLHttpRequest');
    const options = new RequestOptions({ withCredentials: true, headers });
    return this.http.put(BASE_URL + 'product/' + product.id, body, options)
      .map(response => response.json())
      .catch(error => Observable.throw('Server error'));
  }*/

  createPack(pack: Pack){
    /*let newPack: Pack;*/

   /* this.products.push(
      {p1},
      {p2},
      {p3}
    )
    
    newPack={name:name,price:price,products:this.products};*/

    const body = JSON.stringify(pack);
    const headers = new Headers({
        'Content-Type': 'application/json',
        'X-Requested-With': 'XMLHttpRequest'
    });

    console.log(pack);

    const options = new RequestOptions({ withCredentials: true, headers });
      return this.http.post(PACK_URL, pack, options)
        .map(response => response.json())
        .catch(error => this.handleError(error));
  }

  updatePack(pack: Pack) {
    const body = JSON.stringify(pack);
    const headers: Headers = new Headers();
    headers.append('Content-type', 'application/json');
    headers.append('X-Requested-With', 'XMLHttpRequest');
    const options = new RequestOptions({ withCredentials: true, headers });
    return this.http.put(PACK_URL + pack.id, body, options)
      .map(response => response.json())
      .catch(error => Observable.throw('Server error'));
  }

  removePack(id: number) {

    const headers = new Headers({
      'Content-Type': 'application/json',
      'X-Requested-With': 'XMLHttpRequest'
    });
    const options = new RequestOptions({ withCredentials: true, headers });

    return this.http.delete(PACK_URL + id, options)
      .map(response => response.json)
      .catch(error => this.handleError(error));
  }

  private handleError(error: any) {
    console.error(error);
    return Observable.throw('Server error (' + error.status + '): ' + error.text());
  }
}
