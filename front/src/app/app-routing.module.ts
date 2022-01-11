import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CalendarComponent } from './calendar/calendar.component';
import { FormManagerComponent } from './form-manager/form-manager.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { ProfileComponent } from './profile/profile.component';

let routes: Routes =[
  {path: '',redirectTo:'/home',pathMatch:'full'},
  {path: 'home',component:LoginPageComponent},
  {path: 'manager',component:FormManagerComponent},
  {path: 'home',component:LoginPageComponent},
  {path: 'time',component:CalendarComponent},
  {path: 'user',component:ProfileComponent},
  ];

  /*
if(window.sessionStorage.getItem('role')==null){
  console.log('not connected')
  let routes: Routes =[
  
    //{path: '',redirectTo:'/home',pathMatch:'full'},
    {path: 'home',component:LoginPageComponent},
  ]
}

if(window.sessionStorage.getItem('role')=='User'){
  console.log('user connected')

  let routes: Routes =[
  
    //{path: '',redirectTo:'/home',pathMatch:'full'},
    {path: 'home',component:LoginPageComponent},
    {path: 'time',component:CalendarComponent},
    {path: 'user',component:ProfileComponent},
  ]
}

if(window.sessionStorage.getItem('user')=='manager'|| window.sessionStorage.getItem('user')=='admin' ){
    console.log('user connected')

  let routes: Routes =[
  
    //{path: '',redirectTo:'/home',pathMatch:'full'},
    {path: 'manager',component:FormManagerComponent},
    {path: 'home',component:LoginPageComponent},
    {path: 'time',component:CalendarComponent},
    {path: 'user',component:ProfileComponent},
  ]
}

*/



@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
