import { Component, EventEmitter, Output } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent {

  @Output() goToLogin = new EventEmitter<void>();

  constructor(private fb: FormBuilder) {}

  isSubmitting = false;
  success = false;

  forgotForm = this.fb.group({
    email: ['', [Validators.required, Validators.email]]
  });

  submit() {
    if (this.forgotForm.valid) {
      this.isSubmitting = true;

      // Mock API call
      setTimeout(() => {
        this.isSubmitting = false;
        this.success = true;
      }, 1500);
    } else {
      this.forgotForm.markAllAsTouched();
    }
  }
}
