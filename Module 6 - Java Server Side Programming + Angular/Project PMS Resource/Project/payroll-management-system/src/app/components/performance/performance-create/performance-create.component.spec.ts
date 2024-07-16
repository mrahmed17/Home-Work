import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PerformanceCreateComponent } from './performance-create.component';

describe('PerformanceCreateComponent', () => {
  let component: PerformanceCreateComponent;
  let fixture: ComponentFixture<PerformanceCreateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PerformanceCreateComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PerformanceCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
