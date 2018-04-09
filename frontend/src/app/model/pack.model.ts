import {Product} from './product.model';

export interface Pack {
  id?: number;
  name: string;
  price: number;
  hasPhoto?: boolean;
  products?: Product[];
}
