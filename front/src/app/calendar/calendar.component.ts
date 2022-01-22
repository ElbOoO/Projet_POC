import { Component, OnInit } from '@angular/core';
import { CalendarView, CalendarEvent } from 'angular-calendar';//https://mattlewis92.github.io/angular-calendar/docs/components/CalendarWeekViewComponent.html DOC
import { startOfDay } from 'date-fns';

import jspdf from 'jspdf';
import 'jspdf-autotable';
import { RestapiService } from '../restapi.service';

@Component({
  selector: 'app-calendar',
  templateUrl: './calendar.component.html',
  styleUrls: ['./calendar.component.css']
})


export class CalendarComponent implements OnInit {
  constructor(private service:RestapiService) {}
  ngOnInit(){
    this.currentUserId=Number(window.sessionStorage.getItem('id'))
    this.currentUserRole=window.sessionStorage.getItem('role')
    this.selectedUserId=this.currentUserId //initialize the ID of the current user
    this.getEventsfromapi();
  }

  //manager id : http://localhost:8080/personnes/manager=1
  usersOfManager:any= []
  currentUserId:any;
  currentUserRole:any;

  popupAddTime = false;
  popupExportPDF = false;

  inputProject: any;
  inputTime: any;
  inputDate : any;
  selectedUserId : any;

  viewDate: Date = new Date();
  view: CalendarView = CalendarView.Week;
  CalendarView = CalendarView;
  events: CalendarEvent[] = []
  formattedEvents: any = []
  projectsListName:any=[];
  projectsListId:any=[];
  tempsListId:any=[];

  PdfData:any= []
  times = [
    {time: 0.125},
    {time: 0.25},
    {time: 0.375},
    {time: 0.5},
    {time: 0.625},
    {time: 0.75},
    {time: 0.875},
    {time: 1}
  ];

  //API functions ----------------------------------------------------------
  getEventsfromapi(){
    this.service.getProject().subscribe(data=> {// GET: list des projets
      for (let i = 0; i < data.length; i++) {
        this.projectsListName.push(data[i].nom);
        this.projectsListId.push(data[i].id);
      }
    })

    if(this.currentUserRole=='ROLE_Manager'){
          this.service.getUsers().subscribe(data=> {// GET: list des users d'un manager
          for (let i = 0; i < data.length; i++) {
            if(this.currentUserId==data[i].id){ //Self time management
              this.usersOfManager = [...this.usersOfManager, {nom:"(You) "+data[i].prenom+"."+data[i].nom,id:data[i].id}];
            }
            if(this.currentUserId==data[i].manager){//Your users time management
              //this.usersOfManager = [...this.usersOfManager, data[i]];
              this.usersOfManager = [...this.usersOfManager, {nom:data[i].prenom+"."+data[i].nom,id:data[i].id}];
            }
          }
        })
    }

    this.service.getTemps(this.selectedUserId).subscribe(data=> {// GET: list des temps
      for (let i = 0; i < data.length; i++) {
        this.initEvent(data[i].projet.nom,data[i].date,data[i].poids,data[i].projet.couleur)
        this.tempsListId.push(data[i].id);
        this.PdfData.push({nom:data[i].projet.nom,date:data[i].date,poids:data[i].poids});
      }
    })
  }
  
  ApiPostTemps(date:string,poids:number,user_id:number,project_id:number){
    this.service.postTemps(date,poids,user_id,project_id).subscribe(data=>{
    })
  }

  ApiDeleteTemps(id:number){
    this.service.deleteTemps(id).subscribe(data=>{
    })
  }
  //-------------------------------------------------------------------------

  //Calendar functions ------------------------------------------------------

  refreshCalendar(userId:number){
    this.selectedUserId=userId;
    this.resetData();
    this.getEventsfromapi();
  }

  resetData(){
    this.projectsListName=[];
    this.projectsListId=[];
    this.usersOfManager =[];
    this.tempsListId=[];
    this.PdfData=[];
    this.events = []
    this.formattedEvents=[]
  }

  getProjectId(projet:string){
    for (let i = 0; i < this.projectsListName.length; i++) {
      if (this.projectsListName[i]==projet){
        return this.projectsListId[i];
      }
    }
  }

  getEventId(index:number){
    return this.tempsListId[index];
  }

  getUserbyId(id:number){
    for (let i = 0; i < this.usersOfManager.length; i++) {
      if (this.usersOfManager[i].id==id){
        return this.usersOfManager[i].nom;
      }
    }
    return "user not found";
  }

  initEvent(projet: string,date: string,time: any,color:any): void {
    this.events = [
      ...this.events,
      {
        title: projet,
        start: startOfDay(new Date(date)),
        end: addDate(new Date(date),time),
        color:  {
          primary: '#FFFFFF',
          secondary: color,
        },
      },
    ];
    this.formattedEvents =[...this.formattedEvents, {project: projet, date: date}]
    console.log(this.formattedEvents)
  }

  postEvent(projet: string,date: string,time: any): void {
    this.ApiPostTemps(date,time,this.selectedUserId,this.getProjectId(projet));
    window.location.reload();
  }

  deleteEvent(eventToDelete: CalendarEvent,index:number) {
    this.ApiDeleteTemps(this.getEventId(index));
    this.events = this.events.filter((event) => event !== eventToDelete);
    window.location.reload();
  }

  //-------------------------------------------------------------------------

  //Gestion PDF--------------------------------------------------------------
  PDF_startingDate: any;
  PDF_endingDate: any;

  convert(startingDate:Date,endingDate:Date) {
    var doc = new jspdf();
    
    var PDF_head = [['Project','Date','Weight']];
    var PDF_body:any=[];

    this.PdfData.forEach((element: { nom: any; date: any; poids: any; }) => {      
      var temp = [element.nom,element.date,element.poids];
      if (new Date(element.date)>=new Date(startingDate) && new Date(element.date)<=new Date(endingDate)){
        PDF_body.push(temp);
      }
    });       
    doc.setFontSize(16)
    doc.text("Temps de "+this.getUserbyId(this.selectedUserId)+" (du "+startingDate+" au "+endingDate+")", 15, 10);
    (doc as any).autoTable({
      head: PDF_head,
      body: PDF_body,
      theme: 'grid', //striped,plain,grid
    })
    // doc.save('Test.pdf');
    doc.output('dataurlnewwindow');


  }
  //-------------------------------------------------------------------------

}

function addDate(date: Date, hour: any) {
  date.setTime(date.getTime() + ((hour*8-1)*60*60000));
  return date;
}

