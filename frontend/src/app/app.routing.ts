import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import { PublicComponent } from './component/public/public.component';
import { AboutComponent } from './component/public/about/about.component';
import {FooterComponent} from './component/public/footer/footer.component';
import { HomeComponent } from './component/public/home/home.component';
import { LoginComponent } from './component/public/login/login.component';
import { RegisterComponent } from './component/public/register/register.component';
import { ProductComponent } from './component/public/product/product.component';
import { PlistComponent } from './component/public/plist/plist.component';

const appRoutes = [

 	{ path: '', component: PublicComponent, useAsDefault: true,
		children: [
			{path: 'contact', component: AboutComponent},
			{path: 'home', component: HomeComponent},
			{path: 'plist', component: PlistComponent},
			{path: 'product', component: ProductComponent},
			{path: 'login', component: LoginComponent},
			{path: 'register', component: RegisterComponent},
		]
	}
];

export const routing = RouterModule.forRoot(appRoutes);
