import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OffboardingComponent } from './offboarding.component';

describe('OffboardingComponent', () => {
  let component: OffboardingComponent;
  let fixture: ComponentFixture<OffboardingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [OffboardingComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OffboardingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
