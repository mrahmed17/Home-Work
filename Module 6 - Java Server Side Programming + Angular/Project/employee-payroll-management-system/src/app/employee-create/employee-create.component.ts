import { Component } from '@angular/core';
import { EmployeeService } from '../services/employee.service';
import { Employee } from '../models/employee.model';

@Component({
  selector: 'app-employee-create',
  templateUrl: './employee-create.component.html'
})
export class EmployeeCreateComponent {
  employee: Employee = { id: 0, name: '', position: '', department: '' }; // Initialize with default values

  constructor(private employeeService: EmployeeService) {}

  onSubmit(): void {
    this.employeeService.createEmployee(this.employee).subscribe(() => {
      // Navigate back or to another view
    });
  }
}
