import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Router } from '@angular/router';
import { Property } from 'src/app/models/property';

@Component({
  selector: 'app-property',
  templateUrl: './property.component.html',
  styleUrls: ['./property.component.css']
})
export class PropertyComponent {

@Input({ required: true }) property!: Property;
 @Output() viewDetails = new EventEmitter<Property>();
  

  
openDetails() {
    this.viewDetails.emit(this.property);
   
  }
  constructor(private router: Router) {}

goToDetails(id: number) {
  this.router.navigate(['/property', id]);
}

}
