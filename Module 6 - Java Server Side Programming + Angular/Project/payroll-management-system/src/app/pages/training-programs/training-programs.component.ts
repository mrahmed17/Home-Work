import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-training-programs',
  templateUrl: './training-programs.component.html',
  styleUrls: ['./training-programs.component.css']
})
export class TrainingProgramsComponent implements OnInit {
  trainingPrograms = [
    { name: 'Program 1' },
    { name: 'Program 2' },
    // Add more programs
  ];

  constructor() { }

  ngOnInit(): void { }
}
