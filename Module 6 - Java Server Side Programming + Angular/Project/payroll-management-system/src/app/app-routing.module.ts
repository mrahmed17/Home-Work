import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EmployeeListComponent } from '././components/employee/employee-list/employee-list.component';
import { EmployeeCreateComponent } from '././components/employee/employee-create/employee-create.component';
import { LoginComponent } from './pages/login/login.component';
import { AuthGuard } from './guards/auth.guard';
import { OnboardingComponent } from './pages/onboarding/onboarding.component';
import { OffboardingComponent } from './pages/offboarding/offboarding.component';
import { TrainingProgramsComponent } from './pages/training-programs/training-programs.component';
import { SkillDevelopmentComponent } from './pages/skill-development/skill-development.component';
import { ProfileUpdateComponent } from './pages/profile-update/profile-update.component';
import { ExpenseClaimsComponent } from './pages/expense-claims/expense-claims.component';
import { HRAnalyticsComponent } from './pages/hr-analytics/hr-analytics.component';
import { CustomReportsComponent } from './pages/custom-reports/custom-reports.component';
import { PolicyDocumentsComponent } from './pages/policy-documents/policy-documents.component';
import { ComplianceTrackingComponent } from './pages/compliance-tracking/compliance-tracking.component';
import { EmployeeFeedbackComponent } from './pages/employee-feedback/employee-feedback.component';
import { SurveysComponent } from './pages/surveys/surveys.component';
import { HealthProgramsComponent } from './pages/health-programs/health-programs.component';
import { WellnessResourcesComponent } from './pages/wellness-resources/wellness-resources.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { LeaveListComponent } from './components/leave/leave-list/leave-list.component';
import { PayrollListComponent } from './components/payroll/payroll-list/payroll-list.component';
import { AttendanceListComponent } from './components/attendance/attendance-list/attendance-list.component';
import { EmployeeDetailComponent } from './components/employee/employee-detail/employee-detail.component';
import { EmployeeEditComponent } from './components/employee/employee-edit/employee-edit.component';




const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'employees', component: EmployeeListComponent, canActivate: [AuthGuard], data: { expectedRole: 'Admin' } },
  { path: 'dashboard', component: DashboardComponent},
  { path: 'employees/create', component: EmployeeCreateComponent,
    canActivate: [AuthGuard], data: { expectedRole: 'Admin' }},
  { path: 'employees/:id', component: EmployeeDetailComponent },
  { path: 'edit-employee/:id', component: EmployeeEditComponent },
  { path: '', redirectTo: '/login', pathMatch: 'full'},
  { path: '', redirectTo: '/employees', pathMatch: 'full' },
  { path: '**', redirectTo: '/employees' },
  { path: 'onboarding', component: OnboardingComponent },
  { path: 'offboarding', component: OffboardingComponent },
  { path: 'training-programs', component: TrainingProgramsComponent },
  { path: 'leave', component: LeaveListComponent},
  { path: 'payroll', component: PayrollListComponent},
  { path: 'attendance', component: AttendanceListComponent},
  { path: 'skill-development', component: SkillDevelopmentComponent },
  { path: 'profile-update', component: ProfileUpdateComponent },
  { path: 'expense-claims', component: ExpenseClaimsComponent },
  { path: 'hr-analytics', component: HRAnalyticsComponent },
  { path: 'custom-reports', component: CustomReportsComponent },
  { path: 'policy-documents', component: PolicyDocumentsComponent },
  { path: 'compliance-tracking', component: ComplianceTrackingComponent },
  { path: 'employee-feedback', component: EmployeeFeedbackComponent },
  { path: 'surveys', component: SurveysComponent },
  { path: 'health-programs', component: HealthProgramsComponent },
  { path: 'wellness-resources', component: WellnessResourcesComponent },


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
