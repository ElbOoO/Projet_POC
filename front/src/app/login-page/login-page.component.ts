import { Component, OnInit } from '@angular/core';
import { RestapiService } from '../restapi.service';
import { TokenStorageService } from '../token-storage.service';

interface responseauth {
id:number
username:string
roles:Array<1>
access_token:string
token_type:string
}

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {

  username:string='';
  password:string='';
  message:any;
  //response: responseauth;

  constructor(private service:RestapiService,private tokenservice:TokenStorageService) { }
  ngOnInit(): void {
    if(window.sessionStorage.getItem('role')!= null){
      this.logged =true
    }
  }

  doLogin(): string {
  let resp= this.service.login(this.username,this.password);
  console.log(resp)
  resp.subscribe(data=>{
    //  this.data=data;
    // console.log(this.data['access_token'])
      console.log(data)
      console.log(JSON.parse((JSON.stringify(data))).accessToken)
      window.sessionStorage.setItem('access_token',JSON.parse((JSON.stringify(data))).accessToken)
      window.sessionStorage.setItem('role',data.roles)
      console.log(window.sessionStorage.getItem('role'))
      window.location.reload();
    // window.sessionStorage.setItem("auth-token",data.access_token)
    })
  return ''
  }

  deconnection(){
    window.sessionStorage.removeItem('access_token')
    window.sessionStorage.removeItem('role')
    window.location.reload();
  }
 logged = false
}