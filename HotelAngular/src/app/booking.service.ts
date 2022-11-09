import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class BookingService {

  constructor(private http:HttpClient) { }

  
  API1 = 'http://localhost:9092'


  

  public getRooms1(){
    return this.http.get(this.API1 + '/getrooms');
  }


  public deletebooking(id: string){
    return this.http.delete(this.API1 + '/deletebooking?id='  +id);
  }
}
