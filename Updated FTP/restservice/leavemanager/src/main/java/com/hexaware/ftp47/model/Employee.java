package com.hexaware.ftp47.model;

import com.hexaware.ftp47.persistence.DbConnection;
import com.hexaware.ftp47.persistence.EmployeeDAO;

import java.util.Objects;
import java.util.List;

/**
 * Employee class to store employee personal details.
 * @author hexware
 */
public class Employee {

  /**
   * @param empId to store employee id.
   * @param empName to store employee name.
   * @param empdate to store employee date of birth.
   * @param managerId to store manager id.
   * @param empPhone to store employee phone number.
   * @param empDept to store employee department.
   * @param empEmail to store employee email.
   * @param empLeaveAvailable to store employee's available leaves.
   */
  private int empId;
  private String empName;
  private String empDate;
  private int managerId;
  private long empPhone;
  private String empDept;
  private String empEmail;
  private int empLeaveAvailable;

  @Override
  public final boolean equals(final Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Employee emp = (Employee) obj;
    if (Objects.equals(empId, emp.empId) && Objects.equals(empName, emp.empName) && Objects.equals(empDate, emp.empDate)
        && Objects.equals(managerId, emp.managerId) && Objects.equals(empPhone, emp.empPhone)
        && Objects.equals(empDept, emp.empDept) && Objects.equals(empEmail, emp.empEmail)
        && Objects.equals(empLeaveAvailable, emp.empLeaveAvailable)) {
      return true;
    }
    return false;
  }

  @Override
  public final int hashCode() {
    return Objects.hash(empId, empName, empDate, managerId, empPhone, empDept, empEmail, empLeaveAvailable);
  }

  /**
   * @param argEmpId to initialize employee id.
   */

  public Employee(final int argEmpId) {
    this.empId = argEmpId;
  }

  /**
   * empty constuctor.
   */

  public Employee() {

  }

  /**
   * @param argEmpId             for id,
   * @param argEmpName           for name,
   * @param argEmpDate           for date,
   * @param argManagerId         for Manager id,
   * @param argEmpPhone          for phone,
   * @param argEmpDept           for department,
   * @param argEmpEmail          for email,
   * @param argEmpLeaveAvailable for leave available
   */
  public Employee(final int argEmpId, final String argEmpName, final String argEmpDate, final int argManagerId,
      final long argEmpPhone, final String argEmpDept, final String argEmpEmail, final int argEmpLeaveAvailable) {
    this.empId = argEmpId;
    this.empName = argEmpName;
    this.empDate = argEmpDate;
    this.managerId = argManagerId;
    this.empPhone = argEmpPhone;
    this.empDept = argEmpDept;
    this.empEmail = argEmpEmail;
    this.empLeaveAvailable = argEmpLeaveAvailable;
  }

  /**
   * Gets the EmployeeId.
   * @return this Employee's ID.
   */
  public final int getEmpId() {
    return empId;
  }
  /**
   * Gets the EmployeeName.
   * @return this Employee's Name.
   */
  public final String getEmpName() {
    return empName;
  }

  /**
   * Gets the EmployeeDate.
   * @return this Employee's Date.
   */
  public final String getEmpDate() {
    return empDate;
  }

  /**
   * Gets the ManagerID.
   * @return this Manager's ID.
   */
  public final int getManagerId() {
    return managerId;
  }

  /**
   * Gets the EmployeePhone.
   * @return this Employee's Phone.
   */
  public final long getEmpPhone() {
    return empPhone;
  }

  /**
   * Gets the EmployeeDept.
   * @return this Employee's Department.
   */
  public final String getEmpDept() {
    return empDept;
  }

  /**
   * Gets the EmployeeEmail.
   * @return this Employee's Email.
   */
  public final String getEmpEmail() {
    return empEmail;
  }

  /**
   * Gets the EmpLeaveAvailable.
   * @return this Employee's Leave Available.
   */
  public final int getEmpLeaveAvailable() {
    return empLeaveAvailable;
  }

  /**
   * @param argEmpId to set employee id.
   */
  public final void setEmpId(final int argEmpId) {
    this.empId = argEmpId;
  }

  /**
   * @param argEmpName is intialized for name.
   */
  public final void setEmpName(final String argEmpName) {
    this.empName = argEmpName;
  }

  /**
   * @param argEmpDate is initialized for date.
   */
  public final void setEmpDate(final String argEmpDate) {
    this.empDate = argEmpDate;
  }

  /**
   * @param argManagerId is initialized for manager id.
   */
  public final void setManagerId(final int argManagerId) {
    this.managerId = argManagerId;
  }

  /**
   * @param argEmpPhone is initialized for phone number.
   */
  public final void setEmpPhone(final long argEmpPhone) {
    this.empPhone = argEmpPhone;
  }

  /**
   * @param argEmpDept is initialized for department.
   */
  public final void setEmpDept(final String argEmpDept) {
    this.empDept = argEmpDept;
  }

  /**
   * @param argEmpEmail is initialized for email.
   */
  public final void setEmpEmail(final String argEmpEmail) {
    this.empEmail = argEmpEmail;
  }

  /**
   * @param argEmpLeaveAvailable is initialized for the leave balance.
   */

  public final void setEmpLeaveAvailable(final int argEmpLeaveAvailable) {
    this.empLeaveAvailable = argEmpLeaveAvailable;
  }

  /**
   * Gets the EmployeeDetails.
   * @return all the details.
   */
  public final String toString() {
    return "\n" + empId + "  " + empName + "  " + empDate + "  " + managerId + "  " + empPhone + "  " + empDept
       + "  " + empEmail + "  " + empLeaveAvailable + "  ";
  }
  /**
   * updates the employee leave available details.
   * @param leaveAVAILABLE for available leaves.
   * @param empID for empID
   * @return the updated balance
   */
  public static String updateLeavesAvailable(final int leaveAVAILABLE, final int empID) {
    dao().updateLeaves(leaveAVAILABLE, empID);
    return "LeaveBalanceUpdated";
  }
  /**
   * The dao for employee.
   */
  private static EmployeeDAO dao() {
    DbConnection db = new DbConnection();
    return db.getConnect().onDemand(EmployeeDAO.class);
  }

  /**
   * list all employee details.
   * @return all employees' details
   */
  public static Employee[]  listAll() {

    List<Employee> es = dao().list();
    return es.toArray(new Employee[es.size()]);
  }

  /**
   * list employee details by id.
   * @param empID id to get employee details.
   * @return Employee
   */
  public static Employee listById(final int empID) {
    return dao().find(empID);
  }
  /**
  * checks for the manager by id.
  * @param empId id to get employee details.
  * @return Employee
  */
  public static Employee checkManager(final int empId) {
    return dao().findManager(empId);
  }
}
