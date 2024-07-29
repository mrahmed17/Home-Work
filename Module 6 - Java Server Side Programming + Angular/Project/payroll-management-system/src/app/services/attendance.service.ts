import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Attendance } from '../models/attendance.model';

@Injectable({
  providedIn: 'root'
})
export class AttendanceService {
  private attendanceData: Attendance[] = [
    { id: 1, employeeId: 1, date: new Date(), status: 'Present' },
    // Add more mock data
  ];

  getAttendances(): Observable<Attendance[]> {
    return of(this.attendanceData);
  }

  getAttendanceById(id: number): Observable<Attendance> {
    return of(this.attendanceData.find(a => a.id === id)!);
  }

  // Implement create, update, delete methods
}
