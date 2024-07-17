import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WellnessResourcesComponent } from './wellness-resources.component';

describe('WellnessResourcesComponent', () => {
  let component: WellnessResourcesComponent;
  let fixture: ComponentFixture<WellnessResourcesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [WellnessResourcesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(WellnessResourcesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
