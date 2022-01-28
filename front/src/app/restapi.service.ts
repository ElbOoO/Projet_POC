import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

const AUTH_OPTIONS = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' ,responseType:'text' as 'json'})
};
const AUTH_API = 'http://localhost:8080/auth/';
const LINK_API = 'http://localhost:8080/';

@Injectable({
  providedIn: 'root'
})
export class RestapiService {

  constructor(private http:HttpClient) {}  

  login(username:string,password:string) {
    return this.http.post<login>(AUTH_API + 'login', {username: username,password: password}, AUTH_OPTIONS);
  }

  makeHeader(){
    let tokenParse = window.sessionStorage.getItem('access_token') || '{}'           
    let header = {
      headers: new HttpHeaders({ 'Authorization': 'Bearer '+ tokenParse ,responseType:'text' as 'json'})
    };
    return header;
  }

  // Gestion des Users par l'API----------------------------------------------------------------------------
  public getUsers(){
    return this.http.get<User[]>(LINK_API +'personnes',this.makeHeader())
  };

  public getUser(_id:number){
    return this.http.get<User[]>(LINK_API +'personnes/'+_id,this.makeHeader())
  };

  public getUsersManager(_id:number){
    return this.http.get<User[]>(LINK_API +'personnes/manager='+_id,this.makeHeader())
  };

  public getManagers(){
    return this.http.get<User[]>(LINK_API +'personnes/managers',this.makeHeader())
  };

  public postUsers(nom:string,prenom:string,password:string,role:string,manager:number){
    const body = {"nom": nom,"prenom": prenom,"password": password,"role": role,"manager": manager}
    console.log(body)
    return this.http.post<User>(LINK_API +'personnes',body,this.makeHeader())
  };

  public deleteUsers(_id:number){
    return this.http.delete<User>(LINK_API +'personnes/'+_id.toString(),this.makeHeader())
  };

  public patchUsers(id:number,nom:string,prenom:string,password:string,role:string,manager:number){
    var body;
    if (password=="" || password==null){
      body = {"id":id,"nom": nom,"prenom": prenom,"role": role,"manager": manager}
    }else{
      body = {"id":id,"nom": nom,"prenom": prenom,"password": password,"role": role,"manager": manager}
    }
    
    return this.http.patch<User>(LINK_API +'personnes',body,this.makeHeader())
  };

  // Gestion des Temps par l'API----------------------------------------------------------------------------
  public getTemps(id:number){
    return this.http.get<Temps[]>(LINK_API +'temps/utilisateur='+id.toString(),this.makeHeader())
  };

  public postTemps(date:string,poids:number,user_id:number,project_id:number){
    const body = {"date": date,"poids": poids,"utilisateur": user_id,"projet": project_id}
    return this.http.post<Temps>(LINK_API +'temps',body,this.makeHeader())
  };

  public deleteTemps(_id:number){
    return this.http.delete<Temps>(LINK_API +'temps/'+_id.toString(),this.makeHeader())
  };

  // Gestion des Projets par l'API----------------------------------------------------------------------------
  public getProject(){
    return this.http.get<Projet[]>(LINK_API +'projets',this.makeHeader())
  };

  public postProject(_couleur:string,_nom:string,_manager_id:number){
    const body ={'id':1,'couleur':_couleur,'nom':_nom,'manager':_manager_id}
    return this.http.post<Projet>(LINK_API +'projets',body,this.makeHeader())
  };
  
  public deleteProject(_id:number){
    return this.http.delete<Projet>(LINK_API +'projets/'+_id.toString(),this.makeHeader())
  };

}

interface login{
  username:string,
  roles:string  
  access_token:string,
  id:string
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

interface User{
  id: number,
  prenom:string
  nom:string
  role:string
  manager: number
}

interface Error{
error:string
}