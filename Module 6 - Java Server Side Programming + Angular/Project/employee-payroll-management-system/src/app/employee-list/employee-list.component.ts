import { Component, OnInit } from '@angular/core';

import { EmployeeService } from '../services/employee.service';
import { Router } from '@angular/router';
import { Employee } from '../models/employee.model';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html'
})
export class EmployeeListComponent implements OnInit {
  employees: Employee[] = [];

  constructor(private employeeService: EmployeeService, private router: Router) {}

  ngOnInit(): void {
    this.employeeService.getEmployees().subscribe((data) => {
      this.employees = data;
    });
  }

  editEmployee(id: number) {
    this.router.navigate(['/employee/edit', id]);
  }

  deleteEmployee(id: number) {
    this.employeeService.deleteEmployee(id).subscribe(() => {
      this.employees = this.employees.filter(employee => employee.id !== id);
    });
  }
}
