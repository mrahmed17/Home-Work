import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-wellness-resources',
  templateUrl: './wellness-resources.component.html',
  styleUrls: ['./wellness-resources.component.css']
})
export class WellnessResourcesComponent implements OnInit {
  wellnessResources = [
    { title: 'Healthy Eating Guide' },
    { title: 'Stress Management Tips' },
    // Add more wellness resources
  ];

  constructor() { }

  ngOnInit(): void { }
}
