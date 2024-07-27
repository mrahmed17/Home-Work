import { Component, OnInit } from '@angular/core';
import { Leave } from '../models/leave.model';
import { LeaveService } from '../services/leave.service';
import { EmployeeService } from '../services/employee.service';

@Component({
  selector: 'app-leave-list',
  templateUrl: './leave-list.component.html'
})
export class LeaveListComponent implements OnInit {
  leaves: Leave[] = [];
  employees: { [key: number]: string } = {};

  constructor(
    private leave: LeaveService,
    private employeeService: EmployeeService
  ) {}

  ngOnInit(): void {
    this.leave.getLeaves().subscribe((data) => {
      this.leaves = data;
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

  approveLeave(id: number) {
    this.leave.updateLeaveStatus(id, 'Approved').subscribe(() => {
      this.leaves = this.leaves.map(leave => leave.id === id ? { ...leave, status: 'Approved' } : leave);
    });
  }

  rejectLeave(id: number) {
    this.leave.updateLeaveStatus(id, 'Rejected').subscribe(() => {
      this.leaves = this.leaves.map(leave => leave.id === id ? { ...leave, status: 'Rejected' } : leave);
    });
  }
}
