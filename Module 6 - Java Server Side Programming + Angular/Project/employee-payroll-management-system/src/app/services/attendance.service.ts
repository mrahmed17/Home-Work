import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Attendance } from '../models/attendance.model';

@Injectable({
  providedIn: 'root'
})
export class AttendanceService {
  private apiUrl = 'http://your-api-url/attendance'; // Replace with your backend API URL

  constructor(private http: HttpClient) {}

  getAttendanceRecords(): Observable<Attendance[]> {
    return this.http.get<Attendance[]>(this.apiUrl);
  }

  getAttendanceRecord(id: number): Observable<Attendance> {
    return this.http.get<Attendance>(`${this.apiUrl}/${id}`);
  }

  createAttendanceRecord(record: Attendance): Observable<Attendance> {
    return this.http.post<Attendance>(this.apiUrl, record);
  }

  updateAttendanceRecord(id: number, record: Attendance): Observable<Attendance> {
    return this.http.put<Attendance>(`${this.apiUrl}/${id}`, record);
  }

  deleteAttendanceRecord(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
