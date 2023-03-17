import { Component } from '@angular/core';
import { fromEvent, Subject, takeUntil } from 'rxjs';
import { AuthService } from '../authentication/auth.service';

@Component({
  selector: 'app-manager-dash-board',
  templateUrl: './manager-dash-board.component.html',
  styleUrls: ['./manager-dash-board.component.css']
})
export class ManagerDashBoardComponent {
  private unsubscriber: Subject<void> = new Subject<void>();
  constructor(private service: AuthService) {

  }
  logout() {
    this.service.loggedOut();
  }
  ngOnInit() {
    history.pushState(null, '', location.href);
    fromEvent(window, 'popstate').pipe(takeUntil(this.unsubscriber)).subscribe((_) => {
      history.pushState(null, '');
      alert(`You can't go back at this time.`);
    });


  }
}
