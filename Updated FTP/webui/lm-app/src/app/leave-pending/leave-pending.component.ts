import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpModule } from '@angular/http';

import { LeaveDetails } from '../LeaveDetails';
import { ActivatedRoute } from '@angular/router';
import { LeaveDetailsService } from '../leave-details.service';

@Component({
  selector: 'app-leave-pending',
  templateUrl: './leave-pending.component.html',
  styleUrls: ['./leave-pending.component.css'],
  providers: [LeaveDetailsService]
})
export class LeavePendingComponent implements OnInit {
  employeeId:number;
  constructor(private leaveDetailsService: LeaveDetailsService, private activeRoute: ActivatedRoute,private route: Router) { 
    this.employeeId = this.activeRoute.snapshot.params['employeeId'];
  }
  empId=this.employeeId;
  title = 'Leave Management Application';
  leavePending: LeaveDetails[];
  getLeavePending(empId): void {
      this.leaveDetailsService.getLeavePending(empId).then(leavePending => {
      console.log('getLeaveHistory promise resolved : ' + leavePending.length);
      this.leavePending = leavePending;
      console.log("helloooo",this.leavePending);
    }
    );}
    onback() {
        this.route.navigate(['/dashboard', this.employeeId]); 
    }
    ngOnInit(): void {
      this.getLeavePending(this.employeeId);
    }
}
