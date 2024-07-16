import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PayrollDetailComponent } from './payroll-detail.component';

describe('PayrollDetailComponent', () => {
  let component: PayrollDetailComponent;
  let fixture: ComponentFixture<PayrollDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PayrollDetailComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PayrollDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
