import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EmployeeListComponent } from '././components/employee/employee-list/employee-list.component';
import { EmployeeCreateComponent } from '././components/employee/employee-create/employee-create.component';
import { LoginComponent } from './components/login/login.component';
import { RoleGuard } from './guards/auth.guard';
import { OnboardingComponent } from './ex-component/onboarding/onboarding.component';
import { OffboardingComponent } from './ex-component/offboarding/offboarding.component';
import { TrainingProgramsComponent } from './ex-component/training-programs/training-programs.component';
import { SkillDevelopmentComponent } from './ex-component/skill-development/skill-development.component';
import { ProfileUpdateComponent } from './ex-component/profile-update/profile-update.component';
import { ExpenseClaimsComponent } from './ex-component/expense-claims/expense-claims.component';
import { HRAnalyticsComponent } from './ex-component/hr-analytics/hr-analytics.component';
import { CustomReportsComponent } from './ex-component/custom-reports/custom-reports.component';
import { PolicyDocumentsComponent } from './ex-component/policy-documents/policy-documents.component';
import { ComplianceTrackingComponent } from './ex-component/compliance-tracking/compliance-tracking.component';
import { EmployeeFeedbackComponent } from './ex-component/employee-feedback/employee-feedback.component';
import { SurveysComponent } from './ex-component/surveys/surveys.component';
import { HealthProgramsComponent } from './ex-component/health-programs/health-programs.component';
import { WellnessResourcesComponent } from './ex-component/wellness-resources/wellness-resources.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'employees', component: EmployeeListComponent, canActivate: [RoleGuard], data: { expectedRole: 'Admin' } },
  { path: 'employees/create', component: EmployeeCreateComponent, canActivate: [RoleGuard], data: { expectedRole: 'Admin' } },
  { path: '', redirectTo: '/employees', pathMatch: 'full' },
  { path: '**', redirectTo: '/employees' },
   { path: 'onboarding', component: OnboardingComponent },
  { path: 'offboarding', component: OffboardingComponent },
  { path: 'training-programs', component: TrainingProgramsComponent },
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
  // other routes

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
