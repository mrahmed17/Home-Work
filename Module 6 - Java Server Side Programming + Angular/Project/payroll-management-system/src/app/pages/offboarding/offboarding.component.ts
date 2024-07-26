import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-offboarding',
  templateUrl: './offboarding.component.html',
  styleUrls: ['./offboarding.component.css']
})
export class OffboardingComponent implements OnInit {
  offboardingForm: FormGroup;

  constructor(private fb: FormBuilder) {
     this.offboardingForm = this.fb.group({
      // form controls
    });
   }

  ngOnInit(): void {
    this.offboardingForm = this.fb.group({
      employeeId: ['', Validators.required],
      exitDate: ['', Validators.required],
      // Add more form controls as needed
    });
  }

  offboardEmployee(): void {
    if (this.offboardingForm.valid) {
      console.log(this.offboardingForm.value);
      // Handle offboarding logic
    }
  }
}
