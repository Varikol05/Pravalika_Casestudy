import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { fromEvent, Subject, takeUntil } from 'rxjs';
import { Reservation } from '../reservation';
import { ReservationService } from '../reservation.service';

@Component({
  selector: 'app-list-res',
  templateUrl: './list-res.component.html',
  styleUrls: ['./list-res.component.css']
})
export class ListResComponent implements OnInit {
  private unsubscriber: Subject<void> = new Subject<void>();
  reservations: Reservation[] = [];
  constructor(private reservationService: ReservationService, private router: Router) { }


  ngOnInit() {
    history.pushState(null, '', location.href);
    fromEvent(window, 'popstate').pipe(takeUntil(this.unsubscriber)).subscribe((_) => {
      history.pushState(null, '');
      alert(`You can't go back at this time.`);
    });
    this.getReservation();
  } getReservation() {
    this.reservationService.getAllReservation().
      subscribe(data => this.reservations = data
      );
  }

  deleteReservation(reservationCode: number) {
    if (confirm('Are you sure to delete record?'))
      this.reservationService.deleteReservation(reservationCode).
        subscribe((result) => {
          console.log(result);
          location.reload();
        })
  }

  navigate() {
    var role = JSON.parse(localStorage.getItem("currentRole")!);


    if (role.role == "ROLE_OWNER") {
      this.router.navigate(['/ownerdashboard']);
    }

    else {
      this.router.navigate(['/reciptionistdashboard']);
    }
  }
}


