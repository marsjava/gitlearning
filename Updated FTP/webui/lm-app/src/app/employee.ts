export class Employee {
    empId: number;
    empName: String;
    empDate: String;
    managerId: number;
    empPhone: number;
    empDept: String;
    empEmail: String;
    empLeaveAvailable: number;

    constructor(argempId: number, argempName: String, argempDate: String,
        argmanagerId: number, argempPhone: number,argempDept: String, argempEmail: String, argempLeaveAvailable: number) {
      this.empId = argempId;
      this.empName = argempName;
      this.empDate = argempDate;
      this.managerId = argmanagerId;
      this.empPhone = argempPhone;
      this.empDept = argempDept;
      this.empEmail = argempEmail;
      this.empLeaveAvailable = argempLeaveAvailable;
      
      
    }
}

    