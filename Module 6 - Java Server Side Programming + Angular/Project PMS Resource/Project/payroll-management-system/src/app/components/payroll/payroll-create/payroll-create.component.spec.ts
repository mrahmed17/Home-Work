import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PayrollCreateComponent } from './payroll-create.component';

describe('PayrollCreateComponent', () => {
  let component: PayrollCreateComponent;
  let fixture: ComponentFixture<PayrollCreateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PayrollCreateComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PayrollCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
