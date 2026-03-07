import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Country } from '../models/country';

@Injectable({
  providedIn: 'root'
})
export class CountryService {
  private apiUrl = "http://localhost:8080/api/country";

  constructor(private http: HttpClient) { }

    getCountries(): Observable<Country[]> {
    return this.http.get<Country[]>(this.apiUrl);
  }

  createCountry(country: Country): Observable<Country> {
    return this.http.post<Country>(this.apiUrl, country);
  }
}
