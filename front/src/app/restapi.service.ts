import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' ,responseType:'text' as 'json'})
};


const AUTH_API = 'http://localhost:8080/auth/';

@Injectable({
  providedIn: 'root'
})
export class RestapiService {

  constructor(private http:HttpClient) { }

    /*public loginn(username:string,password:string){
      const headers= new HttpHeaders({Authorization: 'Basic '+ btoa(username+":"+password)})
      const body = '{"username": "' + username + '", "password": "' + password + '"}';
      return this.http.post("http://localhost:8080/auth/login",body)

      
      
      //return this.http.post("http://localhost:8080/auth/login",{headers,responseType:'text' as'json'})

    }*/
    
    login(username:string,password:string) {
      return this.http.post(AUTH_API + 'login', {
        username: username,
        password: password
      }, httpOptions);
    }

    public getProject(){
      let username="javatechie";
      let password="jt14"
      let token = window.sessionStorage.getItem('access_token') 
      let headers= new HttpHeaders()
      console.log(window.sessionStorage.getItem('access_token') || '{}') 
      let tokenParse = window.sessionStorage.getItem('access_token') || '{}'           
      headers.append('Authorization', `Bearer ${tokenParse}`);
      
      let httpopt = {
        headers: new HttpHeaders({ 'Authorization': 'Bearer '+ tokenParse ,responseType:'text' as 'json'})
      };
      return this.http.get<Projets>('http://localhost:8080/' +'projets',httpopt)

    }
}
interface Projets {
  nom: string;
  color: string;
}
