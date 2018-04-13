import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { routing } from './app.routing';
import { FormsModule } from '@angular/forms';
import { HttpModule, JsonpModule } from '@angular/http';

// Services
import { LoginService} from './service/login.service';
import { UserService } from './service/user.service';
import {ProductService} from './service/product.service';

// COMPONENTS INDEX
import { HeaderComponent } from './component/public/header/header.component';
import { AppComponent } from './app.component';
import { AboutComponent } from './component/public/about/about.component';
import { FooterComponent } from './component/public/footer/footer.component';
import { HomeComponent } from './component/public/home/home.component';
import { LoginComponent } from './component/public/login/login.component';
import { RegisterComponent } from './component/public/register/register.component';
import { ProductComponent } from './component/public/product/product.component';
import { VideoGamesComponent } from './component/public/videogames/videogames.component';
import { SeriesComponent } from './component/public/series/series.component';
import { MoviesComponent } from './component/public/movies/movies.component';
import { PublicComponent } from './component/public/public.component';

// COMPONENTS ADMIN
import { AdminComponent } from './component/admin/admin.component';
import { AdminDashboardComponent } from './component/admin/dashboard/dashboard.component';
import { AdminHeaderComponent } from './component/admin/header/header.component';
import { AdminSidebarComponent } from './component/admin/sidebar/sidebar.component';

@NgModule({
  declarations: [

    // INDEX
    PublicComponent,
    HomeComponent,
    VideoGamesComponent,
    ProductComponent,
    LoginComponent,
    RegisterComponent,
    AboutComponent,
    FooterComponent,
    AppComponent,
    HeaderComponent,
    SeriesComponent,
    MoviesComponent,

    //ADMIN
    AdminComponent,
    AdminDashboardComponent,
    AdminHeaderComponent,
    AdminSidebarComponent

  ],
  imports: [BrowserModule, FormsModule, HttpModule, JsonpModule, routing],
  providers: [LoginService, UserService, ProductService],
  bootstrap: [AppComponent]
})
export class AppModule { }
