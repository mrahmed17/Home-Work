import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-expense-claims',
  templateUrl: './expense-claims.component.html',
  styleUrls: ['./expense-claims.component.css']
})
export class ExpenseClaimsComponent implements OnInit {
  expenseForm: FormGroup;

  constructor(private fb: FormBuilder) { 
 this.expenseForm = this.fb.group({
      
    });

  }

  ngOnInit(): void {
    this.expenseForm = this.fb.group({
      amount: ['', Validators.required],
      description: ['', Validators.required],
      // Add more form controls as needed
    });
  }

  submitExpense(): void {
    if (this.expenseForm.valid) {
      console.log(this.expenseForm.value);
      // Handle expense submission logic
    }
  }
}
