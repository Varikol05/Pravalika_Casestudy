import { Component, OnInit } from '@angular/core';
import { Room } from '../room';
import { RoomService } from '../room.service';

@Component({
  selector: 'app-room-list',
  templateUrl: './room-list.component.html',
  styleUrls: ['./room-list.component.css']
})
export class RoomListComponent implements OnInit {

  rooms: Room[] = [];
  constructor(private roomService: RoomService) {

  }
  ngOnInit() {
    this.getRooms();

  }
  searchText:string='';

  getRooms() {
    this.roomService.getAllRooms().subscribe(data => {
      this.rooms = data;
    })
  }
  deleteRoom(roomNumber: number) {
    if (confirm('Are you sure to delete record?'))
      this.roomService.deleteRoom(roomNumber).subscribe(
        (result) => {
          console.log(result);
        }

      )
  }


}

