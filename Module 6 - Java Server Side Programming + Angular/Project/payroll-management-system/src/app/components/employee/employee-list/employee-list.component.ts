import { Component, OnInit } from '@angular/core';
import { EmployeeService } from '../../../services/employee.service';
import { Employee } from '../../../models/employee.model';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit {
  employees: any[] = [];

  constructor(private employeeService: EmployeeService) { }

  ngOnInit(): void {
   this.fetchEmployees();
   
    // this.employeeService.getEmployees().subscribe(data => {
    //   this.employees = data;
    // });
  }

 fetchEmployees(): void {
    this.employeeService.getEmployees().subscribe((data: any[]) => {
      this.employees = data;
    });
    }

  deleteEmployee(id: number): void {
    this.employeeService.deleteEmployee(id).subscribe(() => {
      this.employees = this.employees.filter(employee => employee.id !== id);
    });
  }
    
}
