import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { EmployeeListComponent } from './employee-list/employee-list.component';
import { AttendanceListComponent } from './attendance-list/attendance-list.component';
import { LeaveListComponent } from './leave-list/leave-list.component';
import { PayrollListComponent } from './payroll-list/payroll-list.component';
import { NavbarComponent } from './navbar/navbar.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { AuthService } from './services/auth.service';
import { EmployeeService } from './services/employee.service';
import { AttendanceService } from './services/attendance.service';
import { LeaveService } from './services/leave.service';
import { PayrollService } from './services/payroll.service';
import { EmployeeDetailComponent } from './employee-detail/employee-detail.component';
import { EmployeeCreateComponent } from './employee-create/employee-create.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { ReportListComponent } from './report-list/report-list.component';
import { ReportService } from './services/report.service';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    EmployeeListComponent,
    AttendanceListComponent,
    LeaveListComponent,
    PayrollListComponent,
    NavbarComponent,
    DashboardComponent,
    EmployeeDetailComponent,
    EmployeeCreateComponent,
    NotFoundComponent,
    ReportListComponent
  ],
  imports: [
    AuthService,
    AttendanceService,
    EmployeeService,
    LeaveService,
    PayrollService,
    ReportService,
    BrowserModule,
    AppRoutingModule
  ],
  providers: [
    provideClientHydration()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
