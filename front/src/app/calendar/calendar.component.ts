import { Component, OnInit } from '@angular/core';
import { CalendarView, CalendarEvent, CalendarEventTimesChangedEvent } from 'angular-calendar';
import { startOfDay } from 'date-fns';
import { range, Subject } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { tap } from 'rxjs';
import { CalendarPreviousViewDirective } from 'angular-calendar/modules/common/calendar-previous-view.directive';

//https://mattlewis92.github.io/angular-calendar/docs/components/CalendarWeekViewComponent.html DOC
@Component({
  selector: 'app-calendar',
  templateUrl: './calendar.component.html',
  styleUrls: ['./calendar.component.css']
})
export class CalendarComponent implements OnInit {
  constructor(private httpClient: HttpClient) {}

  ngOnInit(){
    this.getEvents();
  }

  //API functions ---------------------------------------------------------
  apiURL: string = 'https://jsonplaceholder.typicode.com/posts';
  public getApiData(url?: string){   

    return this.httpClient.get<any>(`${this.apiURL}`,
    { observe: 'response' }).pipe(tap(res => {
      return res;
    }));
  }

  getEvents() {
    this.getApiData().subscribe(res => { 
      for (let i = 0; i < 5/*res.body.length*/; i++) {
        this.projets.push(res.body[i].title);//initialisation projet
        this.initEvent(res.body[i].title,"2021-12-24","0.5"/*,'#00FF00'*/);
      }
    });
  }
  //API --------------------------------------------------------------------


  selectedProject: any;
  selectedTime: any;
  popup = false
  selectedDate : any;
  dateOutForm :any;
  viewDate: Date = new Date();
  view: CalendarView = CalendarView.Week;
  CalendarView = CalendarView;
  refresh = new Subject<void>();
  events: CalendarEvent[] = []
  projets:any =[];
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
        title: "Projet : "+projet /*+"\n Poids :"+ time*/,
        start: startOfDay(new Date(date)),
        end: addDate(new Date(date),time),
        /*color:  {
          primary: '#FFFFFF',
          secondary: color,
        },*/
      },
    ];
    //console.log(new Date(date).getHours()+time*8);
  }

  addEvent(projet: string,date: string,time: any): void {
    this.events = [
      ...this.events,
      {
        title: "Projet : "+projet,
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
}

function addDate(date: Date, hour: any) {
  date.setTime(date.getTime() + ((hour*8-1)*60*60000));
  return date;
}