import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'app-attendance-list',
  templateUrl: './attendance-list.component.html',
  styleUrl: './attendance-list.component.css'
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

