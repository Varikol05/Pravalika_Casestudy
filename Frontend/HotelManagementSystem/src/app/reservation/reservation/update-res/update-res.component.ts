import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { fromEvent, Subject, takeUntil } from 'rxjs';
import { Reservation } from '../reservation';
import { ReservationService } from '../reservation.service';

@Component({
  selector: 'app-update-res',
  templateUrl: './update-res.component.html',
  styleUrls: ['./update-res.component.css']
})
export class UpdateResComponent implements OnInit {
  private unsubscriber: Subject<void> = new Subject<void>();
  id: number = 0;
  constructor(public fb: FormBuilder, private service: ReservationService, private route: ActivatedRoute, private router: Router) { }
  updateReservation = this.fb.group({
    reservationCode: 0,
    name: ['', [Validators.required, Validators.pattern('^[a-zA-Z ]*$'), Validators.minLength(3)]],
    gender: [''],
    checkIn: new Date,
    checkOut: new Date,
    roomType: ['']
  });

  res: Reservation = {
    reservationCode: this.id,
    name: '',
    gender: '',
    checkIn: new Date(),
    checkOut: new Date(),
    roomType: ''
  };
  getReservation(id: any) {
    this.service.getById(id).subscribe(
      (reservation) => this.updateReservation.setValue({
        reservationCode: reservation.reservationCode,
        name: reservation.name,
        gender: reservation.gender,
        checkIn: reservation.checkIn,
        checkOut: reservation.checkOut,
        roomType: reservation.roomType
      })
    )
  }
  ngOnInit() {
    history.pushState(null, '', location.href);
    fromEvent(window, 'popstate').pipe(takeUntil(this.unsubscriber)).subscribe((_) => {
      history.pushState(null, '');
      alert(`You can't go back at this time.`);
    });
    this.route.paramMap.subscribe(
      (param) => {
        var id = Number(param.get('id'))
        this.getReservation(id)
      })

  }
  UpdateData() {
    this.res = {
      reservationCode: this.updateReservation.get('reservationCode')?.value!,
      name: this.updateReservation.get('name')?.value!,
      gender: this.updateReservation.get('gender')?.value!,
      checkIn: this.updateReservation.get('checkIn')?.value!,
      checkOut: this.updateReservation.get('checkOut')?.value!,
      roomType: this.updateReservation.get('roomType')?.value!
    }
    this.service.updateReservation(this.res).subscribe({
      next: (data) => this.router.navigate(['res/list']),
      error: (data) => console.log(data)
    })
  }
  CurrentDate: any = new Date();
}
