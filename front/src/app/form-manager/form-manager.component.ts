import { Component, OnInit } from '@angular/core';

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

  addproject(_projectName:string,_projectColor:string) {
    this.projects.push({nom:_projectName, couleur:_projectColor})
  }

  removeItem(index : number){
    this.projects.splice(index,1);
  }

}
