import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { StudentComponent } from './studentform/student.component';
import { StudentListComponent } from './studentlist/student-list.component';
import { StudentDetailsComponent } from './studentdetails/student-details.component';
import { StudentUpdateComponent } from './studentupdate/student-update.component';

const routes: Routes = [

  { path: 'studentform', component: StudentComponent},
  { path: 'studentlist', component: StudentListComponent},
  { path: 'studentdetails', component: StudentDetailsComponent},
  { path: 'studentupdate', component: StudentUpdateComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
