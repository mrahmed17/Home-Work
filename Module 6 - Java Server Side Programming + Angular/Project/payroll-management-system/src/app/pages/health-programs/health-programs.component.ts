import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-health-programs',
  templateUrl: './health-programs.component.html',
  styleUrls: ['./health-programs.component.css']
})
export class HealthProgramsComponent implements OnInit {
  healthPrograms = [
    { name: 'Yoga Classes' },
    { name: 'Gym Membership' },
    // Add more health programs
  ];

  constructor() { }

  ngOnInit(): void { }
}
