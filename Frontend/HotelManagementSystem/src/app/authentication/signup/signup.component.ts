import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { match } from '../passwordvalidation';
import { User } from '../user';


@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {

  constructor(private fb: FormBuilder, private authService: AuthService, private router: Router) {

  }

  signupForm = this.fb.group({
    username: ['', [Validators.required, Validators.minLength(7), Validators.maxLength(32), Validators.pattern('^[a-zA-Z\s]*$')]],
    roles: ['Role', Validators.required],
    password: ['', [Validators.required, Validators.minLength(8), Validators.maxLength(32)]],
    confirmPassword: ['', [Validators.required, Validators.minLength(8), Validators.maxLength(32)]]
  },

    {
      validators: match('password', 'confirmPassword')
    }
  );
  validateTopic(value: any) {
    if (value === "Role") {
      this.roleHasError = true;
    }
    else {
      this.roleHasError = false;
    }
  }


  get username() {
    return this.signupForm.get('username');
  }

  get roles() {
    return this.signupForm.get('roles');
  }

  get password() {
    return this.signupForm.get('password');
  }

  get confirmPassword() {
    return this.signupForm.get('confirmPassword');
  }


  roleHasError = true;
  validateRole(value: any) {
    if (value === 'Role') {
      this.roleHasError = true;
    }
    else {
      this.roleHasError = false;
    }
  }


  user: User = {
    userName: '',
    password: '',
    active: false,
    role: ''
  }

  onSubmit() {
    this.user = {
      userName: this.signupForm.get('username')?.value!,
      password: this.signupForm.get('password')?.value!,
      active: true,
      role: this.signupForm.get('roles')?.value!
    }
    console.log(this.user);
    alert("Successfully Rigistered");
    this.authService.register(this.user).subscribe({

      next: (result) => {
        this.router.navigate(["authentication/login"])
      },
      error: (data) => console.log(data)
    });
  }
}