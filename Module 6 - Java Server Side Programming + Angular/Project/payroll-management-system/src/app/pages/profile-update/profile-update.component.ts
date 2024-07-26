import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-profile-update',
  templateUrl: './profile-update.component.html',
  styleUrls: ['./profile-update.component.css']
})
export class ProfileUpdateComponent implements OnInit {
  profileForm: FormGroup;

  constructor(private fb: FormBuilder) { 
     this.profileForm = this.fb.group({
      // form controls
    });
  }

  ngOnInit(): void {
    this.profileForm = this.fb.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      // Add more form controls as needed
    });
  }

  updateProfile(): void {
    if (this.profileForm.valid) {
      console.log(this.profileForm.value);
      // Handle profile update logic
    }
  }
}
