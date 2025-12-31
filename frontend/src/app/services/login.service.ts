import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) { }
  loginUser(username: string, password: string): Observable<any> {
    console.log('service :', username);
    console.log('service :', password);
    const url = 'http://localhost:8091/user/login';
    return this.http
      .post<any>(url, { username, password })
      .pipe(catchError(this.handleError));
  }
  private handleError(httpErrorResponse: HttpErrorResponse): Observable<never> {
    let errorMessage = 'An unknown error occurred!';
    if (httpErrorResponse.error instanceof ErrorEvent) {
      // Client-side error
      errorMessage = `Client-side error: ${httpErrorResponse.error.message}`;
      alert('client side error');
    } else {
      // Server-side error
      if (httpErrorResponse.status === 0) {
        // Network error
        errorMessage = 'Server Down. Please try again later.';
      } else {
        alert('server side error 500'+ httpErrorResponse);

        errorMessage = `Server-side error: ${httpErrorResponse.status} - ${httpErrorResponse.error.message}`;
      }
    }
    return throwError(errorMessage);
  }

}
