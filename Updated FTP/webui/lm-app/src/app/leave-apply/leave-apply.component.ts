     import { Component, OnInit } from '@angular/core';
     import { Router } from '@angular/router';
     import { HttpModule } from '@angular/http';
     
     import { LeaveDetails } from '../LeaveDetails';
     import { ActivatedRoute } from '@angular/router';
     import { Employee } from '../employee';
     import { EmployeeService } from '../employee.service';
     import {FormsModule, ReactiveFormsModule} from '@angular/forms';
     import { LeaveDetailsService } from '../leave-details.service';
     
     
     @Component({
       selector: 'app-leave-apply',
       templateUrl: './leave-apply.component.html',
       styleUrls: ['./leave-apply.component.css'],
       providers: [LeaveDetailsService, EmployeeService]
     })
     export class LeaveApplyComponent implements OnInit {
       msg: string;
        daydiff: number;
        timediff: number;
        eD: Date;
        sD: Date;
        employeeId:number;
        leaveReason:number;
        noOfDays:number;
        startDate;
        endDate;
        leaveType;
        startDATE;
        name:String;
        date;
        manager;
        phone;
        dept;
        email;
        leaves;
        selectedOption: string;
        options = [
         { Option: "EL", value: 1 },
         { Option: "SL", value: 2 },
         { Option: "ML", value: 3}
       ]
      today = new Date();
      presentDate = new Date(this.startDate);

        getDate(startDate,endDate){
          this.sD= new Date(this.startDate);
          this.eD= new Date(this.endDate)
         this.timediff = Math.abs(this.sD.getTime()-this.eD.getTime());
         this.daydiff = Math.ceil(this.timediff/(1000 * 3600 * 24) + 1);
         return this.daydiff;
        }
       
        constructor(private leaveDetailsService: LeaveDetailsService, private EmployeeService: EmployeeService, private activeRoute: ActivatedRoute,private route: Router) { 
                this.employeeId = this.activeRoute.snapshot.params['employeeId'];
              }
              // empId=this.employeeId;
              title = 'Leave Management Application';
              employee: Employee;
              getEmployeeDetails(employeeId): void {
                  this.EmployeeService.getEmployeeDetails(this.employeeId).then(employee => {
                  console.log('getEmployeeDetails promise resolved : ' + employee);
                  this.employee = employee;
                  this.name = this.employee.empName;
                  this.date = this.employee.empDate;
                  this.manager = this.employee.managerId;
                  this.phone = this.employee.empPhone;
                  this.dept = this.employee.empDept;
                  this.leaves = this.employee.empLeaveAvailable;
                }
                );}
            doApply(empId,startDate,endDate,leaveReason){
        
              
              this.leaveDetailsService.setApply(this.employeeId,this.startDate,this.endDate,this.leaveReason, this.selectedOption).subscribe(
                data => {
                  alert(data);
                  if(data == "Leave Applied")
                    this.route.navigate(['/dashboard', this.employeeId]);
                },
                err => {
                  console.log(err);
                });
              }
         
          onapply(){
              this.doApply(this.employeeId,this.startDate,this.endDate,this.leaveReason);
             }
           oncancel(){
             this.route.navigate(['/history', this.employeeId]);
           }
          ngOnInit(): void {
                 this.getEmployeeDetails(this.employeeId);
           }
          }
     