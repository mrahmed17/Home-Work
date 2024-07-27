import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Payroll } from '../models/payroll.model';

@Injectable({
  providedIn: 'root'
})
export class PayrollService {
  private apiUrl = 'http://your-api-url/payrolls'; // Replace with your backend API URL

  constructor(private http: HttpClient) {}

  getPayrolls(): Observable<Payroll[]> {
    return this.http.get<Payroll[]>(this.apiUrl);
  }

  getPayroll(id: number): Observable<Payroll> {
    return this.http.get<Payroll>(`${this.apiUrl}/${id}`);
  }

  createPayroll(payroll: Payroll): Observable<Payroll> {
    return this.http.post<Payroll>(this.apiUrl, payroll);
  }

  updatePayroll(id: number, payroll: Payroll): Observable<Payroll> {
    return this.http.put<Payroll>(`${this.apiUrl}/${id}`, payroll);
  }

  deletePayroll(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
