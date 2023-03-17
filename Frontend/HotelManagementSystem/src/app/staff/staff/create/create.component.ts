import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { fromEvent, Subject, takeUntil } from 'rxjs';
import { StaffService } from '../staff.service';

@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.css']
})
export class CreateComponent implements OnInit {
  private unsubscriber: Subject<void> = new Subject<void>();
  constructor(private staff: StaffService, private router: Router) { }
  ngOnInit(): void {
    history.pushState(null, '', location.href);
    fromEvent(window, 'popstate').pipe(takeUntil(this.unsubscriber)).subscribe((_) => {
      history.pushState(null, '');
      alert(`You can't go back at this time.`);
    });
  }
  addStaff = new FormGroup({


    id: new FormControl(''),
    employeeName: new FormControl('', [Validators.required, Validators.minLength(3), Validators.pattern('^[a-zA-Z ]*$'), Validators.maxLength(16)]),
    phoneNo: new FormControl(''),
    email: new FormControl('', Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$')),
    age: new FormControl(''),
    employeeAddress: new FormControl(''),
    salary: new FormControl('')

  });
  SaveData() {
    console.log(this.addStaff.value);
    alert("Staff details added successfully");
    this.staff.saveStaff(this.addStaff.value).subscribe({
      next: (result) => {
        this.router.navigate(["staff/all"])
      },
      error: (error) => {
        console.log(error)
      }
    });


  }
}
