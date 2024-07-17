import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExpenseClaimsComponent } from './expense-claims.component';

describe('ExpenseClaimsComponent', () => {
  let component: ExpenseClaimsComponent;
  let fixture: ComponentFixture<ExpenseClaimsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ExpenseClaimsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ExpenseClaimsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
