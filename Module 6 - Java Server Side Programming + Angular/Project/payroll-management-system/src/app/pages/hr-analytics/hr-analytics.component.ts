import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-hr-analytics',
  templateUrl: './hr-analytics.component.html',
  styleUrls: ['./hr-analytics.component.css']
})
export class HRAnalyticsComponent implements OnInit {
  reports = [
    { title: 'Employee Turnover', description: 'Analysis of employee turnover rates.' },
    { title: 'Performance Metrics', description: 'Evaluation of employee performance metrics.' },
    // Add more reports
  ];

  constructor() { }

  ngOnInit(): void { }
}
