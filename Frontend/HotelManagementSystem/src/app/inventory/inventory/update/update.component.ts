
import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { fromEvent, Subject, takeUntil } from 'rxjs';
import { Inventory } from '../inventory';
import { InventoryService } from '../inventory.service';

@Component({
  selector: 'app-update',
  templateUrl: './update.component.html',
  styleUrls: ['./update.component.css']
})
export class UpdateComponent implements OnInit {
  private unsubscriber: Subject<void> = new Subject<void>();
  id: number = 0;
  constructor(private fb: FormBuilder, private service: InventoryService, private route: ActivatedRoute, private router: Router) { }
  ngOnInit(): void {
    history.pushState(null, '', location.href);
    fromEvent(window, 'popstate').pipe(
      takeUntil(this.unsubscriber)
    ).subscribe((_) => {
      history.pushState(null, '');
      alert(`You can't go back at this time.`);
    });
    this.route.paramMap.subscribe(
      (param) => {
        console.log(param.get('id'))
        var id = Number(param.get('id'))
        this.getInventory(id)
      })
  }
  updateForm = this.fb.group(
    {
      inventoryCode: 0,
      inventoryType: ['', Validators.pattern('^[a-zA-Z ]*$')],
      inventoryName: ['', Validators.pattern('^[a-zA-Z ]*$')],
      inventoryQuantity: 0
    }
  );

  inv: Inventory = {
    inventoryCode: this.id,
    inventoryType: '',
    inventoryName: '',
    inventoryQuantity: 0
  }

  getInventory(id: number) {
    this.service.getById(id).subscribe(
      (inventory) => {
        console.log(inventory);
        this.updateForm.setValue(
          {
            inventoryCode: inventory.inventoryCode,
            inventoryType: inventory.inventoryType,
            inventoryName: inventory.inventoryName,
            inventoryQuantity: inventory.inventoryQuantity
          })

      }
    )
  }
  update() {
    this.inv = {
      inventoryCode: this.updateForm.get('inventoryCode')?.value!,
      inventoryType: this.updateForm.get('inventoryType')?.value!,
      inventoryName: this.updateForm.get('inventoryName')?.value!,
      inventoryQuantity: this.updateForm.get('inventoryQuantity')?.value!
    }
    this.service.updateInventory(this.inv).subscribe({
      next: (data) => this.router.navigate([`inventory/all`]),
      error: (data) => console.log(data)
    })
  }
}
