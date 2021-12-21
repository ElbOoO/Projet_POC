import { Component, OnInit } from '@angular/core';
import { CalendarView, CalendarEvent, CalendarEventTimesChangedEvent } from 'angular-calendar';
import { startOfDay } from 'date-fns';
import { Subject } from 'rxjs';
import {NgSelectModule, NgOption} from '@ng-select/ng-select';
import { DatePipe } from '@angular/common';

const colors: any = {
  red: {
    primary: '#ad2121',
    secondary: '#FAE3E3',
  },
  blue: {
    primary: '#1e90ff',
    secondary: '#D1E8FF',
  },
  yellow: {
    primary: '#e3bc08',
    secondary: '#FDF1BA',
  },
};
//https://mattlewis92.github.io/angular-calendar/docs/components/CalendarWeekViewComponent.html DOC
@Component({
  selector: 'app-calendar',
  templateUrl: './calendar.component.html',
  styleUrls: ['./calendar.component.css']
})
export class CalendarComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }
  selectedProject: any;
  selectedTime: any;
  popup = false
  cities = [
    {id:1,name: 'Vilnius'},
    {id:2,name: 'Kaunas'},
    {id:3,name: 'Pavilnys'},
    {id:4,name: 'Pabradė'},
    {id:5,name: 'Klaipėda'}
];
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


  
  selectedDate : any;
  dateOutForm :any;
  viewDate: Date = new Date();
  view: CalendarView = CalendarView.Week;
  CalendarView = CalendarView;
  
  refresh = new Subject<void>();

  refreshView(): void {
    this.refresh.next();
  }
  setView(view: CalendarView) {
    this.view = view;
    this.refresh.next();
  }
 
 events: CalendarEvent[] = [
    {
      start: startOfDay(new Date("December 06, 2021 23:15:00")),
      title: 'First event',
      end: new Date("December 06, 2021 12:15:00"),
    },
    {
      start: new Date('2021-12-09T10:30:00'),
      end: new Date('2021-12-10T11:30:00'),
      title: 'Second event',
    }
  ]

  addEvent(projet: string,date: string,time: any): void {
    this.events = [
      ...this.events,
      {
        title: "Projet : "+projet +"\n Poids :"+ time,
        start: startOfDay(new Date(date)),
        end: addDate(new Date(date),time),
        color: colors.red,
      },
    ];
    console.log(new Date(date).getHours()+time*8);
  }

  deleteEvent(eventToDelete: CalendarEvent) {

    this.events = this.events.filter((event) => event !== eventToDelete);
  }
}
function addDate(date: Date, hour: any) {
  date.setTime(date.getTime() + ((hour*8-1)*60*60000));
  return date;
}