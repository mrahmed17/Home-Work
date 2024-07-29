import { Component, OnInit } from '@angular/core';
import { PayrollService } from '../../../services/payroll.service';
import { Payroll } from '../../../models/payroll.model';

@Component({
  selector: 'app-payroll-list',
  templateUrl: './payroll-list.component.html',
  styleUrls: ['./payroll-list.component.css']
})
export class PayrollListComponent implements OnInit {
  payrolls: Payroll[] | undefined;

  constructor(private payrollService: PayrollService) { }

  ngOnInit(): void {
    this.payrollService.getPayrolls().subscribe(data => {
      this.payrolls = data;
    });
  }
}
