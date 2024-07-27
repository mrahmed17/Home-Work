import { Component, OnInit } from '@angular/core';
import { Attendance } from '../models/attendance.model';
import { AttendanceService } from '../services/attendance.service';
import { EmployeeService } from '../services/employee.service';

@Component({
  selector: 'app-attendance-list',
  templateUrl: './attendance-list.component.html'
})
export class AttendanceListComponent implements OnInit {
  attendanceRecords: Attendance[] = [];
  employees: { [key: number]: string } = {};

  constructor(
    private attendance: AttendanceService,
    private employeeService: EmployeeService
  ) {}

  ngOnInit(): void {
    this.attendance.getAttendanceRecords().subscribe((data) => {
      this.attendanceRecords = data;
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
