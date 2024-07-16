import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EmployeeListComponent } from '././components/employee/employee-list/employee-list.component';
import { EmployeeCreateComponent } from '././components/employee/employee-create/employee-create.component';
import { LoginComponent } from './components/login/login.component';
import { RoleGuard } from './guards/role.guard';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'employees', component: EmployeeListComponent, canActivate: [RoleGuard], data: { expectedRole: 'Admin' } },
  { path: 'employees/create', component: EmployeeCreateComponent, canActivate: [RoleGuard], data: { expectedRole: 'Admin' } },
  { path: '', redirectTo: '/employees', pathMatch: 'full' },
  { path: '**', redirectTo: '/employees' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
