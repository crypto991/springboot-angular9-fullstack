import { RegisterComponent } from './register/register.component';
import { AddFarmComponent } from './add-farm/add-farm.component';
import { ListAccountFarmsComponent } from './list-account-farms/list-account-farms.component';
import { AddAccountComponent } from './add-account/add-account.component';
import { ListUserAccountsComponent } from './list-user-accounts/list-user-accounts.component';
import { ListUserFarmsComponent } from './list-user-farms/list-user-farms.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { LoginComponent } from './login/login.component';
import { AuthGuard } from './_services/auth_guard.service';


const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'list-user-accounts', component: ListUserAccountsComponent, canActivate: [AuthGuard] },
  { path: 'list-user-farms', component: ListUserFarmsComponent, canActivate: [AuthGuard] },
  { path: 'addAccount/:accountId', component: AddAccountComponent, canActivate: [AuthGuard] },
  { path: 'addAccount', component: AddAccountComponent, canActivate: [AuthGuard] },
  { path: 'addFarm/:accountId', component: AddFarmComponent, canActivate: [AuthGuard] },
  { path: 'editFarm/:accountId/:farmId', component: AddFarmComponent, canActivate: [AuthGuard] },
  { path: 'list-account-farms/:accountId', component: ListAccountFarmsComponent, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }