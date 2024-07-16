import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AttendanceDetailComponent } from './attendance-detail.component';

describe('AttendanceDetailComponent', () => {
  let component: AttendanceDetailComponent;
  let fixture: ComponentFixture<AttendanceDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AttendanceDetailComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AttendanceDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
