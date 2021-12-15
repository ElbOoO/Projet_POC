import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CalendarComponent } from './calendar/calendar.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { ProfileComponent } from './profile/profile.component';


const routes: Routes =[
  {path: '',redirectTo:'/home',pathMatch:'full'},
  {path: 'home',component:LoginPageComponent},
  {path: 'time',component:CalendarComponent},
  {path: 'user',component:ProfileComponent},
  ];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
