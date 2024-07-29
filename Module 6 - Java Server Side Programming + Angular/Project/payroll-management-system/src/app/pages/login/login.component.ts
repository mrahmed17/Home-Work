import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loginForm: FormGroup;
  returnUrl: string;
  error: string=''; // Ensure this line is included

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    if (this.authService.currentUserValue) {
      this.router.navigate(['/']);
    }

    this.loginForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });

    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

    onSubmit(): void {
      if (this.loginForm.valid) {
       const username = this.loginForm.get('username')?.value;
        const password = this.loginForm.get('password')?.value;
        
      this.authService.login(username, password).subscribe(
          data => {
            this.router.navigate([this.returnUrl]);
          },
          error => {
            this.error = 'Invalid username or password';
          }
        );
    }
  }
}
