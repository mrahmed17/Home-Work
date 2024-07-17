import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-policy-documents',
  templateUrl: './policy-documents.component.html',
  styleUrls: ['./policy-documents.component.css']
})
export class PolicyDocumentsComponent implements OnInit {
  policies = [
    { title: 'Privacy Policy' },
    { title: 'Code of Conduct' },
    // Add more policies
  ];

  constructor() { }

  ngOnInit(): void { }
}

