import { Observable } from 'rxjs';
import { Customer } from './../classes/customer';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Account } from '../classes/account';
import { environment } from 'src/environments/environment';


@Injectable({
  providedIn: 'root'
})
export class AccountService {

  private API_URL= environment.apiUrl;


  constructor(private http: HttpClient) { }


  getAccountsForUser(userId: string): Observable<any> {
    return this.http.get<Account[]>(this.API_URL + `/accounts/${userId}`, { observe: "response" });
  }


  addAccount(account: Account, userId: string): Observable<any> {
    return this.http.post<any>(this.API_URL + `/accounts/${userId}`, account);
  }

  getAccountById(accountId: string): Observable<any> {
    return this.http.get<Account>(this.API_URL + `/accounts/${accountId}`, { observe: "response" });
  }

  updateAccount(accountId: string, account: Account): Observable<any> {
    return this.http.put<Account>(this.API_URL + `/accounts/${accountId}`, account);
  }

  deleteAccountById(userId: string, accountId: string): Observable<any> {
    return this.http.delete<Account>(this.API_URL + `/accounts/${userId}/${accountId}`);
  }
}
