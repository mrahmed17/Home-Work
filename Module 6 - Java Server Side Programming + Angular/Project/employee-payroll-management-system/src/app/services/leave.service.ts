import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Leave } from '../models/leave.model';

@Injectable({
  providedIn: 'root'
})
export class LeaveService {
  private apiUrl = 'http://your-api-url/leaves'; // Replace with your backend API URL

  constructor(private http: HttpClient) {}

  getLeaves(): Observable<Leave[]> {
    return this.http.get<Leave[]>(this.apiUrl);
  }

  getLeave(id: number): Observable<Leave> {
    return this.http.get<Leave>(`${this.apiUrl}/${id}`);
  }

  createLeave(leave: Leave): Observable<Leave> {
    return this.http.post<Leave>(this.apiUrl, leave);
  }

  updateLeave(id: number, leave: Leave): Observable<Leave> {
    return this.http.put<Leave>(`${this.apiUrl}/${id}`, leave);
  }

  updateLeaveStatus(id: number, status: string): Observable<Leave> {
    return this.http.patch<Leave>(`${this.apiUrl}/${id}`, { status });
  }

  deleteLeave(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
