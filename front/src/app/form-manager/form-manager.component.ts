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
  constructor() { }
  ngOnInit(): void {
  setTimeout(() => { this.ngOnInit() }, 1000 * 10)}

  projects: Array<{nom: string, couleur: string}> = [
  {nom: 'cassandra', couleur: '#ff0000'},
  {nom: 'n-tiers', couleur: '#00ff00'},
  {nom: 'PRI', couleur: '#0000ff'},
  ];


  popupAddProject=false;
  projectName:any;
  projectColor:any;
  constructor(private service:RestapiService) { }
  addproject() {
    
    this.service.getProject().subscribe(data=> {

      console.log(data)

    })
    //this.http.get<Projets>('https://api.npms.io/v2/search?q=scope:angular').subscribe(data => {
    //  this.totalAngularPackages = data.total;
   // })

    projects.push({nom:'a', couleur:'toto'})
    console.log(projects.toString())
  }
  ngOnInit(): void {
    setTimeout(() => { this.ngOnInit() }, 1000 * 10)


  addproject(_projectName:string,_projectColor:string) {
    this.projects.push({nom:_projectName, couleur:_projectColor})
  }

  removeItem(index : number){
    this.projects.splice(index,1);
  }

}
