import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import {LoginService} from './service/login.service';
import { LoginComponent } from './component/login/login.component';
import { routing } from './app.routing';
import { FormsModule } from '@angular/forms';
import { HttpModule, JsonpModule } from '@angular/http';
import { PublicComponent } from './component/public.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    PublicComponent
  ],
  imports: [BrowserModule, FormsModule, HttpModule, JsonpModule, routing],
  providers: [LoginService],
  bootstrap: [AppComponent]
})
export class AppModule { }
