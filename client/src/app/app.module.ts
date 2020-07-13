import { AuthGuard } from './_services/auth_guard.service';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { BrowserModule } from '@angular/platform-browser';
import { LoginComponent } from './login/login.component';
import { AppComponent } from './app.component';


import { authInterceptorProviders } from './_helpers/auth.interceptor';
import { ListUserFarmsComponent } from './list-user-farms/list-user-farms.component';
import { ListUserAccountsComponent } from './list-user-accounts/list-user-accounts.component';
import { AddAccountComponent } from './add-account/add-account.component';
import { AddFarmComponent } from './add-farm/add-farm.component';
import { ListAccountFarmsComponent } from './list-account-farms/list-account-farms.component';
import { RegisterComponent } from './register/register.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    ListUserFarmsComponent,
    ListUserAccountsComponent,
    AddAccountComponent,
    AddFarmComponent,
    ListAccountFarmsComponent,
    RegisterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [AuthGuard, authInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }