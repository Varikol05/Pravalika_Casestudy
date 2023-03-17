import { Component } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Room } from '../room';
import { RoomService } from '../room.service';

@Component({
  selector: 'app-update-room',
  templateUrl: './update-room.component.html',
  styleUrls: ['./update-room.component.css']
})
export class UpdateRoomComponent {
  id: number = 0;

  constructor(public fb: FormBuilder, private service: RoomService, private route: ActivatedRoute, private router: Router) { }

  updateForm = this.fb.group({
    roomNumber: 0,
    roomStatus: false,
    roomType: '',
    roomPrice: 0
  })
  room: Room = {
    roomNumber: this.id,
    roomStatus: true,
    roomType: '',
    roomPrice: 0
  }

  getRoom(id: number) {
    this.service.getBynumber(id).subscribe(
      (room) => {
        this.updateForm.setValue
          ({
            roomNumber: room.roomNumber,
            roomStatus: room.roomStatus,
            roomType: room.roomType,
            roomPrice: room.roomPrice
          })
      }
    )


  }


  ngOnInit() {
    this.route.paramMap.subscribe(
      (param) => {
        var id = Number(param.get('id'))
        this.getRoom(id)
      })
  }

  PutData() {

    this.room = {
      roomNumber: this.updateForm.get('roomNumber')?.value!,
      roomStatus: this.updateForm.get('roomStatus')?.value!,
      roomType: this.updateForm.get('roomType')?.value!,
      roomPrice: this.updateForm.get('roomPrice')?.value!
    }

    this.service.updateRoom(this.room).subscribe({
      next: (data) => this.router.navigate(['roomlist']),
      error: (data) => console.log(data)
    })

  }



}
