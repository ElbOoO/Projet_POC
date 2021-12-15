import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  constructor() {
    
   }

  ngOnInit(): void {
    
  }

  name : any="thog";
  firstname : any="yeey";
  role : any="user";
  manager: any="moi";
  password : any="tt";
  passconfirm: any="tt";

 compare(password: string, passconfirm: string): Boolean {
    if (password == passconfirm){
      return true;
    }else{
      return false;
    }
  }

  checkrole(): String {
    if (this.role == "admin"){
      return "admin";
    }    
    else if (this.role == "manager"){
      return "manager";
    }else{
      return "user";
    }
  }

  save(_name: string,_firstname: string,_role: string,_manager: string,_password: string,_passconfirm: string): void {
    this.name=_name;
    this.firstname=_firstname;
    this.role=_role;
    this.manager=_manager;
    this.password=_password;
    this.passconfirm=_passconfirm;
    console.log("saved");
  }
}
