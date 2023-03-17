import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { InventoryRoutingModule } from './inventory-routing.module';
import { ListComponent } from './list/list.component';
import { CreateComponent } from './create/create.component';
import { GetByIdComponent } from './get-by-id/get-by-id.component';
import { UpdateComponent } from './update/update.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { InventoryfilterPipe } from '../inventoryfilter.pipe';

@NgModule({
  declarations: [
    ListComponent,
    CreateComponent,
    GetByIdComponent,
    UpdateComponent,
    InventoryfilterPipe
  ],
  imports: [
    CommonModule,
    InventoryRoutingModule,
    ReactiveFormsModule,
    FormsModule

  ]
})
export class InventoryModule { }
