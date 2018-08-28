import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { LoginFormComponent } from './login-form/login-form.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { Routes, RouterModule } from '@angular/router';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule,ReactiveFormsModule } from '@angular/forms';
import { EmployeeService } from './employee.service';
import { MymanagerComponent } from './mymanager/mymanager.component';
import { LeaveHistoryComponent } from './leave-history/leave-history.component';
import { MydetailsComponent } from './mydetails/mydetails.component';
import { LeaveApplyComponent } from './leave-apply/leave-apply.component';
import { LeavePendingComponent } from './leave-pending/leave-pending.component';
import { ManagerApprovalComponent } from './manager-approval/manager-approval.component';
import { ApproveDenyComponent } from './approve-deny/approve-deny.component';
import { EmployeeComponent } from './employee/employee.component';


@NgModule({
  declarations: [
    AppComponent,EmployeeComponent,LeaveHistoryComponent,
    LeavePendingComponent,LoginFormComponent,
    DashboardComponent,LeaveApplyComponent, MydetailsComponent, MymanagerComponent, ManagerApprovalComponent, ApproveDenyComponent
  ],
  imports: [
    BrowserModule,AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpModule
  ],
  
  providers: [EmployeeService],
  bootstrap: [AppComponent]
})
export class AppModule { }
