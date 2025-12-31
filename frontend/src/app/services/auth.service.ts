import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class AuthService {

  private roleSubject = new BehaviorSubject<string | null>(
    localStorage.getItem('role')
  );

  private usernameSubject = new BehaviorSubject<string | null>(
    localStorage.getItem('userName')
  );

  role$ = this.roleSubject.asObservable();
  userName$ = this.usernameSubject.asObservable();


  setRole(role: string,userName: string) {
    localStorage.setItem('role', role);
    this.roleSubject.next(role);

    localStorage.setItem('userName', userName);
    this.usernameSubject.next(userName);

  }

  logout() {
    localStorage.removeItem('role');
    this.roleSubject.next(null);   //  THIS REFRESHES UI
    
    localStorage.removeItem('userName');
    this.usernameSubject.next(null);
  }

  get role(): string | null {
    return this.roleSubject.value;
  }

  get username(): string | null {
    return this.usernameSubject.value;
  }
}
