import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HealthProgramsComponent } from './health-programs.component';

describe('HealthProgramsComponent', () => {
  let component: HealthProgramsComponent;
  let fixture: ComponentFixture<HealthProgramsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [HealthProgramsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HealthProgramsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
