import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Staff } from '../staff';
import { StaffService } from '../staff.service';

@Component({
  selector: 'app-update',
  templateUrl: './update.component.html',
  styleUrls: ['./update.component.css']
})
export class UpdateComponent {
  id: number = 0;
  constructor(public fb: FormBuilder, private service: StaffService, private route: ActivatedRoute, private router: Router) { }

  updateStaff = this.fb.group({
    id: [0],
    employeeName: ['', [Validators.required, Validators.minLength(3), Validators.pattern('^[a-zA-Z ]*$'), Validators.maxLength(16)]],
    phoneNo: [''],
    email: ['', Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$')],
    age: [0],
    employeeAddress: [''],
    salary: [0]
  })

  staff: Staff = {
    id: this.id,
    employeeName: '',
    phoneNo: '',
    email: '',
    age: 0,
    employeeAddress: '',
    salary: 0
  }
  getStaff(id: number) {
    this.service.getById(id).subscribe(
      (staff) => this.updateStaff.setValue({
        id: staff.id,
        employeeName: staff.employeeName,
        phoneNo: staff.phoneNo,
        email: staff.email,
        age: staff.age,
        employeeAddress: staff.employeeAddress,
        salary: staff.salary
      })
    )
  }
  ngOnInit() {
    this.route.paramMap.subscribe(
      (param) => {
        var id = Number(param.get('id'))
        this.getStaff(id)
      }
    )
  }
  UpdateData() {
    this.staff = {
      id: this.updateStaff.get('id')?.value!,
      employeeName: this.updateStaff.get('employeeName')?.value!,
      phoneNo: this.updateStaff.get('phoneNo')?.value!,
      email: this.updateStaff.get('email')?.value!,
      age: this.updateStaff.get('age')?.value!,
      employeeAddress: this.updateStaff.get('employeeAddress')?.value!,
      salary: this.updateStaff.get('salary')?.value!
    }
    this.service.updateStaff(this.staff).subscribe({
      next: (data) => this.router.navigate(['staff/all']),
      error: (data) => console.log(data)
    })
  }
}
