import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AttendanceDeleteComponent } from './attendance-delete.component';

describe('AttendanceDeleteComponent', () => {
  let component: AttendanceDeleteComponent;
  let fixture: ComponentFixture<AttendanceDeleteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AttendanceDeleteComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AttendanceDeleteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
