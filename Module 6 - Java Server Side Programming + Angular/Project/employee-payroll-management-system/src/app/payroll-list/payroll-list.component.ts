import { Component, OnInit } from '@angular/core';
import { Payroll } from '../models/payroll.model';
import { PayrollService } from '../services/payroll.service';
import { EmployeeService } from '../services/employee.service';

@Component({
  selector: 'app-payroll-list',
  templateUrl: './payroll-list.component.html'
})
export class PayrollListComponent implements OnInit {
  payrolls: Payroll[] = [];
  employees: { [key: number]: string } = {};

  constructor(
    private payroll: PayrollService,
    private employeeService: EmployeeService
  ) {}

  ngOnInit(): void {
    this.payroll.getPayrolls().subscribe((data) => {
      this.payrolls = data;
    });
    this.employeeService.getEmployees().subscribe((employees) => {
      employees.forEach(employee => {
        this.employees[employee.id] = employee.name;
      });
    });
  }

  getEmployeeName(employeeId: number): string {
    return this.employees[employeeId];
  }
}
