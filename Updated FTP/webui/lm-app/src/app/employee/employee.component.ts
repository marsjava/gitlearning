import { Component, OnInit } from '@angular/core';
import { EmployeeService } from '../employee.service';
import { Employee } from '../employee';
import { Router } from '@angular/router';
import { HttpModule } from '@angular/http';

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css'],
  providers: [EmployeeService]
})
export class EmployeeComponent implements OnInit {

  constructor(private employeeService: EmployeeService, private route: Router) { }
  title = 'Leave Management Application';
  employees: Employee[];
  getEmployees(): void {
      this.employeeService.getEmployees().then(employees => {
      console.log('getEmployees promise resolved : ' + employees.length);
      this.employees = employees;
    }
    );}
  onLogin(empId) {
      this.route.navigate(['/login', empId]);
    }
  ngOnInit(): void {
      this.getEmployees();
    }
}


