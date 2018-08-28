import { Injectable } from '@angular/core';
import { LeaveDetails } from './LeaveDetails';

import { Observable } from 'rxjs/Rx';
import 'rxjs/add/operator/toPromise';
import { Http,HttpModule } from '@angular/http';


@Injectable()
export class LeaveDetailsService {
  constructor(private http: Http) {
  }
  url;
  leavehistory = 'http://localhost:8080/ftp47/api/leave/'; 
  getLeaveHistory(empId): Promise<LeaveDetails[]> {
      console.log('getLeaveHistory called on LeaveDetails.service');
      return this.http.get('http://localhost:8080/ftp47/api/leave/' + empId + '/history')

          .toPromise()
          .then(response => response.json() as LeaveDetails[])
          .catch(this.handleError);
  }
  getLeavePending(empId): Promise<LeaveDetails[]> {
      console.log('getLeavePending called on LeaveDetails.service');
      return this.http.get('http://localhost:8080/ftp47/api/leave/' + empId + '/pending')
          .toPromise()
          .then(response => response.json() as LeaveDetails)
          .catch(this.handleError);
  }
  setApply(empId, startDate, endDate, leaveReason, selectedOption): Observable<String> {
      this.url = "http://localhost:8080/ftp47/api/leave/" + empId + "/apply/";
      return this.http.post(this.url, { "empId": empId, "startDate": startDate, "endDate": endDate, "leaveReason": leaveReason, "leaveType": selectedOption })
          .map(response => response.text())

          .catch((error: any) => Observable.throw(error.toString() || 'Server error'));

  }
  getLeaveId(leaveId): Promise<LeaveDetails> {
      console.log('getLeavePending called on LeaveDetails.service');
      return this.http.get('http://localhost:8080/ftp47/api/leave/' + leaveId + '/leaveid')
          .toPromise()
          .then(response => response.json() as LeaveDetails)
          .catch(this.handleError);
  }
  getManagerPending(empId): Promise<LeaveDetails[]> {
      console.log('getManagerPending called on LeaveDetails.service');
      return this.http.get('http://localhost:8080/ftp47/api/leave/'+ empId+'/managerpending')
      .toPromise()
      .then(response => response.json() as LeaveDetails[])
      .catch(this.handleError);
  }
  setStatus(empId, leaveStatus, managerComments, leaveId): Observable<string> {
      // console.log('getApproval called on LeaveDetails.service');
      this.url = "http://localhost:8080/ftp47/api/leave/" + empId + '/' + leaveStatus + '/approvedeny/';
      return this.http.put(this.url, { "managerComments": managerComments, "leaveId": leaveId })
          .map(response => response.text())
          .catch((error: any) => Observable.throw(error.toString() || 'Server error'));

  }

  private handleError(error: any): Promise<any> {
      console.error('An error occurred', error); // for demo purposes only
      return Promise.reject(error.message || error);
  }
}
