import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { fromEvent, Subject, takeUntil } from 'rxjs';
import { GuestService } from '../guest.service';

@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.css']
})
export class CreateComponent implements OnInit {
  private unsubscriber: Subject<void> = new Subject<void>();

  constructor(private guest: GuestService, private router: Router) { }
  ngOnInit(): void {
    history.pushState(null, '', location.href);
    fromEvent(window, 'popstate').pipe(takeUntil(this.unsubscriber)).subscribe((_) => {
      history.pushState(null, '');
      alert(`You can't go back at this time.`);
    });
  }

  creationForm = new FormGroup({
    guestId: new FormControl(''),
    name: new FormControl('', [Validators.required, Validators.minLength(3), Validators.pattern('^[a-zA-Z ]*$'), Validators.maxLength(16)]),
    contact: new FormControl(''),
    gender: new FormControl(''),
    email: new FormControl('', Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$')),
    address: new FormControl('')
  });

  get name() {
    return this.creationForm.get('name');
  }
  addguest() {
    console.log(this.creationForm.value);
    alert("Guest details added successfully");
    this.guest.addguest(this.creationForm.value).subscribe({
      next: (result) => {
        console.log(result);
        this.router.navigate(["res/create"])
      },
      error: (error) => {
        console.log(error)
      }

    });

  }

}


