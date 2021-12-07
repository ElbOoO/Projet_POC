import { Component, Input, OnInit } from '@angular/core';


@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent implements OnInit {

  constructor() { }
  Login:string='';
  Pass:string='';
  Confirm:string='';
  ngOnInit(): void {
  }

  validate():void{
    if(this.Pass==this.Confirm)
    alert("log success")
    else alert("log failed")
    console.log("sudo hack --password :  '" + this.Pass+"'")

    this.Login='';
    this.Pass="";
    this.Confirm="";
  }
}
