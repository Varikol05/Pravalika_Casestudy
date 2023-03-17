import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';

import { RoomRoutingModule } from './room-routing.module';
import { RoomListComponent } from './room-list/room-list.component';
import { CreateRoomComponent } from './create-room/create-room.component';
import { FormsModule } from '@angular/forms';

import { RoomByIdComponent } from './room-by-id/room-by-id.component';
import { UpdateRoomComponent } from './update-room/update-room.component';
import { RoomfilterPipe } from 'src/app/roomfilter.pipe';


@NgModule({
  declarations: [
    RoomListComponent,
    CreateRoomComponent,

    RoomByIdComponent,
    UpdateRoomComponent,
    RoomfilterPipe
  ],
  imports: [
    CommonModule,
    RoomRoutingModule,
    ReactiveFormsModule,
    FormsModule
  ]
})
export class RoomModule { }
