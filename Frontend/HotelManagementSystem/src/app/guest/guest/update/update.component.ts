import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Guest } from '../guest';
import { GuestService } from '../guest.service';
@Component({
  selector: 'app-update',
  templateUrl: './update.component.html',
  styleUrls: ['./update.component.css']
})
export class UpdateComponent implements OnInit {
  id: number = 0;
  constructor(private fb: FormBuilder, private service: GuestService, private route: ActivatedRoute, private router: Router) { }
  ngOnInit(): void {
    this.route.paramMap.subscribe((param) => {
      this.id = Number(param.get('id'))
      this.getGuest(this.id);


    }

    )
  }
  creationForm = this.fb.group(
    {

      name: ['', [Validators.required, Validators.minLength(3), Validators.pattern('^[a-zA-Z ]*$'), Validators.maxLength(16)]],
      contact: [0],
      gender: [''],
      email: ['', Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$')],
      address: [''],
    });

  guest: Guest = {
    guestId: this.id,
    name: '',
    contact: 0,
    gender: '',
    email: '',
    address: '',

  }
  getGuest(id: number) {
    this.service.getById(id).subscribe(
      (guest) => this.creationForm.setValue(
        {
          name: guest.name,
          contact: guest.contact,
          gender: guest.gender,
          email: guest.email,
          address: guest.address


        })
    )
  }
  update() {
    this.guest = {
      guestId: this.id,
      name: this.creationForm.get('name')?.value!,
      contact: this.creationForm.get('contact')?.value!,
      gender: this.creationForm.get('gender')?.value!,
      email: this.creationForm.get('email')?.value!,
      address: this.creationForm.get('address')?.value!,
    }
    this.service.updateguest(this.guest).subscribe({
      next: (data) => this.router.navigate([`guest/all`]),
      error: (data) => console.log(data)
    })
  }

}
