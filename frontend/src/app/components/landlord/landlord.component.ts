import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-landlord-applications',
  templateUrl: './landlord.component.html',
  styleUrls: ['./landlord.component.css']
})
export class LandlordComponent implements OnInit {

  activeTab: 'properties' | 'applications' = 'properties';

   properties: any[] = [];
  applications: any[] = [];
  loading = false;
  userName!: string;
  loadingProperties = false;
loadingApplications = false;


  constructor(private http: HttpClient,
    private route: ActivatedRoute,
     private router: Router
  ) {}

  ngOnInit(): void {
  this.userName = localStorage.getItem('userName')!;

  this.route.params.subscribe(params => {
    const tab = params['tab'];

    if (tab === 'applications') {
      this.activeTab = 'applications';
      this.loadApplications();
    } else {
      this.activeTab = 'properties';
      this.loadProperties();
    }
  });
}

openProperty(id: number) {
  this.router.navigate(['/property', id]);
}


  loadApplications() {
    this.loading = true;

    this.http
      .get<any[]>(`https://renteasy-fix3.onrender.com/application/user/${this.userName}`)
      .subscribe({
        next: res => {
          this.applications = res;
          this.loading = false;
        },
        error: () => {
          alert('Failed to load applications');
          this.loading = false;
        }
      });
  }
  

  //  ACCEPT / REJECT
 updateStatus(appId: number, status: string) {
  this.http
    .put(
      `https://renteasy-fix3.onrender.com/application/update/${appId}?status=${status}`,
      {}
    )
    .subscribe({
      next: () => {
        // update UI without reload
        const app = this.applications.find(a => a.id === appId);
        if (app) {
          app.status = status;
        }
      },
      error: () => alert('Failed to update status')
    });
}

deleteApplication(appId: number) {
  if (!confirm('Are you sure you want to delete this application?')) {
    return;
  }

  this.http
    .delete(`https://renteasy-fix3.onrender.com/application/delete/${appId}`)
    .subscribe({
      next: () => {
        // 🔥 remove from UI instantly
        this.applications = this.applications.filter(
          app => app.id !== appId
        );
      },
      error: () => {
        alert('Failed to delete application');
      }
    });
}

switchTab(tab: 'properties' | 'applications') {
  if (this.activeTab === tab) return;

  this.activeTab = tab;

  if (tab === 'properties' && this.properties.length === 0) {
    this.loadProperties();
  }

  if (tab === 'applications' && this.applications.length === 0) {
    this.loadApplications();
  }
}

//  MY PROPERTIES
  loadProperties() {
  this.loadingProperties = true;

  this.http
    .get<any[]>(`https://renteasy-fix3.onrender.com/image/my/${this.userName}`)
    .subscribe({
      next: res => {
        this.properties = res;
        this.loadingProperties = false;
      },
      error: () => {
        alert('Failed to load properties');
        this.loadingProperties = false;
      }
    });
}



}
