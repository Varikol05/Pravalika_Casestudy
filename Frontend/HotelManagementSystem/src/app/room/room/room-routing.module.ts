import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from 'src/app/auth.guard';
import { CreateRoomComponent } from './create-room/create-room.component';
import { RoomByIdComponent } from './room-by-id/room-by-id.component';
import { RoomListComponent } from './room-list/room-list.component';
import { UpdateRoomComponent } from './update-room/update-room.component';


const routes: Routes = [{
  path: 'roomlist',
  component: RoomListComponent, canActivate: [AuthGuard]
}, {
  path: 'createroom',
  component: CreateRoomComponent, canActivate: [AuthGuard]
}, {

  path: 'view/:id',
  component: RoomByIdComponent, canActivate: [AuthGuard]
}, {
  path: 'updateroom/:id',
  component: UpdateRoomComponent, canActivate: [AuthGuard]

}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RoomRoutingModule { }
