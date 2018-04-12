import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import{HeaderComponent} from './component/public/header/header.component';
import {} from './service/login.service';

import { AppComponent } from './app.component';
import {LoginService} from './service/login.service';
import { routing } from './app.routing';
import { FormsModule } from '@angular/forms';
import { HttpModule, JsonpModule } from '@angular/http';

import { AboutComponent } from './component/public/about/about.component';
import {FooterComponent} from './component/public/footer/footer.component';
import { HomeComponent } from './component/public/home/home.component';
import { LoginComponent } from './component/public/login/login.component';
import { RegisterComponent } from './component/public/register/register.component';
import { ProductComponent } from './component/public/product/product.component';
import { PlistComponent } from './component/public/plist/plist.component';
import { PublicComponent } from './component/public/public.component';

import {UserService} from './service/user.service';
import {ProductService} from './service/product.service';

@NgModule({
  declarations: [
    PublicComponent,
    HomeComponent,
    PlistComponent,
    ProductComponent,
    LoginComponent,
    RegisterComponent,
    AboutComponent,
    FooterComponent,
    AppComponent,
    HeaderComponent
  ],
  imports: [BrowserModule, FormsModule, HttpModule, JsonpModule, routing],
  providers: [LoginService, UserService, ProductService],
  bootstrap: [AppComponent]
})
export class AppModule { }
