import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AttendanceCreateComponent } from './attendance-create.component';

describe('AttendanceCreateComponent', () => {
  let component: AttendanceCreateComponent;
  let fixture: ComponentFixture<AttendanceCreateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AttendanceCreateComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AttendanceCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
