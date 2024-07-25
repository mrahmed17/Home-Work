import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-onboarding',
  templateUrl: './onboarding.component.html',
  styleUrls: ['./onboarding.component.css']
})
export class OnboardingComponent implements OnInit {
  onboardingForm: FormGroup;

  constructor(private fb: FormBuilder) {
     this.onboardingForm = this.fb.group({
      // form controls
    });
   }

  ngOnInit(): void {
    this.onboardingForm = this.fb.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      // Add more form controls as needed
    });
  }

  onboardEmployee(): void {
    if (this.onboardingForm.valid) {
      console.log(this.onboardingForm.value);
      // Handle onboarding logic
    }
  }
}
