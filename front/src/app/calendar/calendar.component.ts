import { Component, OnInit } from '@angular/core';
import { CalendarView, CalendarEvent, CalendarEventTimesChangedEvent } from 'angular-calendar';//https://mattlewis92.github.io/angular-calendar/docs/components/CalendarWeekViewComponent.html DOC
import { startOfDay } from 'date-fns';
import { Subject } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { tap } from 'rxjs';

import jspdf from 'jspdf';
import 'jspdf-autotable';

@Component({
  selector: 'app-calendar',
  templateUrl: './calendar.component.html',
  styleUrls: ['./calendar.component.css']
})
export class CalendarComponent implements OnInit {
  constructor(private httpClient: HttpClient) {}

  ngOnInit(){
    this.getEvents();

    //test sans API----------------------------------
    this.PDF_userDateList.push(new Date("2021-12-12"))
    this.PDF_userDateList.push(new Date("2022-01-02"))
    this.PDF_userDateList.push(new Date("2022-01-04"))
    this.PDF_userDateList.push(new Date("2022-01-06"))
  }

  //API functions ----------------------------------------------------------
  apiURL: string = 'https://jsonplaceholder.typicode.com/posts';
  public getApiData(url?: string){   

    return this.httpClient.get<any>(`${this.apiURL}`,
    { observe: 'response' }).pipe(tap(res => {
      return res;
    }));
  }

  getEvents() {
    this.getApiData().subscribe(res => { 
      for (let i = 0; i < 4/*res.body.length*/; i++) {
        this.projectsList.push(res.body[i].title);// GET: list des projets
        this.initEvent(res.body[i].title,"2021-12-24","0.5"/*,'#00FF00'*/); // GET : infos sur temps
        //Lists pour creation PDF
        this.PDF_userProjectsList.push(res.body[i].title)
        //this.PDF_userDateList.push(new Date())
        this.PDF_userWeightList.push("0.5")
      }
    });
  }
  //-------------------------------------------------------------------------

  popupAddTime = false
  popupExportPDF = false;
  selectedProject: any;
  selectedTime: any;
  selectedDate : any;
  viewDate: Date = new Date();
  view: CalendarView = CalendarView.Week;
  CalendarView = CalendarView;
  refresh = new Subject<void>();
  events: CalendarEvent[] = []
  projectsList:any =[];
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

  refreshView(): void {
    this.refresh.next();
  }
  setView(view: CalendarView) {
    this.view = view;
    this.refresh.next();
  }
  initEvent(projet: string,date: string,time: any,/*color:any*/): void {
    this.events = [
      ...this.events,
      {
        title: projet /*+"\n Poids :"+ time*/,
        start: startOfDay(new Date(date)),
        end: addDate(new Date(date),time),
        /*color:  {
          primary: '#FFFFFF',
          secondary: color,
        },*/
      },
    ];
  }

  addEvent(projet: string,date: string,time: any): void {
    this.events = [
      ...this.events,
      {
        title: projet,
        start: startOfDay(new Date(date)),
        end: addDate(new Date(date),time),
      },
    ];
    //perform post & f5
  }

  deleteEvent(eventToDelete: CalendarEvent) {
    this.events = this.events.filter((event) => event !== eventToDelete);
    //perform delete & f5
  }
  //Gestion PDF-------------------------------------------------------------------------
  //TODO verouiiler les temps exportés
  PDF_userProjectsList:any = []
  PDF_userDateList:any = []
  PDF_userWeightList:any = []
  PDF_startingDate: any;
  PDF_endingDate: any;
  createPdf(startingDate:Date,endingDate:Date) { //HELP : https://www.freakyjolly.com/angular-jspdf-autotable-tutorial/
    var PDF_head = [['Project','Time','Weight']];
    var PDF_body=[];
    var PDF_total = [];
    var doc = new jspdf();
    PDF_total=concatenateTab([],this.PDF_userProjectsList,this.PDF_userDateList,this.PDF_userWeightList)
    for (let i = 0; i < this.PDF_userDateList.length; i++) {
      if (PDF_total[i][1]>=new Date(startingDate) && PDF_total[i][1]<=new Date(endingDate)){
        PDF_body.push(PDF_total[i]);
      }
    }

    (doc as any).autoTable({
      head: PDF_head,
      body: PDF_body,
      theme: 'grid', //striped,plain,grid
    })
    doc.output('dataurlnewwindow'); // Open PDF document in new tab
  }
  //--------------------------------------------------------------------------------------
}


function addDate(date: Date, hour: any) {
  date.setTime(date.getTime() + ((hour*8-1)*60*60000));
  return date;
}

function concatenateTab(finalTab:any,tab1:any,tab2:any,tab3:any){
  for (let i = 0; i < tab1.length; i++) {
    finalTab.push([tab1[i],tab2[i],tab3[i]])
  }
  return finalTab;
}