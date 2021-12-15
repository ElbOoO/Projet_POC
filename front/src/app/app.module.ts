import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgSelectModule } from '@ng-select/ng-select';
import { FormGroup, FormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { DpDatePickerModule } from 'ng2-date-picker';
import { MDBBootstrapModule } from 'angular-bootstrap-md';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CalendarComponent } from './calendar/calendar.component';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { HomePageComponent } from './home-page/home-page.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { DropdownuserComponent } from './dropdownuser/dropdownuser.component';

import { CalendarModule, DateAdapter } from 'angular-calendar';
import { adapterFactory } from 'angular-calendar/date-adapters/date-fns';
import { ProfileComponent } from './profile/profile.component';
 
const routes: Routes =[
  {path: '',redirectTo:'/home',pathMatch:'full'},
  {path: 'home',component:LoginPageComponent},
  {path: 'time',component:CalendarComponent},
  {path: 'user',component:ProfileComponent},
  ];


@NgModule({
  declarations: [
    AppComponent,
    CalendarComponent,
    NavBarComponent,
    HomePageComponent,
    LoginPageComponent,
    DropdownuserComponent,
    ProfileComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    NgSelectModule,
    DpDatePickerModule,
    MDBBootstrapModule.forRoot(),
    CalendarModule.forRoot({
      provide: DateAdapter,
      useFactory: adapterFactory,
    })
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
