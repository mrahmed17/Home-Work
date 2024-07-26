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
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { ProfileComponent } from './components/profile/profile.component';
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


@NgModule({
  declarations: [
    AppComponent,
    EmployeeListComponent,
    EmployeeDetailComponent,
    EmployeeCreateComponent,
    EmployeeEditComponent,
    LoginComponent,
    DashboardComponent,
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
