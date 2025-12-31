import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NavbarComponent } from './components/navbar/navbar.component';
import { PropertyComponent } from './components/property/property.component';
import { MainComponent } from './components/main/main.component';
import { CurrencyPipe } from '@angular/common';

import { ForgotPasswordComponent } from './components/forgot-password/forgot-password.component';
import { MyRentalsComponent } from './components/my-rentals/my-rentals.component';
import { AddPropertyComponent } from './components/add-property/add-property.component';
import { UpdatePropertyComponent } from './components/update-property/update-property.component';
import { PropertyDetailsComponent } from './components/property-details/property-details.component';
import { UpdateUserComponent } from './components/update-user/update-user.component';
import { ProfileComponent } from './components/profile/profile.component';
import { LandlordComponent } from './components/landlord/landlord.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    NavbarComponent,
    PropertyComponent,
    MainComponent,
    ForgotPasswordComponent,
    MyRentalsComponent,
    AddPropertyComponent,
    UpdatePropertyComponent,
    PropertyDetailsComponent,
    UpdateUserComponent,
    ProfileComponent,
    LandlordComponent,
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule, 
    ReactiveFormsModule
  ],
 providers: [CurrencyPipe],
  bootstrap: [AppComponent]
})
export class AppModule { }
