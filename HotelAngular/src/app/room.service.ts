import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Book } from './book';

@Injectable({
  providedIn: 'root'
})
export class RoomService {

  constructor(private http:HttpClient) { }

  API = 'http://localhost:8081'
  


  public getRooms(){
    return this.http.get(this.API + '/getdata');
  }

  public bookRoomFromRemote(book :Book):Observable<any>{
    return this.http.post<any>("http://localhost:9092/bookuser",book)
  }


  
}
