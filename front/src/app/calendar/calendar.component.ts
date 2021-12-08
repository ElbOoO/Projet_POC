import { Component, OnInit } from '@angular/core';
import { CalendarView, CalendarEvent } from 'angular-calendar';
import { startOfDay } from 'date-fns';
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
  title = 'my-app';

  viewDate: Date = new Date();
  view: CalendarView = CalendarView.Week;
  CalendarView = CalendarView;
  
  setView(view: CalendarView) {
    this.view = view;
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

}
