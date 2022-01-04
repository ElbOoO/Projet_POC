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

  public saveToken(token: string) {
    window.sessionStorage.removeItem(ACCESS_TOKEN);
    window.sessionStorage.setItem(ACCESS_TOKEN, token);
  }

  public getToken(): string|null {
    return sessionStorage.getItem(ACCESS_TOKEN);
  }
}
