import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { fromEvent, Subject, takeUntil } from 'rxjs';
import { ReservationService } from '../reservation.service';

@Component({
  selector: 'app-create-res',
  templateUrl: './create-res.component.html',
  styleUrls: ['./create-res.component.css']
})
export class CreateResComponent implements OnInit {
  private unsubscriber: Subject<void> = new Subject<void>();
  roleHasError: boolean = false;
  constructor(private reservationService: ReservationService, private router: Router) { }
  ngOnInit(): void {

    history.pushState(null, '', location.href);
    fromEvent(window, 'popstate').pipe(
      takeUntil(this.unsubscriber)
    ).subscribe((_) => {
      history.pushState(null, '');
      alert(`You can't go back at this time.`);
    });




  }
  addReservation = new FormGroup({
    reservationCode: new FormControl(''),
    name: new FormControl('', [Validators.required, Validators.pattern('^[a-zA-Z\s]*$'), Validators.minLength(3), Validators.maxLength(16)]),
    gender: new FormControl(''),
    checkIn: new FormControl(''),
    checkOut: new FormControl(''),
    roomType: new FormControl('')
  });
  SaveData() {
    console.log(this.addReservation.value);
    alert("Reservation details added successfully");
    this.reservationService.saveReservation(this.addReservation.value).subscribe({
      next: (result) => {
        this.router.navigate(["createroom"])
      },
      error: (error) => {
        console.log(error)
      }
    });


  }
  CurrentDate: any = new Date();
}










function validateRole(value: any, any: any) {
  throw new Error('Function not implemented.');
}

function validateTopic(value: any, any: any) {
  throw new Error('Function not implemented.');
}

