import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './auth.guard';
import { HomeComponent } from './home/home.component';
import { ManagerDashBoardComponent } from './manager-dash-board/manager-dash-board.component';
import { OwnerDashBoardComponent } from './owner-dash-board/owner-dash-board.component';
import { PaymentComponent } from './payment/payment.component';
import { ReciptionistDashBoardComponent } from './reciptionist-dash-board/reciptionist-dash-board.component';
import { TermsandconditionsComponent } from './termsandconditions/termsandconditions.component';

const routes: Routes = [{
  path: '',
  redirectTo: 'home',
  pathMatch: 'full'
}, {
  path: 'ownerdashboard',
  component: OwnerDashBoardComponent,canActivate:[AuthGuard]
},
{
  path: 'managerdashboard',
  component: ManagerDashBoardComponent,canActivate:[AuthGuard]
}, {
  path: 'reciptionistdashboard',
  component: ReciptionistDashBoardComponent,canActivate:[AuthGuard]
},
{
  path: 'home',
  component: HomeComponent
},
{
  path:'payment',
  component:PaymentComponent
},{
path:'terms&conditions',
component:TermsandconditionsComponent
}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
