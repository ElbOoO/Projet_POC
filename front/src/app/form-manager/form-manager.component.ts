import { Component, OnInit } from '@angular/core';
import { RestapiService } from '../restapi.service';


interface Projets {
  nom: string;
  color: string;
}


const projects: Array<{nom: string, couleur: string}> = [
  {nom: 'cassandra', couleur: '#ff0000'},
  {nom: 'n-tiers', couleur: '#ff0000'},
  {nom: 'PRI', couleur: '#ff0000'},
];

@Component({
  selector: 'app-form-manager',
  templateUrl: './form-manager.component.html',
  styleUrls: ['./form-manager.component.css']
})
export class FormManagerComponent implements OnInit {
  projects: Array<{nom: string, couleur: string}> = [
    {nom: 'cassandra', couleur: '#ff0000'},
    {nom: 'n-tiers', couleur: '#ff0000'},
    {nom: 'PRI', couleur: '#ff0000'},
  ];

   movies = [
    {name:'nosql'},
    {name:'PRI',},
    {name:'ntiers',}
    
    
    ]
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


  }

}
