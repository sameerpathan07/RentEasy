import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CrudService {

constructor(private http: HttpClient) { }

private api = 'https://renteasy-production.up.railway.app';

  getProfile(userName: string) {
    return this.http.get<any>(`${this.api}/profile/${userName}`);
  }

  updateProfile(data: any) {
    return this.http.put(`${this.api}/update`, data);
  }

  changePassword(data: any) {
    return this.http.put(`${this.api}/updatePass`, data);
  }

  deleteUser(userName: string) {
  return this.http.delete(
    `https://renteasy-production.up.railway.app/user/delete/${userName}`
  );
}
}
