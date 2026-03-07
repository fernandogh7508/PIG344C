import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Region } from '../models/region';

@Injectable({
  providedIn: 'root'
})
export class RegionService {

  private apiUrl = "http://localhost:8080/api/region";
 
   constructor(private http: HttpClient) { }
 
     getRegiones(): Observable<Region[]> {
     return this.http.get<Region[]>(this.apiUrl);
   }
 
   createRegion(region: Region): Observable<Region> {
     return this.http.post<Region>(this.apiUrl, region);
   }
}
