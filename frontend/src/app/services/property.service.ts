import { Injectable } from '@angular/core';
import { Property } from '../models/property';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class PropertyService {


private apiUrl = 'http://localhost:8091/image';

  constructor(private http: HttpClient) {}

//   getAll(): Observable<Property[]> {
//   return this.http.get<Property[]>(this.apiUrl);
// }

getAll(): Observable<Property[]> {
  return this.http.get<Property[]>(
    'http://localhost:8091/image/getAllImg'
  );
}

getAllMy(): Observable<Property[]> {
  return this.http.get<Property[]>(
    'http://localhost:8091/image/my'
  );
}


 getById(id: number): Observable<Property> {
  return this.http.get<Property>(`${this.apiUrl}/getImg/${id}`);
}


  addImage(property: Property) {
    return this.http.post(`${this.apiUrl}/addImg`, property);
  }


  updateProperty(id: number, property: Property) {
  return this.http.put(`${this.apiUrl}/updateImg/${id}`,property);
}

  deleteProperty(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/deleteImg/${id}`);
  }

}
