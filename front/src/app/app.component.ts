import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { tap } from 'rxjs';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'my-app';
/*
  apiURL: string = 'https://jsonplaceholder.typicode.com/posts/1';
  products= [];*/

  constructor(/*private httpClient: HttpClient*/) {}

  ngOnInit(){
    //this.getProducts();
  }
/*
  public getContacts(url?: string){   

    return this.httpClient.get<any>(`${this.apiURL}`,
    { observe: 'response' }).pipe(tap(res => {
      return res;
    }));
  }

   getProducts() {
    this.products = [];
    this.getContacts().subscribe(res => {      
      this.products = res.body;
    });
  }*/
}

