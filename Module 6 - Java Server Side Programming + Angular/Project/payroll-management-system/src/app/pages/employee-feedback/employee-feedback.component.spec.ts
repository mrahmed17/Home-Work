import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeeFeedbackComponent } from './employee-feedback.component';

describe('EmployeeFeedbackComponent', () => {
  let component: EmployeeFeedbackComponent;
  let fixture: ComponentFixture<EmployeeFeedbackComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [EmployeeFeedbackComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EmployeeFeedbackComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
