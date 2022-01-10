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
      return this.http.post<login>(AUTH_API + 'login', {
        username: username,
        password: password
      }, httpOptions);
    }



    public post_temps(date:string,poids:string,user_id:string,project_id:string){

      let token = window.sessionStorage.getItem('access_token') 
      let headers= new HttpHeaders()
      console.log(window.sessionStorage.getItem('access_token') || '{}') 
      let tokenParse = window.sessionStorage.getItem('access_token') || '{}'           
      headers.append('Authorization', `Bearer ${tokenParse}`);
      
      let httpopt = {
        headers: new HttpHeaders({ 'Authorization': 'Bearer '+ tokenParse ,responseType:'text' as 'json'})
      };


      const body ={'date':'2022-01-08','poids':'0.5','utilisateurId':'4','projetId':'2' }

      
      return this.http.post<Error>('http://localhost:8080/' +'temps',body,httpopt)

    }

    public getProject(){
     
      let token = window.sessionStorage.getItem('access_token') 
      let headers= new HttpHeaders()
      console.log(window.sessionStorage.getItem('access_token') || '{}') 
      let tokenParse = window.sessionStorage.getItem('access_token') || '{}'           
      headers.append('Authorization', `Bearer ${tokenParse}`);
      
      let httpopt = {
        headers: new HttpHeaders({ 'Authorization': 'Bearer '+ tokenParse ,responseType:'text' as 'json'})
      };
      return this.http.get<Projet[]>('http://localhost:8080/' +'projets',httpopt)

    }

    public getTemps(id:number){
      
      let token = window.sessionStorage.getItem('access_token') 
      let headers= new HttpHeaders()
      console.log(window.sessionStorage.getItem('access_token') || '{}') 
      let tokenParse = window.sessionStorage.getItem('access_token') || '{}'           
      headers.append('Authorization', `Bearer ${tokenParse}`);
      
      let httpopt = {
        headers: new HttpHeaders({ 'Authorization': 'Bearer '+ tokenParse ,responseType:'text' as 'json'})
      };
      return this.http.get<Temps[]>('http://localhost:8080/' +'temps/utilisateur='+id.toString(),httpopt)

    }

}

interface login{
roles:string  
access_token:string

}

interface Temps{
  date: string;
  poids: number;
  //projet_id:string;

}


interface Projets {
  Projet: Projet[]
}

interface Projet {
  id:number;
  nom: string;
  manager: manager;
  couleur: string

}

interface manager {
id : number
nom:string
prenom:string
password:string
role:string
username:string
//'#0000ff'

}

interface Error{
error:string


}