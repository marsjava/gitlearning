import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Employee } from '../employee';
import { RouterTestingModule } from '@angular/router/testing';
import { Http,HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';
import { EmployeeService } from '../employee.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
  providers: [EmployeeService]
})
export class DashboardComponent implements OnInit {
  employeeId: number;
  constructor(private employeeService: EmployeeService, private activeRoute: ActivatedRoute,private route: Router) { 
    this.employeeId = this.activeRoute.snapshot.params['employeeId'];
  }
  empId=this.employeeId;
  employee: Employee;
  getEmployeeDetails(empId): void {
      this.employeeService.getEmployeeDetails(empId).then(employee => {
      console.log('getEmployeeDetails promise resolved : ' + employee);
      this.employee = employee;
  
    }
    );
  }
  onhistory() {
    this.route.navigate(['/history', this.employeeId]);
  }
  onpending() {
    this.route.navigate(['/pending', this.employeeId]);
  }
  ondetails() {
    this.route.navigate(['/mydetails', this.employeeId]);
  }
  onmanager(managerId) {
    this.route.navigate(['/mymanager', this.employeeId, managerId]);
  }
  onstatus() {
    this.route.navigate(['/managerpending', this.employeeId]);
  }
  onelements() {
    this.route.navigate(['/elements']);
  }
  ngOnInit() {
    this.getEmployeeDetails(this.employeeId);
  }

}