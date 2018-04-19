import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

// INDEX
import { AppComponent } from './app.component';
import { PublicComponent } from './component/public/public.component';
import { AboutComponent } from './component/public/about/about.component';
import { FooterComponent } from './component/public/footer/footer.component';
import { HomeComponent } from './component/public/home/home.component';
import { LoginComponent } from './component/public/login/login.component';
import { RegisterComponent } from './component/public/register/register.component';
import { ProductComponent } from './component/public/product/product.component';
import { CartComponent } from './component/public/cart/cart.component';
import { VideoGamesComponent } from './component/public/videogames/videogames.component';
import { SeriesComponent } from './component/public/series/series.component';
import { MoviesComponent } from './component/public/movies/movies.component';
import { AdminEditProductComponent } from './component/admin/aproducts/edit/edit.component';

//ADMIN
import { AdminComponent } from './component/admin/admin.component';
import { AdminDashboardComponent } from './component/admin/dashboard/dashboard.component';
import { AdminHeaderComponent } from './component/admin/header/header.component';
import { AdminSidebarComponent } from './component/admin/sidebar/sidebar.component';
import { AdminProductsComponent } from './component/admin/aproducts/aproducts.component';
import { AdminUsersComponent } from './component/admin/ausers/ausers.component';
import { AddProductComponent} from './component/admin/aproducts/addProduct/addproduct.component';
import { AddUserComponent } from './component/admin/ausers/addUser/adduser.component';
import { AdminOrdersComponent } from './component/admin/aorders/aorders.component';
import { CanActivateViaAuthGuard } from './service/guard/guard';
import { ProfileComponent } from './component/admin/myProfile/profile.component';

const appRoutes = [

 	{ path: '', component: PublicComponent, useAsDefault: true,
		children: [
			{path: 'contact', component: AboutComponent},
			{path: 'home', component: HomeComponent},
			{path: 'videogames', component: VideoGamesComponent},
			{path: 'product', component: ProductComponent},
			{path: 'product/:id', component: ProductComponent},
			{path: 'login', component: LoginComponent},
		  {path: 'register', component: RegisterComponent},
		  {path: 'series', component: SeriesComponent},
      {path: 'movies', component: MoviesComponent},
      {path: 'cart', component: CartComponent},
		  {path: '', redirectTo: 'home', pathMatch: 'full' }
		]
	},

	{ path: 'admin', component: AdminComponent, useAsDefault: true, canActivateChild: [CanActivateViaAuthGuard],
		children: [
			{path: 'dashboard', component: AdminDashboardComponent},
			{path: '', redirectTo: 'dashboard', pathMatch: 'full' },
			{path: 'products', component: AdminProductsComponent},
			{path: 'users', component: AdminUsersComponent},
			{path: 'products/new', component: AddProductComponent },
			{path: 'users/new', component: AddUserComponent },
			{path: 'orders', component: AdminOrdersComponent},
			{path: 'products/edit/:id', component: AdminEditProductComponent},
			{path: 'profile', component: ProfileComponent},
			
		]
	}
];

export const routing = RouterModule.forRoot(appRoutes);
