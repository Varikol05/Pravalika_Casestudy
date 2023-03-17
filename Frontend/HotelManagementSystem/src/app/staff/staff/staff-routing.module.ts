import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from 'src/app/auth.guard';
import { CreateComponent } from './create/create.component';
import { GetByIdComponent } from './get-by-id/get-by-id.component';
import { ListComponent } from './list/list.component';
import { UpdateComponent } from './update/update.component';

const routes: Routes = [{
  path: 'staff/all',
  component: ListComponent,canActivate:[AuthGuard]
}, {
  path: 'staff/add',
  component: CreateComponent,canActivate:[AuthGuard]
}, {
  path: 'staff/update/:id',
  component: UpdateComponent,canActivate:[AuthGuard]
}, {
  path: 'staff/:id',
  component: GetByIdComponent,canActivate:[AuthGuard]
}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class StaffRoutingModule { }
