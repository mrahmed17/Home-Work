import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PerformanceDeleteComponent } from './performance-delete.component';

describe('PerformanceDeleteComponent', () => {
  let component: PerformanceDeleteComponent;
  let fixture: ComponentFixture<PerformanceDeleteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PerformanceDeleteComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PerformanceDeleteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
