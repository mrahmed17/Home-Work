import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PerformanceDetailComponent } from './performance-detail.component';

describe('PerformanceDetailComponent', () => {
  let component: PerformanceDetailComponent;
  let fixture: ComponentFixture<PerformanceDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PerformanceDetailComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PerformanceDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
