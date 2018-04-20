import {POrder} from './pOrder.model';
  export interface User {
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
  orders?: POrder[];
  }
