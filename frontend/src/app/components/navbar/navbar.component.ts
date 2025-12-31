import { Component, NgModule, OnInit } from '@angular/core';
import { NavigationEnd, Router, RouterModule } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { CrudService } from 'src/app/services/crud.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})

export class NavbarComponent implements OnInit {

  @NgModule({
  imports: [RouterModule]
})

  userRole: string | null = null;
  // userName: string | null = null;
  showDropdown = false;

  constructor(public auth: AuthService,private crudService: CrudService,
    private router: Router) {}

  ngOnInit() {
    this.loadUser();

    //  THIS LINE FIXES YOUR ISSUE
    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        this.loadUser();
      }
    });
  }

  loadUser() {
    this.userRole = localStorage.getItem('role');
  }
  // loadUserName() {
  //   this.userName = localStorage.getItem('userName');
  // }

 logout() {
    this.auth.logout();             //  state update
    this.showDropdown = false;
    this.router.navigate(['/']);    // home
  }

  toggleDropdown() {
    this.showDropdown = !this.showDropdown;
  }

  closeDropdown() {
    this.showDropdown = false;
  }

 deleteAccount() {
  const userName = localStorage.getItem('userName');

  if (!userName) {
    alert('User not logged in');
    return;
  }

  if (confirm('This action cannot be undone. Continue?')) {
    this.crudService.deleteUser(userName).subscribe({
      next: () => {
        this.auth.logout();
        this.router.navigate(['/login']);
      },
      error: err => {
        console.error(err);
        alert('Failed to delete account');
      }
    });
  }
}


}
