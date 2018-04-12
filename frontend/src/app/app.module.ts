import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import {LoginService} from './service/login.service';
import { LoginComponent } from './component/login/login.component';
import { routing } from './app.routing';
import { FormsModule } from '@angular/forms';
import { HttpModule, JsonpModule } from '@angular/http';
import { PublicComponent } from './component/public.component';

import {UserService} from './service/user.service';
import {ProductService} from './service/product.service';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    PublicComponent
  ],
  imports: [BrowserModule, FormsModule, HttpModule, JsonpModule, routing],
  providers: [LoginService, UserService, ProductService],
  bootstrap: [AppComponent]
})
export class AppModule { }
