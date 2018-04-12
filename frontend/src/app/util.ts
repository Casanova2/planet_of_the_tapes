import { environment } from '../environments/environment';

export const BASE_URL = environment.API_URL;

export const PRODUCTS_URL = BASE_URL + 'mplist';
export const USER_URL = BASE_URL + 'admin-userList';
export const PACKS_URL = BASE_URL + 'mplist/packs';
export const ORDERS_URL = BASE_URL + 'admin-orderlist';
export const SINGLEPRODUCT_URL = BASE_URL + 'product';
export const ALLPRODUCTS_URL = BASE_URL + 'admin-products';

// export const PRODUCTS_IMG_URL = "./assets/img/books/";
// export const IMG_URL = "./assets/img/";
// export const ADMIN_IMG_URL = "../../../assets/img/";

export const STATUS_NO_CONTENT = 204;
