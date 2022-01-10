import { Injectable } from '@angular/core';


const ACCESS_TOKEN = 'access_token';


@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {

  constructor() { }


  signOut() {
    window.sessionStorage.clear();
  }

  public saveToken(name:string,token: string) {
    window.sessionStorage.removeItem(name);
    window.sessionStorage.setItem(name, token);
  }

  public getToken(name: string): string|null {
    return window.sessionStorage.getItem(name);
  }
  public removeAllToken(){
    window.sessionStorage.clear()

  }
}
