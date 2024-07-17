import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-compliance-tracking',
  templateUrl: './compliance-tracking.component.html',
  styleUrls: ['./compliance-tracking.component.css']
})
export class ComplianceTrackingComponent implements OnInit {
  compliances = [
    { title: 'Safety Training', status: 'Completed' },
    { title: 'Data Privacy', status: 'Pending' },
    // Add more compliances
  ];

  constructor() { }

  ngOnInit(): void { }
}

