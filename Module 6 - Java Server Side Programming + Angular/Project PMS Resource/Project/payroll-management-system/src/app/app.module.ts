import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { EmployeeListComponent } from './components/employee/employee-list/employee-list.component';
import { EmployeeDetailComponent } from './components/employee/employee-detail/employee-detail.component';
import { EmployeeCreateComponent } from './components/employee/employee-create/employee-create.component';
import { EmployeeEditComponent } from './components/employee/employee-edit/employee-edit.component';
import { LoginComponent } from './components/login/login.component';
import { AuthService } from './services/auth.service';
import { AuthGuard } from './guards/auth.guard';
import { JwtInterceptor } from './interceptors/jwt.interceptor';
import { provideHttpClient, withFetch } from '@angular/common/http';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ProfileComponent } from './profile/profile.component';
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

@NgModule({
  declarations: [
    AppComponent,
    EmployeeListComponent,
    EmployeeDetailComponent,
    EmployeeCreateComponent,
    EmployeeEditComponent,
    LoginComponent,
    DashboardComponent,
    ProfileComponent,
    OnboardingComponent,
    OffboardingComponent,
    TrainingProgramsComponent,
    SkillDevelopmentComponent,
    ProfileUpdateComponent,
    ExpenseClaimsComponent,
    HRAnalyticsComponent,
    CustomReportsComponent,
    PolicyDocumentsComponent,
    ComplianceTrackingComponent,
    EmployeeFeedbackComponent,
    SurveysComponent,
    HealthProgramsComponent,
    WellnessResourcesComponent,
    // Other existing components
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [
    provideHttpClient(withFetch()),
    AuthService,
    AuthGuard,
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
