import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-surveys',
  templateUrl: './surveys.component.html',
  styleUrls: ['./surveys.component.css']
})
export class SurveysComponent implements OnInit {
  surveyForm: FormGroup;

  constructor(private fb: FormBuilder) { 
     this.surveyForm = this.fb.group({
      // form controls
    });
  }

  ngOnInit(): void {
    this.surveyForm = this.fb.group({
      survey: ['', Validators.required],
      // Add more form controls as needed
    });
  }

  submitSurvey(): void {
    if (this.surveyForm.valid) {
      console.log(this.surveyForm.value);
      // Handle survey submission logic
    }
  }
}
