import { Component, OnInit } from '@angular/core';
import { RestapiService } from '../restapi.service';


interface Projets {
  nom: string;
  color: string;
}


@Component({
  selector: 'app-form-manager',
  templateUrl: './form-manager.component.html',
  styleUrls: ['./form-manager.component.css']
})
export class FormManagerComponent implements OnInit {
  

  projects: Array<{nom: string, couleur: string}> = [
  {nom: 'cassandra', couleur: '#ff0000'},
  {nom: 'n-tiers', couleur: '#00ff00'},
  {nom: 'PRI', couleur: '#0000ff'},
  ];


  popupAddProject=false;
  projectName:any;
  projectColor:any;
  constructor(private service:RestapiService) { }
  get_project() {
    
    this.service.getProject().subscribe(data=> {
      console.log(data)
      console.log(data[0])
      this.projects=data
      console.log(this.projects)

    })
    //this.http.get<Projets>('https://api.npms.io/v2/search?q=scope:angular').subscribe(data => {
    //  this.totalAngularPackages = data.total;
   // })


  }
  ngOnInit(): void {
    this.get_project()
  }
  
  addproject(_projectName:string,_projectColor:string) {
    this.projects.push({nom:_projectName, couleur:_projectColor})
  }

  removeItem(index : number){
    this.projects.splice(index,1);
 }


}
