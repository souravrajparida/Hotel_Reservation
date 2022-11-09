import { Component, OnInit, ViewChild } from '@angular/core';
import { RoomService } from '../room.service';
import { Book } from '../book';
import { Router } from '@angular/router';

@Component({
  selector: 'app-room',
  templateUrl: './room.component.html',
  styleUrls: ['./room.component.css']
})
export class RoomComponent implements OnInit {
  
  roomDetails=null as any;
  roomtoview = {
    type:"",
    conditioner:"",
    
  }
  book=new Book();

  constructor(private roomService: RoomService, private _router : Router) {
    this.getRoomsDetails();

   }
   getRoomsDetails(){
    this.roomService.getRooms().subscribe(
      (resp)=>{
        console.log(resp);
        this.roomDetails = resp;
      },
      (err)=>{
        console.log(err);
      }
    );
  }
  book1(room: any){
    this.roomtoview = room;
  }


  ngOnInit(): void {
  }


  bookRoom(){
    this.roomService.bookRoomFromRemote(this.book).subscribe(
      data => {
        console.log("response received");
      //  this.msg="Registration successful";

      this._router.navigate(['/room'])

  },
  error => {
    console.log("exception occured");
    // this.msg=error.error;
    

  }
    )
}




}
