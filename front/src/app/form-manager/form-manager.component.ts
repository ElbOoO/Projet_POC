import { Component, OnInit } from '@angular/core';
import { RestapiService } from '../restapi.service';

@Component({
  selector: 'app-form-manager',
  templateUrl: './form-manager.component.html',
  styleUrls: ['./form-manager.component.css']
})

export class FormManagerComponent implements OnInit {

  projects: Array<{id:number,nom: string, couleur: string}> = [];
  popupAddProject=false;
  inputName:any;
  inputColor:any;
  
  constructor(private service:RestapiService) { }
  ngOnInit(): void {
    this.get_project()
  }

  //API functions ----------------------------------------------------------
  get_project() { 
    this.service.getProject().subscribe(data=> {
      this.projects=data
    })
  }

  postProject(couleur: string,nom: string,manager_id:number){
    this.service.postProject(couleur,nom,manager_id).subscribe(data=>{

    }) 
  }

  deleteProject(index:number){
    this.service.deleteProject(index).subscribe(data=>{

    }) 
  }
  //-------------------------------------------------------------------------


  addproject(_projectName:string,_projectColor:string) {
    this.projects.push({id:1,nom:_projectName, couleur:_projectColor})
    this.postProject(_projectColor,_projectName,1);
    //TODO IDmanager------------------------------
    window.location.reload();
  }

  removeItem(index : number){
    this.deleteProject(this.projects[index].id);
    this.projects.splice(index,1);
    window.location.reload();
 }


}
