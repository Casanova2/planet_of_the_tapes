import { Routes, RouterModule } from '@angular/router';

import {LoginComponent} from '../app/component/login/login.component';
import { AppComponent } from './app.component';

const appRoutes = [
  { path: '', component: AppComponent, useAsDefault: true},
  { path: 'login', component: LoginComponent }
];

export const routing = RouterModule.forRoot(appRoutes);
