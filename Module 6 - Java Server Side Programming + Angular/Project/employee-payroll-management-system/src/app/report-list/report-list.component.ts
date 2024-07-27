import { Component, OnInit } from '@angular/core';
import { ReportService } from '../services/report.service';
import { Report } from '../models/report.model';

@Component({
  selector: 'app-report-list',
  templateUrl: './report-list.component.html',
  styleUrl: './report-list.component.css'
})
export class ReportListComponent implements OnInit {
  reports: Report[] = [];

  constructor(private reportService: ReportService) {}

  ngOnInit(): void {
    this.reportService.getReports().subscribe(reports => this.reports = reports);
  }

  viewReport(id: number): void {
    // Implement view logic
  }

  editReport(id: number): void {
    // Implement edit logic
  }

  deleteReport(id: number): void {
    this.reportService.deleteReport(id).subscribe(() => {
      this.reports = this.reports.filter(report => report.id !== id);
    });
  }
}