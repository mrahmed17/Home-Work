import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PayrollRoutingModule } from './payroll-routing.module';
import { PayrollListComponent } from './payroll-list/payroll-list.component';
import { PayrollDetailComponent } from './payroll-detail/payroll-detail.component';
import { PayrollCreateComponent } from './payroll-create/payroll-create.component';


@NgModule({
  declarations: [
    PayrollListComponent,
    PayrollDetailComponent,
    PayrollCreateComponent
  ],
  imports: [
    CommonModule,
    PayrollRoutingModule
  ]
})
export class PayrollModule { }
