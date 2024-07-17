import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SkillDevelopmentComponent } from './skill-development.component';

describe('SkillDevelopmentComponent', () => {
  let component: SkillDevelopmentComponent;
  let fixture: ComponentFixture<SkillDevelopmentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SkillDevelopmentComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SkillDevelopmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
