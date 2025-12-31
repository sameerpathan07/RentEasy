import { Component, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { CrudService } from 'src/app/services/crud.service';

@Component({
  selector: 'app-update-user',
  templateUrl: './update-user.component.html',
  styleUrls: ['./update-user.component.css']
})
export class UpdateUserComponent {
activeTab: 'profile' | 'security' = 'profile';

  user: any;
  profileForm!: FormGroup;
  passwordForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private userService: CrudService,
    private auth: AuthService,
    private router: Router
  ) {}

  ngOnInit() {
    
    this.profileForm = this.fb.group({
      userName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      phoneNo: ['', Validators.required]
    });

    this.passwordForm = this.fb.group({
      userName: [{ value: localStorage.getItem('userName'), disabled: false}],
      oldPassword: ['', Validators.required],
      newPassword: ['', Validators.required]
    });

    this.loadProfile();

  }

 loadProfile() {
  const userName = localStorage.getItem('userName');

  if (!userName) {
    alert('User not logged in');
    this.router.navigate(['/login']);
    return;
  }

  this.userService.getProfile(userName).subscribe({
    next: res => {
      this.user = res;
      this.profileForm.patchValue(res); // ✅ AUTO-FILL
    },
    error: err => {
      console.error(err);
      alert('Failed to load profile');
    }
  });
}


  updateProfile() {
    this.userService.updateProfile(this.profileForm.value).subscribe(() => {
      alert('Profile updated successfully');
      this.router.navigate(['/']);
    });
  }

 changePassword() {
  const userName = localStorage.getItem('userName');

  if (!userName) {
    alert('User not logged in');
    return;
  }

  const payload = {
    userName: userName,
    oldPass: this.passwordForm.value.oldPassword,
    newPass: this.passwordForm.value.newPassword
  };

  this.userService.changePassword(payload).subscribe({
    next: () => {
      alert('Password changed successfully');
      this.passwordForm.reset();
      this.router.navigate(['/']);
    },
    error: err => {
      alert(err.error?.message || 'Password change failed');
    }
  });
}


  // deleteAccount() {
  //   if (confirm('This action cannot be undone. Continue?')) {
  //     this.userService.deleteAccount().subscribe(() => {
  //       this.auth.logout();
  //       this.router.navigate(['/login']);
  //     });
  //   }
  // }
   
}

