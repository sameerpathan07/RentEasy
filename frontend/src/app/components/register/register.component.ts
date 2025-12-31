import { Component, EventEmitter, inject, Output } from '@angular/core';
import { FormBuilder, Validators, AbstractControl, ValidationErrors } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';

// Custom validator for password confirmation
function passwordMatchValidator(control: AbstractControl): ValidationErrors | null {
  const password = control.get('password');
  const confirmPassword = control.get('confirmPassword');

  if (password && confirmPassword && password.value !== confirmPassword.value) {
    return { passwordMismatch: true };
  }
  return null;
}

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  private userService = inject(UserService);

  @Output() registerSuccess = new EventEmitter<void>();
  @Output() goToLogin = new EventEmitter<void>();

  constructor(private fb: FormBuilder, private router: Router) {}

  registerForm = this.fb.group({
    userName: ['', Validators.required],
    email: ['', [Validators.required, Validators.email]],
    password: ['', [Validators.required, Validators.minLength(6)]],
    role: ['Tenant', Validators.required],
    phoneNo: ['', [Validators.required, Validators.pattern('^[0-9]{10}$')]]
  }, { validators: passwordMatchValidator });

  error = '';
  isSubmitting = false;

  register() {
  if (this.registerForm.valid) {
    this.userService.register(this.registerForm.value).subscribe(() => {
      alert('Registration successful');
      this.router.navigate(['/login']);
    });
  }
}


  get f() {
    return this.registerForm.controls;
  }
}
