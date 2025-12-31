import { HttpClient } from "@angular/common/http";
import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { Property } from "src/app/models/property";
import { AuthService } from "src/app/services/auth.service";

@Component({
  selector: 'app-update-property',
  templateUrl: './update-property.component.html',
  styleUrls: ['./update-property.component.css']
})
export class UpdatePropertyComponent implements OnInit {

  property?: Property;
  id!: number;
  selectedFile?: File;
  property1: any = {}

  constructor(
    private route: ActivatedRoute,
    private http: HttpClient,
    private router: Router,
    private auth:AuthService,
    
  ) {}

  ngOnInit(): void {

  
  this.property1.role = this.auth.username!;


    this.id = Number(this.route.snapshot.paramMap.get('id'));

    this.http.get<Property>(`http://localhost:8091/image/getImg/${this.id}`)
      .subscribe(data => {
        this.property = data;
      });
  }

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
  }

  updateProperty() {
    const formData = new FormData();

    if (this.selectedFile) {
      formData.append('image', this.selectedFile);
    }

    formData.append(
      'property',
      new Blob([JSON.stringify(this.property)], {
        type: 'application/json'
      })
    );

    this.http.put(
      `http://localhost:8091/image/updateImg/${this.id}`,
      formData
    ).subscribe({
      next: () => {
        alert('Property Updated Successfully');
        this.router.navigate(['/']);
      },
      error: err => {
        console.error(err);
        alert('Update failed');
      }
    });
  }
}
