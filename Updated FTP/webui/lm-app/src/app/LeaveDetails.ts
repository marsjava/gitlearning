export class LeaveDetails {
    leaveId: number;
    empId: number;
    noOfDays:number;
    startDate: String;
    endDate: String;
    leaveType: String;
    leaveStatus: String;
    leaveReason: String;
    leaveAppliedDate: String;
    managerComments: String;

    constructor(argleaveId: number, argempId: number, argnoOfDays: number,argstartDate: String, argendDate: String,
        argleaveType: String, argleaveStatus: String, argleaveReason: String, argleaveAppliedDate: String, argmanagerComments: String) {
      this.leaveId = argleaveId;
      this.empId = argempId;
      this.noOfDays = argnoOfDays;
      this.startDate = argstartDate;
      this.endDate = argendDate;
      this.leaveType = argleaveType;
      this.leaveStatus = argleaveStatus;
      this.leaveReason = argleaveReason;
      this.leaveAppliedDate = argleaveAppliedDate;
      this.managerComments = argmanagerComments;
    }
}
