import {POrder} from './pOrder.model';

 id?: number;
  name: string;
  passwordHash?: string;
  dni: string;
  email: string;
  telephone: number;
  viewTelephone?: boolean;
  address?: string;
  roles?: string[];
  hasPhoto?: boolean;
  orders?: POrder[];export interface User {
 

}
