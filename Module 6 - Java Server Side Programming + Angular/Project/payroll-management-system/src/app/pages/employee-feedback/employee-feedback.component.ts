import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-employee-feedback',
  templateUrl: './employee-feedback.component.html',
  styleUrls: ['./employee-feedback.component.css']
})
export class EmployeeFeedbackComponent implements OnInit {
  feedbackForm: FormGroup;

  constructor(private fb: FormBuilder) {
    this.feedbackForm = this.fb.group({
      feedback: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    // this.feedbackForm = this.fb.group({
    //   feedback: ['', Validators.required],
    //   // Add more form controls as needed
    // });
  }

  submitFeedback(): void {
    if (this.feedbackForm.valid) {
      console.log(this.feedbackForm.value);
     // Logic to handle feedback submission
    }
  }
}
