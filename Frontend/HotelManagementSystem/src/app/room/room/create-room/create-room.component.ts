import { Component, OnInit } from '@angular/core';
import { Room } from '../room';
import { FormBuilder } from '@angular/forms';
import { RoomService } from '../room.service';
import { Router } from '@angular/router';
import { OwnerDashBoardComponent } from 'src/app/owner-dash-board/owner-dash-board.component';
import { fromEvent, Subject, takeUntil } from 'rxjs';
@Component({
  selector: 'app-create-room',
  templateUrl: './create-room.component.html',
  styleUrls: ['./create-room.component.css']
})
export class CreateRoomComponent implements OnInit {
  private unsubscriber: Subject<void> = new Subject<void>();
  roomTypeHasError: boolean = false;

  constructor(private fb: FormBuilder, private roomervice: RoomService, private router: Router) { }
  ngOnInit() {
    history.pushState(null, '', location.href);
    fromEvent(window, 'popstate').pipe(takeUntil(this.unsubscriber)).subscribe((_) => {
      history.pushState(null, '');
      alert(`You can't go back at this time.`);
    });

  }

 


  creationForm = this.fb.group({
    roomNumber: [''],
    roomStatus: [''],
    
    totalRooms: ['']

  });



  onSubmit() {
    console.log(this.creationForm.value);
    this.roomervice.addRoom(this.creationForm.value).subscribe({
      next: (result) => {
        this.router.navigate(["payment"])
      },
      error: (error) => {
        console.log(error)
      }

    });
  }

  validateroomType(value: any) {
    if (value === 'RoomType') {
      this.roomTypeHasError = true;
    } else {
      this.roomTypeHasError = false;
    }
  }

  navigate() {
    var role = JSON.parse(localStorage.getItem("currentRole")!);


    if (role.role == "ROLE_OWNER") {
      this.router.navigate(['/ownerdashboard']);
    }
    else if (role.role == "ROLE_MANAGER") {
      this.router.navigate(['/managerdashboard']);
    }

    else if (role.role == "ROLE_RECEPTIONIST") {
      this.router.navigate(['/reciptionistdashboard']);
    }
  }



}
