import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpModule } from '@angular/http';

import { LeaveDetails } from '../LeaveDetails';
import { ActivatedRoute } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Employee } from "../employee";
import { EmployeeService } from "../employee.service";
import { LeaveDetailsService } from '../leave-details.service';





@Component({
  selector: 'app-approve-deny',
  templateUrl: './approve-deny.component.html',
  styleUrls: ['./approve-deny.component.css'],
  providers: [LeaveDetailsService]
})
export class ApproveDenyComponent implements OnInit {
  managerId: any;
  employeeId: number;
  leaveId: number
  managerComments: String;
  leaveStatus: String;
  name:String;
  leaveid;
  startdate;
  enddate;
  applieddate;
  reason;
  type;
  days;
  leaves;
  
 
 
    constructor(private leaveDetailsService: LeaveDetailsService, private employeeService: EmployeeService, private activeRoute: ActivatedRoute,private route: Router) { 
      this.employeeId = this.activeRoute.snapshot.params['empId'];
      this.leaveId = this.activeRoute.snapshot.params['leaveId'];
      this.managerId = this.activeRoute.snapshot.params['managerId'];
        
    }

    fetchLeaveId: LeaveDetails;
    getLeaveId(leaveId): void {
        this.leaveDetailsService.getLeaveId(this.leaveId).then(fetchLeaveId => {
        console.log('getLeaveId promise resolved : ' + fetchLeaveId);
        console.log('leaveId details',fetchLeaveId);
        this.fetchLeaveId = fetchLeaveId;
        this.leaveid=this.fetchLeaveId.leaveId;
        this.startdate=this.fetchLeaveId.startDate;
        this.enddate=this.fetchLeaveId.endDate;
        this.applieddate=this.fetchLeaveId.leaveAppliedDate;
        this.reason=this.fetchLeaveId.leaveReason;
        this.type= this.fetchLeaveId.leaveType;
        this.days=this.fetchLeaveId.noOfDays;
  

      }
      );}
  
      empId=this.employeeId;
      employee: Employee;
      getEmployeeDetails(empId): void {
          this.employeeService.getEmployeeDetails(empId).then(employee => {
          console.log('getEmployeeDetails promise resolved : ' + employee);
          this.employee = employee;
          this.name = this.employee.empName;
          this.leaves = this.employee.empLeaveAvailable;

        }
        );}
  
    getapprovedeny(leaveStatus, managerComments, leaveId):void {
        this.leaveDetailsService.setStatus(this.employeeId, this.leaveStatus, this.managerComments, this.leaveId).subscribe(data => {
        console.log(data)
        alert(data)
        if(data == "Leave Status Changed") {
          this.route.navigate(['/managerpending', this.employeeId]);
        }
        err => {
          console.log(err);
        };
       
      }
      );}
      managerCommentsEnable():boolean{
        if (this.managerComments==null){
          return  true;
        }
        else{
          return false;
        }
      }
    onapprove(){
    this.leaveStatus="APPROVED";
    this.getapprovedeny( this.leaveStatus, this.managerComments, this.leaveId);
    this.route.navigate(['/dashboard',this.employeeId]);
    }
    ondeny(){
      this.leaveStatus="DENIED";
      this.getapprovedeny(this.leaveStatus, this.managerComments, this.leaveId);
      this.route.navigate(['/dashboard',this.employeeId]);
      }
    onback() {
    this.route.navigate(['/managerpending',this.managerId]);
  }
    ngOnInit() {
      this.getLeaveId(this.leaveId);
      this.getEmployeeDetails(this.employeeId);
    }
  }
  