import { Component, OnInit } from '@angular/core';
import { AttendanceService } from '../../../services/attendance.service';
import { Attendance } from '../../../models/attendance.model';

@Component({
  selector: 'app-attendance-list',
  templateUrl: './attendance-list.component.html',
  styleUrls: ['./attendance-list.component.css']
})

export class AttendanceListComponent implements OnInit {
  getEmployeeName(arg0: any) {
    throw new Error('Method not implemented.');
  }

  attendances: Attendance[] = [];

  constructor(private attendanceService: AttendanceService) { }

  ngOnInit(): void {
    this.attendanceService.getAttendances().subscribe(data => this.attendances = data);
  }
}
