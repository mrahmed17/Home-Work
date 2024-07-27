import { Component, OnInit } from '@angular/core';
import { EmployeeService } from '../services/employee.service';
import { LeaveService } from '../services/leave.service';
import { PayrollService } from '../services/payroll.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html'
})
export class DashboardComponent implements OnInit {
  totalEmployees: number = 0;
  pendingLeaves: number = 0;
  totalPayrolls: number = 0;

  constructor(
    private employeeService: EmployeeService,
    private leaveService: LeaveService,
    private payrollService: PayrollService
  ) {}

  ngOnInit(): void {
    this.employeeService.getEmployees().subscribe(employees => {
      this.totalEmployees = employees.length;
    });

    this.leaveService.getLeaves().subscribe(leaves => {
      this.pendingLeaves = leaves.filter(leave => leave.status === 'Pending').length;
    });

    this.payrollService.getPayrolls().subscribe(payrolls => {
      this.totalPayrolls = payrolls.length;
    });
  }
}
