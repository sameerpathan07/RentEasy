import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { MainComponent } from './components/main/main.component';
import { ForgotPasswordComponent } from './components/forgot-password/forgot-password.component';
import { MyRentalsComponent } from './components/my-rentals/my-rentals.component';
import { AddPropertyComponent } from './components/add-property/add-property.component';
import { UpdatePropertyComponent } from './components/update-property/update-property.component';
import { PropertyDetailsComponent } from './components/property-details/property-details.component';
import { UpdateUserComponent } from './components/update-user/update-user.component';
import { ProfileComponent } from './components/profile/profile.component';
import { LandlordComponent } from './components/landlord/landlord.component';



const routes: Routes = [
  { path: '', component: MainComponent },        
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'forgot', component: ForgotPasswordComponent },
  { path: 'property/:id', component: PropertyDetailsComponent },
  { path: 'my-rentals/:tab', component: MyRentalsComponent },
  { path: 'add-property', component: AddPropertyComponent },
  { path: 'update-property/:id', component: UpdatePropertyComponent },
  { path: 'update-user', component: UpdateUserComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'landlord', component: LandlordComponent },
  { path: 'landlord/:tab', component: LandlordComponent },

  { path: '**', redirectTo: '' }  
   
  ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
