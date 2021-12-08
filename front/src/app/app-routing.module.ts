import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CalendarComponent } from './calendar/calendar.component';
import { LoginPageComponent } from './login-page/login-page.component';


const routes: Routes =[
  {path: 'home',component:LoginPageComponent},
  {path: '',redirectTo:'/home',pathMatch:'full'},
  {path: 'user',component:CalendarComponent},
  
  ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
