import { Component, OnInit, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Property } from 'src/app/models/property';
import { PropertyService } from 'src/app/services/property.service';



@Component({
  selector: 'app-root',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  constructor(private router: Router) {}
  // Inject services
  private propertyService = inject(PropertyService);
  private fb = inject(FormBuilder);
  

  // State
  properties: Property[] = [];
  filteredProperties: Property[] = [];
  selectedProperty: Property | null = null;
  currentView: 'home' | 'login' | 'register' | 'forgot' | 'details' = 'home';


  // Filters
  searchTerm = '';
  typeFilter = 'All';
  priceSort = 'default';

  // Booking form
  bookingForm!: FormGroup;
  isSubmitting = false;
  bookingSuccess = false;

  ngOnInit() {
    this.loadProperties();
    // Initialize booking form
    this.bookingForm = this.fb.group({
      
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      date: ['', Validators.required]
    });

    
  }
  

loadProperties() {
  this.propertyService.getAll().subscribe({
    next: data => {
      console.log('API DATA:', data);
      this.properties = data;
       this.filteredProperties = [...data];
    },
    error: err => console.error('API ERROR:', err)
  });
}
  // Change view


  openDetails(property: Property) {
  this.router.navigate(['/property', property.id]);
}

  applyFilters() {
    let result = [...this.properties];

    // Text filter
    if (this.searchTerm) {
      const term = this.searchTerm.toLowerCase();
      result = result.filter(p =>
        p.title.toLowerCase().includes(term) ||
        p.location.toLowerCase().includes(term)
      );
    }

    // Type filter
    if (this.typeFilter !== 'All') {
      result = result.filter(p => p.type === this.typeFilter);
    }

    // Price sort
    if (this.priceSort === 'asc') {
      result.sort((a, b) => a.price - b.price);
    } else if (this.priceSort === 'desc') {
      result.sort((a, b) => b.price - a.price);
    }

    this.filteredProperties = result;
  }

  submitBooking() {
    if (this.bookingForm.valid) {
      this.isSubmitting = true;
      setTimeout(() => {
        this.isSubmitting = false;
        this.bookingSuccess = true;
      }, 1500);
    }
  }

  
}
