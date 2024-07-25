import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ComplianceTrackingComponent } from './compliance-tracking.component';

describe('ComplianceTrackingComponent', () => {
  let component: ComplianceTrackingComponent;
  let fixture: ComponentFixture<ComplianceTrackingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ComplianceTrackingComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ComplianceTrackingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
