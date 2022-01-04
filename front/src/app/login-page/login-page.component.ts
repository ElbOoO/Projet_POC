import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
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


  constructor(private service:RestapiService) { }

  ngOnInit(): void {
  }
  doLogin(): string {

  let resp= this.service.login(this.username,this.password);
  console.log(resp)
  resp.subscribe(data=>{
  //  this.data=data;
   // console.log(this.data['access_token'])
    console.log(data)
    console.log(JSON.stringify(data))
    console.log(JSON.parse((JSON.stringify(data))).accessToken)
    window.sessionStorage.setItem('access_token',JSON.parse((JSON.stringify(data))).accessToken)
    
   // window.sessionStorage.setItem("auth-token",data.access_token)
  })
 return ''
  }
 

}




/*
import Grid from '@material-ui/core/Grid';
import test_img from "../../images/test.jpg"
import test_img2 from "../../images/piou.png"

class Login extends Component{
    constructor(props){
        super(props);
        this.state = { 
            isLogin: true
         }
    }*/