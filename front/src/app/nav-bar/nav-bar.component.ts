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

  ngOnInit(): void {
  console.log(window.sessionStorage.getItem('access_token'))
  console.log(window.sessionStorage.getItem('role'))
    if(window.sessionStorage.getItem('role')!= null){

      this.logged =true
    }
    if(window.sessionStorage.getItem('role')== 'Manager'){
      this.manager =true
      
    }
    if(this.logged==true && this.location.isCurrentPathEqualTo('/home')){
     // this.location.go('/time')
     
    }
    if(this.logged==false){
      this.location.go('/home')
     
    }


  }
  
  logged = false
  manager = false

/* deconnection(){

  window.sessionStorage.setItem('access_token','')
  window.sessionStorage.setItem('role','')
  console.log(  window.sessionStorage.getItem('role')
  )
  
 }
 */
}
