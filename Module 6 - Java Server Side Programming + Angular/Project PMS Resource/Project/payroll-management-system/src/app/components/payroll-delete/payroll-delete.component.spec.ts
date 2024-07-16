import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PayrollDeleteComponent } from './payroll-delete.component';

describe('PayrollDeleteComponent', () => {
  let component: PayrollDeleteComponent;
  let fixture: ComponentFixture<PayrollDeleteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PayrollDeleteComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PayrollDeleteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
