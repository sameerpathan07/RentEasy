import { Component, EventEmitter, inject, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  loginForm!: FormGroup;
  error: string | null = null;

  constructor(private fb: FormBuilder, private service: UserService, private authService: AuthService, private router: Router) {}

  ngOnInit() {
    this.loginForm = this.fb.group({
      userName: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

login() {
  if (this.loginForm.valid) {
    this.service.login(this.loginForm.value).subscribe(
      (res) => {
        this.authService.setRole(res.role,res.userName); // TENANT / LANDLORD
        this.router.navigate(['/']);
 //  HOME PAGE
      },
      () => this.error = 'Invalid credentials'
    );
  }
}


  goToRegister() {
  this.router.navigate(['/register']);
}

goToForgot() {
  this.router.navigate(['/forgot-password']);
}

}
