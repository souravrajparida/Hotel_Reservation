import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BookingService } from '../booking.service';
import { RoomService } from '../room.service';

@Component({
  selector: 'app-bookingdetails',
  templateUrl: './bookingdetails.component.html',
  styleUrls: ['./bookingdetails.component.css']
})
export class BookingdetailsComponent implements OnInit {
  bookingDetails=null as any;
  


  constructor(private bookingService: BookingService, private _router : Router) { 
    this.getBookingDetails();

  }
  getBookingDetails(){
    this.bookingService.getRooms1().subscribe(
      (resp)=>{
        console.log(resp);
        this.bookingDetails = resp;
      },
      (err)=>{
        console.log(err);
      }
    );
  }
 
  

  ngOnInit(): void {
  }


  deletebooking(booking: { id: string; }){
    this.bookingService.deletebooking(booking.id).subscribe(
      (resp)=>{
        console.log(resp);
        this.getBookingDetails();
      }
    )
  }

}
