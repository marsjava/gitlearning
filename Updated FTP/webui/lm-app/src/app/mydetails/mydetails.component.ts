import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpModule } from '@angular/http';
import { LeaveDetails } from '../LeaveDetails';
import { ActivatedRoute } from '@angular/router';
import { EmployeeService } from '../employee.service';
import { Employee } from '../employee';

@Component({
  selector: 'app-mydetails',
  templateUrl: './mydetails.component.html',
  styleUrls: ['./mydetails.component.css'],
  providers: [EmployeeService]
  
})
export class MydetailsComponent implements OnInit {
name:String;
date;
manager;
phone;
dept;
email;
leaves;

  employeeId:number;
  constructor(private employeeService: EmployeeService, private activeRoute: ActivatedRoute,private route: Router) { 
    this.employeeId = this.activeRoute.snapshot.params['employeeId'];
  }
  employee: Employee;
  getEmployeeDetails(empId): void {
      this.employeeService.getEmployeeDetails(this.employeeId).then(employee => {
      console.log('getEmployeeDetails promise resolved : ' + employee);
      this.employee = employee;
      console.log("bjbj",this.employee);
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
      this.getEmployeeDetails(this.employeeId);
    }
}
