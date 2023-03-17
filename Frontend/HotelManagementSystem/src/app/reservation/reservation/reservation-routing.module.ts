import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from 'src/app/auth.guard';
import { CreateResComponent } from './create-res/create-res.component';
import { GetByIdResComponent } from './get-by-id-res/get-by-id-res.component';
import { ListResComponent } from './list-res/list-res.component';
import { UpdateResComponent } from './update-res/update-res.component';

const routes: Routes = [{
  path: 'res/list',
  component: ListResComponent,canActivate:[AuthGuard]
}, {
  path: 'res/update/:id',
  component: UpdateResComponent,canActivate:[AuthGuard]
}, {
  path: 'res/create',
  component: CreateResComponent,canActivate:[AuthGuard]
}, {
  path: 'res/get/:id',
  component: GetByIdResComponent,canActivate:[AuthGuard]
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ReservationRoutingModule { }
