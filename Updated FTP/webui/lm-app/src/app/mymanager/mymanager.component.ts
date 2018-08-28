import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpModule } from '@angular/http';
import { LeaveDetails } from '../LeaveDetails';
import { ActivatedRoute } from '@angular/router';
import { EmployeeService } from '../employee.service';
import { Employee } from '../employee';


@Component({
  selector: 'app-mymanager',
  templateUrl: './mymanager.component.html',
  styleUrls: ['./mymanager.component.css'],
  providers: [EmployeeService]
})
export class MymanagerComponent implements OnInit {
  managerId:number;
  employeeId:number;
  name;
  date;
  manager;
  phone;
  dept;
  email;
  leaves;
  constructor(private employeeService: EmployeeService, private activeRoute: ActivatedRoute,private route: Router) { 
    this.managerId = this.activeRoute.snapshot.params['managerId'];
    this.employeeId = this.activeRoute.snapshot.params['employeeId'];
  }
  employee: Employee;
getEmployeeDetails(managerId): void {
    this.employeeService.getEmployeeDetails(this.managerId).then(employee => {
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
    onback() {
        this.route.navigate(['/dashboard', this.employeeId]); 
    }
  ngOnInit(): void {
      this.getEmployeeDetails(this.managerId);
    }
}
