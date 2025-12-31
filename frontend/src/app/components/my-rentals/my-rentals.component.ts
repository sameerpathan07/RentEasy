import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-my-rentals',
  templateUrl: './my-rentals.component.html',
  styleUrls: ['./my-rentals.component.css']
})
export class MyRentalsComponent implements OnInit {

  activeSection: 'wishlist' | 'applications' = 'wishlist';

  wishlist: any[] = [];
  applications: any[] = [];
  loading = false;
  userName!: string;

  constructor(
    private route: ActivatedRoute,
    private http: HttpClient,
    private router: Router
  ) {}

  ngOnInit() {
    this.userName = localStorage.getItem('userName')!;

    this.route.params.subscribe(params => {
      this.activeSection = params['tab'] || 'wishlist';
      this.loadData();
    });
  }

  loadData() {
    if (!this.userName) return;

    this.loading = true;

    //  CLEAR OLD DATA (IMPORTANT)
    this.wishlist = [];
    this.applications = [];

    if (this.activeSection === 'wishlist') {
      this.http
        .get<any[]>(`http://localhost:8091/wishlist/${this.userName}`)
        .subscribe({
          next: res => {
            this.wishlist = res;
            this.loading = false;
          },
          error: () => {
            alert('Failed to load wishlist');
            this.loading = false;
          }
        });
    }

    if (this.activeSection === 'applications') {
  this.http
    .get<any[]>(`http://localhost:8091/application/tenant/${this.userName}`)
    .subscribe({
      next: res => {
  this.applications = res
    .map(app => ({
      ...app,
      meetingDate: app.meetingDate
        ? new Date(app.meetingDate)
        : null
    }))
    .sort((a, b) => {
      //  STATUS PRIORITY
      const priority = (status: string) => {
        if (status === 'APPROVED') return 1;
        if (status === 'PENDING') return 2;
        return 3; // REJECTED
      };

      const p1 = priority(a.status);
      const p2 = priority(b.status);

      if (p1 !== p2) {
        return p1 - p2; // lower number comes first
      }

      // optional: newest first inside same status
      if (a.meetingDate && b.meetingDate) {
        return b.meetingDate.getTime() - a.meetingDate.getTime();
      }

      return 0;
    });

  this.loading = false;
},

      error: () => {
        alert('Failed to load applications');
        this.loading = false;
      }
    });
}

  }

  openProperty(id: number) {
    this.router.navigate(['/property', id]);
  }

  deleteApplication(appId: number) {
  if (!confirm('Are you sure you want to delete this application?')) {
    return;
  }

  this.http
    .delete(`http://localhost:8091/application/delete/${appId}`)
    .subscribe({
      next: () => {
        //  remove from UI instantly
        this.applications = this.applications.filter(
          app => app.id !== appId
        );
      },
      error: () => {
        alert('Failed to delete application');
      }
    });
}

}
