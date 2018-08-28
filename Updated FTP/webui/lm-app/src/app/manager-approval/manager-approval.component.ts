import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpModule } from '@angular/http';

import { LeaveDetails } from '../LeaveDetails';
import { ActivatedRoute } from '@angular/router';
import { Employee } from '../employee';
import { EmployeeService } from '../employee.service';
import { LeaveDetailsService } from '../leave-details.service';


@Component({
    selector: 'app-manager-approval',
    templateUrl: './manager-approval.component.html',
    styleUrls: ['./manager-approval.component.css'],
    providers: [LeaveDetailsService, EmployeeService]
})
export class ManagerApprovalComponent implements OnInit {
    arr: any;
    managerId: number;
    employeeId: number;
    leaveIdSelect: number;
    buttonApproveDeny: boolean;
    approveLeaveId;
    highlight;
    leavePending: LeaveDetails[];
    leaveDetailsByLeaveId: number;
    empName: String;
    employees: Employee[];
    empname: String;
    empLeaveBalance1: number;
    empLeaveBalance: number;

    constructor(private leaveDetailsService: LeaveDetailsService, private activeRoute: ActivatedRoute, private route: Router, private EmployeeService: EmployeeService) {
        this.employeeId = this.activeRoute.snapshot.params['employeeId'];
    }
    empId = this.employeeId;
    title = 'Leave Management Application';
    managerpending: LeaveDetails[];



    getEmployees(): void {
        this.EmployeeService.getEmployees().then(employees => {
            console.log('getEmployees promise resolved  : ' + employees.length);
            this.employees = employees;
        }
        );
    }

    getManagerPending(empId): void {
        this.leaveDetailsService.getManagerPending(empId).then(managerpending => {
            console.log('getManagerPending promise resolved : ' + managerpending.length);

            this.managerpending = managerpending;
        }
        );
    }

    findEmpName(empId: number): String {
        for (var i = 0; i < this.employees.length; i++) {

            if (empId == this.employees[i].empId) {
                this.empname = this.employees[i].empName;
                break;
            }
        }
        return this.empname;

    }

    findEmpLeaveBalance(empId: number): number {
        for (var i = 0; i < this.employees.length; i++) {
            if (empId == this.employees[i].empId) {
                this.empLeaveBalance1 = this.employees[i].empLeaveAvailable;
                break;
            }
        }
        return this.empLeaveBalance1;
    }
    findEmpManager(empId: number): number {
        for (var i = 0; i < this.employees.length; i++) {
            if (empId == this.employees[i].empId) {
                this.managerId = this.employees[i].managerId;
                break;
            }
        }
        return this.managerId;
    }
    
    empIdforTable(): number[] {
        console.log("All employees", this.employees);
        console.log("empId of all", this.employeeId);

        var uniqueEmpUnderManager = [];

        for (var i = 0; i < this.employees.length; i++) {
            console.log("empId of employee", this.employees[i].empId);
            if (this.employees[i].managerId == this.employeeId) {
                console.log("getting employees underManager", this.employees[i].empId);
                uniqueEmpUnderManager.push(this.employees[i].empId);
            }
        }
        for (var k = 0; k < this.managerpending.length; k++) {
            if (this.managerpending[i].empId == uniqueEmpUnderManager[i]) {
                console.log("sdddddddddddasddddddddddddddd", this.employees[i].empId);
            }
        }
        console.log("selecting required employee", uniqueEmpUnderManager);

        return uniqueEmpUnderManager;
    }




    onapprovedeny(empId, leaveId, managerId) {
        this.route.navigate(['/approvedeny', empId, leaveId, managerId]);
    }

    onback() {
        this.route.navigate(['/dashboard', this.employeeId]);
    }

    // selectRow(event:any, employee:any) {
    //   this.leaveIdSelect = employee.leaveId;
    // }

    ngOnInit() {
        this.getEmployees();
        this.getManagerPending(this.employeeId);
    }
}










