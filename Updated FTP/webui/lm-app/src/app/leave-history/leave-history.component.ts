import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpModule } from '@angular/http';

import { LeaveDetails } from '../LeaveDetails';
import { ActivatedRoute } from '@angular/router';
import { LeaveDetailsService } from '../leave-details.service';




@Component({
  selector: 'app-leave-history',
  templateUrl: './leave-history.component.html',
  styleUrls: ['./leave-history.component.css'],
  providers: [LeaveDetailsService]
})
export class LeaveHistoryComponent implements OnInit {
  employeeId:number;
  constructor(private leaveDetailsService: LeaveDetailsService, private activeRoute: ActivatedRoute,private route: Router) { 
    this.employeeId = this.activeRoute.snapshot.params['employeeId'];
  }
  empId=this.employeeId;
  title = 'Leave Management Application';
  leaveHistory: LeaveDetails[];
  getLeaveHistory(empId): void {
      this.leaveDetailsService.getLeaveHistory(empId).then(leaveHistory => {
      console.log('getLeaveHistory promise resolved : ' + leaveHistory.length);
      this.leaveHistory = leaveHistory;
      console.log("helloooo",this.leaveHistory);
    }
    );}
    onback() {
        this.route.navigate(['/dashboard', this.employeeId]); 
    }
    onapply() {
        this.route.navigate(['/apply', this.employeeId]); 
    }
  ngOnInit(): void {
      this.getLeaveHistory(this.employeeId);
    }
}
