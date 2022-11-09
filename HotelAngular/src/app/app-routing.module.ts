import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/registration/register.component';

import { AuthGuardService } from './utils/auth-check.guard';
import { RestrictAccessGuard } from './utils/restrict-access.guard';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { FormsModule } from "@angular/forms";
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { BookingdetailsComponent } from './bookingdetails/bookingdetails.component';
import { RoomComponent } from './room/room.component';



const routes: Routes = [
  { path: "", pathMatch: "full", redirectTo: "/registerUser" },
  { path: "registerUser", component: RegisterComponent },
  //{ path: "", pathMatch: "full", redirectTo: "/login" },
  { path: "login", component: LoginComponent, canActivate: [RestrictAccessGuard] },
  { path: "home", component: HomeComponent, canActivate: [AuthGuardService] },
  
  {path:'room', component:RoomComponent},
  {path:'bookingdetails', component:BookingdetailsComponent},


];

@NgModule({
  imports: [
    RouterModule.forRoot(routes),
    MatDatepickerModule,
    MatNativeDateModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
  ],
  exports: [
    RouterModule,
    MatDatepickerModule,
    MatNativeDateModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
  ]
})
export class AppRoutingModule { }
