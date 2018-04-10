import { Routes, RouterModule } from '@angular/router';

import {LoginComponent} from '../app/component/login/login.component';
import { AppComponent } from './app.component';
import { PublicComponent } from './component/public.component';

const appRoutes = [
  { path: '', component: PublicComponent, useAsDefault: true},
  { path: 'login', component: LoginComponent }
];

export const routing = RouterModule.forRoot(appRoutes);
