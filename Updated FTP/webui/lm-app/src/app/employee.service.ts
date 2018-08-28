import { Injectable } from '@angular/core';
import { Employee } from './employee';

import { Observable } from 'rxjs/Rx';
import 'rxjs/add/operator/toPromise';

import { Http } from '@angular/http';

@Injectable()
export class EmployeeService {
  constructor(private http: Http) {
  }

  getEmployees(): Promise<Employee[]> {
      console.log('getEmployees called on employee.service');
      return this.http.get('http://localhost:8080/ftp47/api/employees')

      .toPromise()
      .then(response => response.json() as Employee[])
      .catch(this.handleError);
  }
  getEmployeeDetails(empId): Promise<Employee> {
      console.log('getEmployeeDetails called on Employee.service');
      return this.http.get('http://localhost:8080/ftp47/api/employees/'+ empId)

      .toPromise()
      .then(response => response.json() as Employee)
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
      console.error('An error occurred', error); // for demo purposes only
      return Promise.reject(error.message || error);
  }
}
