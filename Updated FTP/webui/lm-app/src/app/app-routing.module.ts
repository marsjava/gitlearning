import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { BrowserModule } from '@angular/platform-browser';

import { HttpModule } from '@angular/http';
import { AppComponent } from './app.component';
import { LoginFormComponent } from './login-form/login-form.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { LeaveHistoryComponent } from './leave-history/leave-history.component';
import { LeavePendingComponent } from './leave-pending/leave-pending.component';
import { MydetailsComponent } from './mydetails/mydetails.component';
import { LeaveApplyComponent } from './leave-apply/leave-apply.component';
import { MymanagerComponent } from './mymanager/mymanager.component';
import { ApproveDenyComponent } from './approve-deny/approve-deny.component';
import { ManagerApprovalComponent } from './manager-approval/manager-approval.component';
import { EmployeeComponent } from './employee/employee.component';


const routes: Routes = [
  {path: '', redirectTo: '', pathMatch: 'full'},
  { path: 'table', component: EmployeeComponent },
  { path: 'login/:empId', component: LoginFormComponent },
  { path: 'dashboard/:employeeId', component: DashboardComponent},
  { path: 'history/:employeeId', component: LeaveHistoryComponent},
  { path: 'pending/:employeeId', component: LeavePendingComponent},
  { path: 'mydetails/:employeeId', component: MydetailsComponent},
  { path: 'apply/:employeeId', component: LeaveApplyComponent},
  { path: 'mymanager/:employeeId/:managerId', component: MymanagerComponent},
  { path: 'managerpending/:employeeId', component: ManagerApprovalComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }