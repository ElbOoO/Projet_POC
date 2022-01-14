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

  constructor(private http:HttpClient) {}  

  login(username:string,password:string) {
    return this.http.post<login>(AUTH_API + 'login', {
      username: username,
      password: password
    }, httpOptions);
  }
  // Gestion des Temps par l'API----------------------------------------------------------------------------
  public getTemps(id:number){
    let tokenParse = window.sessionStorage.getItem('access_token') || '{}'           
    let header = {
      headers: new HttpHeaders({ 'Authorization': 'Bearer '+ tokenParse ,responseType:'text' as 'json'})
    };
    return this.http.get<Temps[]>('http://localhost:8080/' +'temps/utilisateur='+id.toString(),header)
  }

  public postTemps(date:string,poids:number,user_id:number,project_id:number){
    let tokenParse = window.sessionStorage.getItem('access_token') || '{}'           
    let header = {
      headers: new HttpHeaders({ 'Authorization': 'Bearer '+ tokenParse ,responseType:'text' as 'json'})
    };
    const body = {"date": date,"poids": poids,"utilisateur": user_id,"projet": project_id}
    return this.http.post<Temps>('http://localhost:8080/' +'temps',body,header)
  }

  public deleteTemps(_id:number){
    let tokenParse = window.sessionStorage.getItem('access_token') || '{}'           
    let header = {
      headers: new HttpHeaders({ 'Authorization': 'Bearer '+ tokenParse ,responseType:'text' as 'json'})
    };

    return this.http.delete<Temps>('http://localhost:8080/' +'temps/'+_id.toString(),header)
  }


  // Gestion des Projets par l'API----------------------------------------------------------------------------
  public getProject(){
    let tokenParse = window.sessionStorage.getItem('access_token') || '{}'                 
    let header = {
      headers: new HttpHeaders({ 'Authorization': 'Bearer '+ tokenParse ,responseType:'text' as 'json'})
    };

    return this.http.get<Projet[]>('http://localhost:8080/' +'projets',header)
  }

  public postProject(_couleur:string,_nom:string,_manager_id:number){
    let tokenParse = window.sessionStorage.getItem('access_token') || '{}'           
    let header = {
      headers: new HttpHeaders({ 'Authorization': 'Bearer '+ tokenParse ,responseType:'text' as 'json'})
    };

    const body ={'id':1,'couleur':_couleur,'nom':_nom,'manager':_manager_id}
    return this.http.post<Projet>('http://localhost:8080/' +'projets',body,header)
  }
  
  public deleteProject(_id:number){
    let tokenParse = window.sessionStorage.getItem('access_token') || '{}'           
    let header = {
      headers: new HttpHeaders({ 'Authorization': 'Bearer '+ tokenParse ,responseType:'text' as 'json'})
    };

    return this.http.delete<Projet>('http://localhost:8080/' +'projets/'+_id.toString(),header)
  }

}

interface login{
  username:string,
  roles:string  
  access_token:string
}

interface Temps{
  id:number;
  date: string;
  poids: number;
  projet:Projet;

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
}

interface Error{
error:string
}