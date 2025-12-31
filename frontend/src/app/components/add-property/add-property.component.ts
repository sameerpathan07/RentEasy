import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Property } from 'src/app/models/property';
import { AuthService } from 'src/app/services/auth.service';
import { PropertyService } from 'src/app/services/property.service';

@Component({
  selector: 'app-add-property',
  templateUrl: './add-property.component.html',
  styleUrls: ['./add-property.component.css']
})
export class AddPropertyComponent {


  constructor(
    private propertyService: PropertyService,
    private router: Router,
    public auth:AuthService,
    private http:HttpClient
  ) {}

ngOnInit() {
  this.property.userName = this.auth.username!;
}

  

  property: any = {
  userName:'',
  title: '',
  type: 'Apartment',
  price: 0,
  location: '',
  bedrooms: 0,
  bathrooms: 0, 
  area: 0,
  description: '',
  available: true
};

selectedFile!: File;

onFileSelected(event: any) {
  this.selectedFile = event.target.files[0];
}

saveProperty() {
    const formData = new FormData();
    
  
    if (!this.selectedFile) {
      alert('Please select an image');
    return;
  }

  // image
  formData.append('image', this.selectedFile);

  // property JSON
  formData.append(
    'property',
    new Blob([JSON.stringify(this.property)], {
      type: 'application/json'
    })
  );
 

  this.http.post('http://localhost:8091/image/addProp', formData)
    .subscribe({
      next: () => {
        alert('Property Added Successfully');
        this.router.navigate(['/']);   // ✅ NOW WORKS
      },
      error: err => {
        console.error(err);
        alert('Failed to add property');
      }
    });
}



}
