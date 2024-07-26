import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-custom-reports',
  templateUrl: './custom-reports.component.html',
  styleUrls: ['./custom-reports.component.css']
})
export class CustomReportsComponent implements OnInit {
  reportForm: FormGroup;

  constructor(private fb: FormBuilder) {
    this.reportForm = this.fb.group({
    });
  }

  ngOnInit(): void {
    this.reportForm = this.fb.group({
      criteria: ['', Validators.required],
      // Add more form controls as needed
    });
  }

  generateReport(): void {
    if (this.reportForm.valid) {
      console.log(this.reportForm.value);
      // Handle report generation logic
    }
  }
}
