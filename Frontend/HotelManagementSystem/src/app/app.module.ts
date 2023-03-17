import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AuthenticationModule } from './authentication/authentication.module';
import { GuestModule } from './guest/guest/guest.module';
import { InventoryModule } from './inventory/inventory/inventory.module';
import { ReservationModule } from './reservation/reservation/reservation.module';
import { RoomModule } from './room/room/room.module';
import { StaffModule } from './staff/staff/staff.module';
import { HomeComponent } from './home/home.component';
import { ManagerDashBoardComponent } from './manager-dash-board/manager-dash-board.component';
import { OwnerDashBoardComponent } from './owner-dash-board/owner-dash-board.component';
import { ReciptionistDashBoardComponent } from './reciptionist-dash-board/reciptionist-dash-board.component';
import { TokeninterceptorService } from './tokeninterceptor.service';
import { PaymentComponent } from './payment/payment.component';
import { TermsandconditionsComponent } from './termsandconditions/termsandconditions.component';
import { InventoryfilterPipe } from './inventory/inventoryfilter.pipe';







@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ManagerDashBoardComponent,
    OwnerDashBoardComponent,
    ReciptionistDashBoardComponent,
    PaymentComponent,
    TermsandconditionsComponent,
   
    
    


  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    AuthenticationModule,
    HttpClientModule,
    RoomModule,
    GuestModule,
    StaffModule,
    InventoryModule,
    ReservationModule,
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: TokeninterceptorService,
    multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
