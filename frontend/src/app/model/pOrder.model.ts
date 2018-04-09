import {Pack} from './pack.model';
import {Product} from './product.model';

export interface POrder {
  id?: number;
  state: string;
  pay: string;
  type: string;
  total: number;
  products?: Product[];
  packs?: Pack[];
}
