import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Farm } from '../classes/farm';
import { environment } from 'src/environments/environment';


@Injectable({
  providedIn: 'root'
})
export class FarmService {

  private API_URL= environment.apiUrl;


  constructor(private http: HttpClient) { }

  addFarm(farm: Farm, accountId: string): Observable<any> {
    return this.http.post<any>(this.API_URL + `/farms/${accountId}`, farm);
  }

  getFarmsForAccount(accountId: string): Observable<any> { 
    return this.http.get<Farm[]>(this.API_URL + `/farms/${accountId}`, {observe: "response" });
  }

  getFarmById(farmId:string): Observable<any> {
    return this.http.get<Farm>(this.API_URL + `/farms/farm/${farmId}`, {observe: "response" });
  }

  editFarm(farm: Farm, farmId:string): Observable<any> {
    return this.http.put<Farm>(this.API_URL + `/farms/${farmId}`, farm);
  }

  deleteFarmById(accountId:string,farmId:string): Observable<any> {
    return this.http.delete<Farm[]>(this.API_URL + `/farms/${accountId}/${farmId}`, {observe: "response" });
  }


  getFarmsForUser(userId: string): Observable<any> {
    return this.http.get<Farm[]>(this.API_URL + `/farms/user/${userId}`, { observe: "response" });
  }
}
