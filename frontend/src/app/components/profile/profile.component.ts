import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CrudService } from 'src/app/services/crud.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  user: any;
  loading = true;

  constructor(
    private crudService: CrudService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadProfile();
  }

  loadProfile() {
    const userName = localStorage.getItem('userName');

    if (!userName) {
      this.router.navigate(['/login']);
      return;
    }

    this.crudService.getProfile(userName).subscribe({
      next: res => {
        this.user = res;
        this.loading = false;
      },
      error: err => {
        console.error(err);
        alert('Failed to load profile');
        this.loading = false;
      }
    });
  }
}
