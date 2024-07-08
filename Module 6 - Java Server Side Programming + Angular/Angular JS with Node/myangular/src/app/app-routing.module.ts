import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { StudentComponent } from './student-create/student.component';
import { StudentListComponent } from './student-list/student-list.component';
import { StudentDetailsComponent } from './student-details/student-details.component';
import { StudentUpdateComponent } from './student-update/student-update.component';

const routes: Routes = [

  { path: 'student-create', component: StudentComponent},
  { path: 'student-list', component: StudentListComponent},
  { path: 'student-details', component: StudentDetailsComponent},
  { path: 'student-update', component: StudentUpdateComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
