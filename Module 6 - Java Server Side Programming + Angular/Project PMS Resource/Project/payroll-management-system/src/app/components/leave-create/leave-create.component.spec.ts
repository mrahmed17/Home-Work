import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LeaveCreateComponent } from './leave-create.component';

describe('LeaveCreateComponent', () => {
  let component: LeaveCreateComponent;
  let fixture: ComponentFixture<LeaveCreateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [LeaveCreateComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LeaveCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
