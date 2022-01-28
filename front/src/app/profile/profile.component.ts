import { Component, OnInit } from '@angular/core';
import { RestapiService } from '../restapi.service';
import { TokenStorageService } from '../token-storage.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  constructor(private service:RestapiService,private token:TokenStorageService) {}

  ngOnInit(){
    this.currentUserId=Number(window.sessionStorage.getItem('id'))
    this.currentUserRole=window.sessionStorage.getItem('role')
    this.currentUserPass=window.sessionStorage.getItem('pass')
    this.selectedUserId=this.currentUserId
    this.getEventsfromapi();
  }

  currentUserId:any;
  currentUserPass:any;
  currentUserRole:any;
  currentUserData:any;

  rolesList:any= [
    "ROLE_Admin",
    "ROLE_Manager",
    "ROLE_User"
  ]
  usersList:any=[]
  managersList:any=[]
  selectedUserId:any

  popupNewUser:Boolean=false

  name : any;
  firstname : any;
  role : any;
  manager: any;
  password : any="";
  passconfirm: any="";

  //API functions ---------------------------------------------------------
  getEventsfromapi(){
    this.ApiShowProfile(this.currentUserId);
    this.service.getUsers().subscribe(data=> {// GET: list des users
      for (let i = 0; i < data.length; i++) {
        if(this.currentUserId==data[i].id){
          this.usersList = [...this.usersList, {nom:"(You) "+data[i].prenom+"."+data[i].nom,id:data[i].id}];
        }else{
          this.usersList = [...this.usersList, {nom:data[i].prenom+"."+data[i].nom,id:data[i].id}];
        }      
      }
    })
    this.service.getManagers().subscribe(data=> {// GET: list des users
      for (let i = 0; i < data.length; i++) {
        if(this.currentUserId==data[i].id){
          this.managersList = [...this.managersList, {nom:"(You) "+data[i].prenom+"."+data[i].nom,id:data[i].id}];
        }else{
          this.managersList = [...this.managersList, {nom:data[i].prenom+"."+data[i].nom,id:data[i].id}];
        }      
      }
    })
  }

  ApiShowProfile(_id:number){
    this.service.getUser(_id).subscribe(data=> {// GET: current user
      this.currentUserData=data;
      this.initProfile(this.currentUserData.nom,this.currentUserData.prenom,this.currentUserData.role,this.currentUserData.manager,this.currentUserPass,this.currentUserPass)
    })
  }

  ApiPatchProfile(_id:number,_nom:string,_prenom:string,_password:string,_role:string,_manager:number){
    this.service.patchUsers(_id,_nom,_prenom,_password,_role,_manager).subscribe(data=>{
      alert("Changes Saved !")
      if(this.currentUserId==_id){
        this.token.signOut()
        window.location.pathname = "/";
      }else {
        window.location.reload();
      } 
    },
    error => alert("Bad inputs / User already exists"))
  }

  ApiPostProfile(_nom:string,_prenom:string,_password:string,_role:string,_manager:number){
    this.service.postUsers(_nom,_prenom,_password,_role,_manager).subscribe(data=>{
      alert("User Added !")
      window.location.reload();

    },
    error => alert("Bad inputs / User already exists"))
  }

  ApiDeleteProfile(_id:number){
    this.service.deleteUsers(_id).subscribe(data=>{
      alert("User Deleted !")
      if(this.currentUserId==_id){ //Action empechée sur le boutton pour eviter des erreurs (comme enlever le dernier admin)s
        this.token.signOut()
        window.location.pathname = "/";
      }else {
        window.location.reload();
      } 
    },
    error => alert("Can't find the user to delete / Manager can't be deleted until it has users"))
  }
  //API --------------------------------------------------------------------
 
  refreshProfile(userId:number){
    this.selectedUserId=userId;
    this.ApiShowProfile(userId);
  }

 compare(password: string, passconfirm: string,isNewUser:Boolean): Boolean {
   if(isNewUser==true){

    if (password == passconfirm && password!="" && password!=null){
      //console.log("newusertrue")
      return true;
    }else{
      //console.log("newuserfalse")
      return false;
    }
   }else{

    if (password == passconfirm){
      //console.log("notnewusertrue")
      return true;
    }else{
      //console.log("notnewuserfalse")
      return false;
    }
   }

  }

  isEmpty(): Boolean {
    if (this.name=="" || this.firstname==""){
      //console.log("null")
      return true;
    }else{
      //console.log("pasnull")
      return false;
    }
  }

  initProfile(_name: string,_firstname: string,_role: string,_manager: string,_password: string,_passconfirm: string): void {
    this.name=_name;
    this.firstname=_firstname;
    this.role=_role;
    this.manager=_manager;
    // this.password=_password;
    // this.passconfirm=_passconfirm;
  }

  newUser(){
    this.popupNewUser=true;
    this.name="";
    this.firstname="";
    this.manager="";
    this.password="";
  }

  save(_nom:string,_prenom:string,_password:string,_role:string,_manager:number): void {
    this.ApiPatchProfile(this.selectedUserId,_nom,_prenom,_password,_role,_manager);
  }

  add(_nom:string,_prenom:string,_password:string,_role:string,_manager:number): void {
    this.ApiPostProfile(_nom,_prenom,_password,_role,_manager);
  }
  delete(): void {
    this.ApiDeleteProfile(this.selectedUserId);
  }

  roleChanged(){
    if (this.role!="ROLE_User") this.manager=null
  }
}
