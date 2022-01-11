import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { tap } from 'rxjs';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  constructor(private httpClient: HttpClient) {}

  ngOnInit(){
    this.getEvents();
  }

  //API functions ---------------------------------------------------------
  apiURL: string = 'https://jsonplaceholder.typicode.com/posts/1';
  products:any= [];
  public getApiData(url?: string){   

    return this.httpClient.get<any>(`${this.apiURL}`,
    { observe: 'response' }).pipe(tap(res => {
      return res;
    }));
  }

  getEvents() {
    this.getApiData().subscribe(res => { 
      for (let i = 0; i < 5/*res.body.length*/; i++) {
        this.products.push(res.body.title);//initialisation projet
        this.initProfile(res.body.title,"yeey","admin","moi","tt","tt");
      }
    });
  }
  //API --------------------------------------------------------------------
  name : any;
  firstname : any;
  role : any;
  manager: any;
  password : any;
  passconfirm: any;


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

  initProfile(_name: string,_firstname: string,_role: string,_manager: string,_password: string,_passconfirm: string): void {
    this.name=_name;
    this.firstname=_firstname;
    this.role=_role;
    this.manager=_manager;
    this.password=_password;
    this.passconfirm=_passconfirm;
  }

  save(_name: string,_firstname: string,_role: string,_manager: string,_password: string,_passconfirm: string): void {
    this.name=_name;
    this.firstname=_firstname;
    this.role=_role;
    this.manager=_manager;
    this.password=_password;
    this.passconfirm=_passconfirm;
    //console.log("saved");
    //put and refresh the page
  }
}
