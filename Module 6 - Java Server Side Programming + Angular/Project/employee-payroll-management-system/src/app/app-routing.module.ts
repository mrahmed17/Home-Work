import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { EmployeeListComponent } from './employee-list/employee-list.component';
import { AttendanceListComponent } from './attendance-list/attendance-list.component';
import { LeaveListComponent } from './leave-list/leave-list.component';
import { PayrollListComponent } from './payroll-list/payroll-list.component';
import { DashboardComponent } from './dashboard/dashboard.component';


const routes: Routes = [
{path: '', redirectTo: '/login', pathMatch: 'full'},
{path: 'login', component: LoginComponent},
{path: 'dashboard', component: DashboardComponent},
{path: 'employees', component: EmployeeListComponent},
{path: 'attendance', component: AttendanceListComponent},
{path: 'leave', component: LeaveListComponent},
{path: 'payroll', component: PayrollListComponent},
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
