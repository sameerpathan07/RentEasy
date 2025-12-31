import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PropertyService } from 'src/app/services/property.service';
import { Property } from 'src/app/models/property';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-property-details',
  templateUrl: './property-details.component.html',
  styleUrls: ['./property-details.component.css']
})
export class PropertyDetailsComponent implements OnInit {

  property!: Property;

  isLandlord = false;
  isOwner = false;


  bookingForm!: FormGroup;
  isSubmitting = false;
  bookingSuccess = false;

  saved = false;
  userName!: string;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private propertyService: PropertyService,
    private fb: FormBuilder,
    private http: HttpClient
  ) {}

  ngOnInit() {

    //  role
    const role = localStorage.getItem('role');
    this.isLandlord = role === 'Landlord';

    //  user
    this.userName = localStorage.getItem('userName') || '';

    //  booking form
    this.bookingForm = this.fb.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      phoneNo: ['', Validators.required],
      address: ['', Validators.required],
      date: ['', Validators.required]
    });

    //  get property
    const id = Number(this.route.snapshot.paramMap.get('id'));

    this.propertyService.getById(id).subscribe({
  next: data => {
    this.property = data;

    // check wishlist
    if (this.userName) {
      this.checkSavedStatus();
    }

    //  OWNER CHECK
    this.isOwner =
      this.isLandlord &&
      this.property.user?.userName === this.userName;
  },
  error: () => this.router.navigate(['/'])
});

  }

  // ---------------- WISHLIST ----------------

  checkSavedStatus() {
    this.isSaved(this.userName, this.property.id)
      .subscribe(res => {
        this.saved = res; //  stays black after refresh
      });
  }

  toggleSave() {
  if (!this.userName) {
    alert('Please login first');
    return;
  }

  if (this.saved) {
    //  UNSAVE
    this.removeFromWishlist();
  } else {
    //  SAVE
    this.addToWishlist();
  }

  this.saved = !this.saved;
}

removeFromWishlist() {
  this.http.delete(
    `http://localhost:8091/wishlist/remove/${this.property.id}/${this.userName}`
  ).subscribe({
    next: () => {
      console.log('Removed from wishlist');
    },
    error: err => {
      console.error(err);
      alert('Failed to remove from wishlist');
      this.saved = true; // rollback UI
    }
  });
}


  addToWishlist() {
    this.http.post(
      `http://localhost:8091/wishlist/add/${this.property.id}/${this.userName}`,
      {}
    ).subscribe();
  }

  isSaved(userName: string, propertyId: number) {
    return this.http.get<boolean>(
      `http://localhost:8091/wishlist/exists/${userName}/${propertyId}`
    );
  }

  // ---------------- OTHER ACTIONS ----------------

  goBack() {
    this.router.navigate(['/']);
  }

  deleteProperty() {
    if (!confirm('Are you sure you want to delete this property?')) return;

    this.propertyService.deleteProperty(this.property.id).subscribe(() => {
      alert('Property deleted successfully');
      this.router.navigate(['/']);
    });
  }

  editProperty() {
    this.router.navigate(['/update-property', this.property.id]);
  }


 applyProperty() {
  const userName = localStorage.getItem('userName');

  if (!userName) {
    alert('Login required');
    return;
  }

  this.isSubmitting = true;

  // Clone form values and add tenant username
  const payload = {
    ...this.bookingForm.value,
    tenant: userName
  };

  this.http.post(
    `http://localhost:8091/application/apply/${this.property.id}`,
    payload
  ).subscribe({
    next: () => {
      this.bookingSuccess = true;
      this.isSubmitting = false;
      this.bookingForm.reset();
    },
    error: err => {
      console.error(err);
      alert(err.error || 'Failed to send request');
      this.isSubmitting = false;
    }
  });
}



  get f() {
    return this.bookingForm.controls;
  }
}
