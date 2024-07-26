import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-skill-development',
  templateUrl: './skill-development.component.html',
  styleUrls: ['./skill-development.component.css']
})
export class SkillDevelopmentComponent implements OnInit {
  skills = [
    { name: 'Skill 1', level: 'Beginner' },
    { name: 'Skill 2', level: 'Intermediate' },
    // Add more skills
  ];

  constructor() { }

  ngOnInit(): void { }
}
