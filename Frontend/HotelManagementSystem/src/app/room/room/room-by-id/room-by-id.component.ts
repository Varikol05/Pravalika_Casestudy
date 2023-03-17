import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Room } from '../room';
import { RoomService } from '../room.service';

@Component({
  selector: 'app-room-by-id',
  templateUrl: './room-by-id.component.html',
  styleUrls: ['./room-by-id.component.css']
})
export class RoomByIdComponent implements OnInit {
  getRoomByNumber: Room = {
    roomNumber: 0,
    roomStatus: false,
    roomType:'',
    roomPrice:0
  };

  constructor(private service: RoomService, private route: ActivatedRoute) { }
  getBynumber(roomNumber: number) {
    this.service.getBynumber(roomNumber).subscribe(
      (data) => {
        console.log(data);
        this.getRoomByNumber = data;

      })
  }

  ngOnInit() {
    this.route.paramMap.subscribe(
      (param) => {
        console.log(param);
        var roomNumber = Number(param.get('id'));
        this.getBynumber(roomNumber);
      }
    )
  }
}


