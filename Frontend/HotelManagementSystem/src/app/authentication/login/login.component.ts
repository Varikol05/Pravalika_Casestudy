import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { fromEvent, Subject, takeUntil } from 'rxjs';
import { AuthService } from '../auth.service';
import { AuthRequest } from '../Authrequest';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  errorMsg: string = '';
  private unsubscriber: Subject<void> = new Subject<void>();
  constructor(private fb: FormBuilder, private authService: AuthService, private router: Router) {
  }

  ngOnInit(): void {
    history.pushState(null, '', location.href);
    fromEvent(window, 'popstate').pipe(takeUntil(this.unsubscriber)).subscribe((_) => {
      history.pushState(null, '');
      alert(`You can't go back at this time.`);
    });
  }
  loginForm = this.fb.group({
    username: ['', [Validators.required, Validators.minLength(7), Validators.pattern('^[a-zA-Z ]*$'), Validators.maxLength(32)]],
    role: [''],
    password: ['', [Validators.required, Validators.minLength(8), Validators.maxLength(32)]],
    confirmPassword: ['', Validators.required]
  });


  get username() {
    return this.loginForm.get('username');
  }

  get role() {
    return this.loginForm.get('roles');
  }

  get password() {
    return this.loginForm.get('password');
  }

  get confirmPassword() {
    return this.loginForm.get('confirmPassword');
  }

  request: AuthRequest = {
    userName: '',
    password: ''
  }

  login() {

    this.request.userName = this.loginForm.get('username')?.value!;
    this.request.password = this.loginForm.get('password')?.value!;
    console.log(this.request)
    this.authService.login(this.request).subscribe({
      next: (data) => {
        this.authService.storeToken(data.jwt);
        console.log(data.jwt)
        this.authService.getUserRole(this.request.userName).subscribe({
          next: (role) => {
            localStorage.setItem("currentRole", JSON.stringify(role));
            if (role.role == 'ROLE_RECEPTIONIST') {
              console.log(role.role); this.router.navigate(['reciptionistdashboard'])
            }
            else if (role.role == 'ROLE_MANAGER') {
              console.log(role.role); this.router.navigate(['managerdashboard'])
            }
            else if (role.role == 'ROLE_OWNER') {
              console.log(role.role); this.router.navigate(['ownerdashboard'])
            }

          },
        });
      },
      error: (data) => { 
        this.errorMsg = "Invalid Username/Password";
       }
    })
  }

}
