import {Pack} from './pack.model';
import {POrder} from './pOrder.model';

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
  hasPhoto?: boolean;
  packs?: Pack[];
  orders?: POrder[];
}
