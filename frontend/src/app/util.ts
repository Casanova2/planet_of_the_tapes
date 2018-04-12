import { environment } from '../environments/environment';

export const BASE_URL = environment.API_URL;

export const PRODUCTS_URL = BASE_URL + 'products';
export const USER_URL = BASE_URL + 'users';
export const PACKS_URL = BASE_URL + 'packs';
export const ORDERS_URL = BASE_URL + 'orders';

// export const PRODUCTS_IMG_URL = "./assets/img/books/";
// export const IMG_URL = "./assets/img/";
// export const ADMIN_IMG_URL = "../../../assets/img/";

export const STATUS_NO_CONTENT = 204;
