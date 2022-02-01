import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { TokenStorageService } from '../token-storage.service';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {

  constructor(private location: Location,private service:TokenStorageService) { }
  logged = false
  manager = false
  ngOnInit(): void {
  console.log(window.sessionStorage.getItem('access_token'))
  console.log("ID:"+window.sessionStorage.getItem('id')+" "+window.sessionStorage.getItem('username') +" "+ window.sessionStorage.getItem('role'))

    if(window.sessionStorage.getItem('role')!= null){
      this.logged =true
    }
    if(window.sessionStorage.getItem('role')== 'ROLE_Manager'|| window.sessionStorage.getItem('role')== 'ROLE_Admin'){
      this.manager =true
    }
    if(this.logged==false){
      this.location.go('/home') 
    }
  }
  

}
