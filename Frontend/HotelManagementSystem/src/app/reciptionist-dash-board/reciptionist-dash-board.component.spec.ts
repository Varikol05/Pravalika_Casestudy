import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReciptionistDashBoardComponent } from './reciptionist-dash-board.component';

describe('ReciptionistDashBoardComponent', () => {
  let component: ReciptionistDashBoardComponent;
  let fixture: ComponentFixture<ReciptionistDashBoardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReciptionistDashBoardComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReciptionistDashBoardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
