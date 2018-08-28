package com.hexaware.ftp47.model;

import com.hexaware.ftp47.persistence.DbConnection;
import com.hexaware.ftp47.persistence.LeaveDetailsDAO;

import java.util.Objects;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author hexware leave details class.
 */
public class LeaveDetails {

  /**
   * @param leaveId          to store employee leave id.
   * @param empId            to store employee id.
   * @param noOfDays         to store no of days.
   * @param startDate        to store start date.
   * @param endDate          to store end date.
   * @param leaveType        to store leave type.
   * @param leaveStatus      to store leave status.
   * @param leavereason      to store leave reason.
   * @param leavereason      to store leave reason.
   * @param leaveAppliedDate to store leave applied date.
   * @param managerComments  to store manager comments.
   */
  private int leaveId;
  private int empId;
  private int noOfDays;
  private String startDate;
  private String endDate;
  private LeaveType leaveType;
  private LeaveStatus leaveStatus;
  private String leaveReason;
  private String leaveAppliedDate;
  private String managerComments;

  @Override
  public final boolean equals(final Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    LeaveDetails leave = (LeaveDetails) obj;
    if (Objects.equals(leaveId, leave.leaveId) && Objects.equals(empId, leave.empId)
        && Objects.equals(noOfDays, leave.noOfDays) && Objects.equals(startDate, leave.startDate)
        && Objects.equals(endDate, leave.endDate) && Objects.equals(leaveType, leave.leaveType)
        && Objects.equals(leaveStatus, leave.leaveStatus) && Objects.equals(leaveReason, leave.leaveReason)
        && Objects.equals(leaveAppliedDate, leave.leaveAppliedDate)
        && Objects.equals(managerComments, leave.managerComments)) {
      return true;
    }
    return false;
  }

  @Override
  public final int hashCode() {
    return Objects.hash(leaveId, empId, noOfDays, startDate, endDate, leaveType, leaveStatus, leaveReason,
        leaveAppliedDate, managerComments);
  }

  /**
   * @param argLeaveId to initialize employee id.
   */

  public LeaveDetails(final int argLeaveId) {
    this.leaveId = argLeaveId;
  }

  /**
   * empty constuctor.
   */

  public LeaveDetails() {

  }

  /**
   * @param argLeaveId          for id,
   * @param argEmpId            for name,
   * @param argNoOfDays         for date,
   * @param argStartDate        for Manager id,
   * @param argEndDate          for phone,
   * @param argLeaveType        for department,
   * @param argLeaveStatus      for leave status,
   * @param argLeaveReason      for email,
   * @param argLeaveAppliedDate for leave,
   * @param argManagerComments  for manager comments.
   */
  public LeaveDetails(final int argLeaveId, final int argEmpId, final int argNoOfDays, final String argStartDate,
      final String argEndDate, final LeaveType argLeaveType, final LeaveStatus argLeaveStatus,
      final String argLeaveReason, final String argLeaveAppliedDate, final String argManagerComments) {
    this.leaveId = argLeaveId;
    this.empId = argEmpId;
    this.noOfDays = argNoOfDays;
    this.startDate = argStartDate;
    this.endDate = argEndDate;
    this.leaveType = argLeaveType;
    this.leaveStatus = argLeaveStatus;
    this.leaveReason = argLeaveReason;
    this.leaveAppliedDate = argLeaveAppliedDate;
    this.managerComments = argManagerComments;
  }

  /**
   * Gets the LeaveId.
   * @return this Leave's ID.
   */
  public final int getLeaveId() {
    return leaveId;
  }

  /**
   * Gets the Employee's Id.
   * @return this Employee ID.
   */
  public final int getEmpId() {
    return empId;
  }

  /**
   * Gets the NoOfDays.
   * @return this No of Days of leave.
   */
  public final int getNoOfDays() {
    return noOfDays;
  }

  /**
   * Gets the StartDate.
   * @return this Start Date.
   */
  public final String getStartDate() {
    return startDate;
  }

  /**
   * Gets the EndDate.
   * @return this End Date.
   */
  public final String getEndDate() {
    return endDate;
  }
   /**
   * Gets the LeaveType.
   * @return this Leave Type.
   */
  public final LeaveType getLeaveType() {
    return leaveType;
  }
   /**
   * Gets the LeaveStatus.
   * @return this Leave's Status.
   */
  public final LeaveStatus getLeaveStatus() {
    return leaveStatus;
  }
   /**
   * Gets the LeaveType.
   * @return this Leave Type.
   */
  public final String getLeaveReason() {
    return leaveReason;
  }
   /**
   * Gets the LeaveAppliedDate.
   * @return this Leave Applied Date.
   */
  public final String getLeaveAppliedDate() {
    return leaveAppliedDate;
  }
   /**
   * Gets the ManagerComments.
   * @return this Manager's Comments.
   */
  public final String getManagerComments() {
    return managerComments;
  }
   /**
   * @param argLeaveId to set leave id.
   */
  public final void setLeaveId(final int argLeaveId) {
    this.leaveId = argLeaveId;
  }
   /**
   * @param argEmpId to set employee id.
   */
  public final void setEmpId(final int argEmpId) {
    this.empId = argEmpId;
  }
   /**
   * @param argNoOfDays is intialized for noOfDays
   */
  public final void setNoOfDays(final int argNoOfDays) {
    this.noOfDays = argNoOfDays;
  }
   /**
   * @param argStartDate is initialized for start date
   */
  public final void setStartDate(final String argStartDate) {
    this.startDate = argStartDate;
  }
   /**
   * @param argEndDate is initialized for end date
   */
  public final void setEndDate(final String argEndDate) {
    this.endDate = argEndDate;
  }
   /**
   * @param argLeaveType is initialized for type of leave
   */
  public final void setLeaveType(final LeaveType argLeaveType) {
    this.leaveType = argLeaveType;
  }
   /**
   * @param argLeaveStatus is initialized for leave status
   */
  public final void setLeaveStatus(final LeaveStatus argLeaveStatus) {
    this.leaveStatus = argLeaveStatus;
  }
   /**
   * @param argLeaveReason is initialized for leave reason
   */
  public final void setLeaveReason(final String argLeaveReason) {
    this.leaveReason = argLeaveReason;
  }

  /**
   * @param argLeaveAppliedDate is initialized for the leave applied date
   */
  public final void setLeaveAppliedDate(final String argLeaveAppliedDate) {
    this.leaveAppliedDate = argLeaveAppliedDate;
  }
   /**
   * @param argManagerComments is initialized for the manager comments
   */
  public final void setManagerComments(final String argManagerComments) {
    this.managerComments = argManagerComments;
  }
   /**
   * @return for overriding the toString() method.
   */
  public final String toString() {
    return " \n" + leaveId + " " + empId + " " + noOfDays + " " + startDate + " " + endDate + " " + leaveType
             + " " + leaveStatus + " " + leaveReason + " " + leaveAppliedDate + " " + managerComments + " ";
  }
  //  /**
  //  * @param startDATE to fetch leave details using employee id.
  //  * @return this
  //  * @throws ParseException to
  //  */
  // public static String checkDate(final String startDATE) throws ParseException {
  //   try {
  //     SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
  //     if (startDate.compareTo(dateFormat.parse(java.time.LocalDate.now().toString())) <= 0) {
  //       // throw new IllegalArgumentException("You cannot apply leave for same day or for past days");
  //       return "enter Valid startDate";
  //     }
  //   } catch (ParseException s) {
  //     s.printStackTrace();
  //   }
  //   return "enter valid StartDate";
  // }
  /**
     * @param empId for id.
     * @param startDATE for start date.
     * @param endDATE for end date.
     * @param leaveReason for leave type.
     * @param leaveType for leave type.
     * @return all.
     */
  public static String insertLeaveDetails(final int empId, final String startDATE, final String endDATE,
      final String leaveReason, final String leaveType) {
    try {
      Employee em = Employee.listById(empId);
      int leaveBalance = em.getEmpLeaveAvailable();
      LeaveStatus leaveStatus = LeaveStatus.PENDING;
      int leaveAvailable = 0;
      // LeaveType leaveType = LeaveType.EL;
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      Date startDate = dateFormat.parse(startDATE);
      if (startDate.compareTo(dateFormat.parse(java.time.LocalDate.now().toString())) <= 0) {
        // throw new IllegalArgumentException("You cannot apply leave for same day or for past days");
        System.out.println("You cannot apply leave for same day or for past days");
        return "enter Valid startDate";
      } else {
        Date endDate = dateFormat.parse(endDATE);
        int noofDays = (int) ((endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24));
        if (leaveBalance == 0) {
          throw new IllegalArgumentException("Insufficent leave balance");
        } else {
          noofDays++;
          leaveAvailable = (leaveBalance - noofDays);
          if (noofDays < 1) {
            throw new IllegalArgumentException("Enter a valid End Date");
          }
          if (leaveAvailable < noofDays) {
            throw new IllegalArgumentException("You dont have sufficient leave balance");
          }
        }
        LeaveDetails[] leaveemp = LeaveDetails.listByEmpId(empId);
        for (LeaveDetails l : leaveemp) {
          Date sD;
          Date eD;
          sD = dateFormat.parse(l.startDate);
          eD = dateFormat.parse(l.endDate);
          if ((startDate.compareTo(sD) > 0 || startDate.compareTo(sD) == 0) && (startDate.compareTo(eD) < 0
              || startDate.compareTo(eD) == 0)) {
            System.out.println("You have already applied on this dates");
            return "You have already applied on this dates with leaveId" + l.leaveId;
          } else if ((endDate.compareTo(sD) > 0 || endDate.compareTo(sD) == 0) && (endDate.compareTo(eD) < 0
              || endDate.compareTo(eD) == 0)) {
            System.out.println("You have already applied on this dates");
            return "your have already applied on this dates with leaveId" + l.leaveId;
          }
        }
        dao().enterLeaveDetails(empId, startDate, endDate, leaveType, leaveStatus, leaveReason, noofDays);
        Employee.updateLeavesAvailable(leaveAvailable, empId);
      }
    } catch (ParseException e) {
      System.out.println("Enter Valid EndDate");
    }
    System.out.println("Leave Applied Successfully");
    return "Leave Applied";
  }
  /**
   * updates employee leave details by leave id.
   * @param chooseOption   to get leave status.
   * @param managerComment to get leave id.
   * @param leaveId        to get comments.
   * @return string.
   */
  public static String updateStatus(final int chooseOption, final String managerComment, final int leaveId) {
    String leaveStatus = "";
    if (chooseOption == 1) {
      leaveStatus = "APPROVED";
    } else if (chooseOption == 2) {
      leaveStatus = "DENIED";
      incrementLeaveBalance(leaveId);
    } else {
      throw new IllegalArgumentException("Choose a valid Option");
    }
    dao().updateLeaveStatus(leaveStatus, managerComment, leaveId);
    return "Leave Status Changed";
  }
   /**
   * updates the leave balance.
   * @param leaveId to get leave id
   * @return increment
   */
  public static String incrementLeaveBalance(final int leaveId) {
    LeaveDetails ld = LeaveDetails.fetchLeaveId(leaveId);
    int empId = ld.getEmpId();
    int noOfDays = ld.getNoOfDays();
    Employee em = Employee.listById(empId);
    int leaveBal = em.getEmpLeaveAvailable();
    int num = leaveBal + noOfDays;
    Employee.updateLeavesAvailable(num, empId);
    return "LeaveBalanceIncremented";
  }
   /**
   * The dao for employee.
   */
  private static LeaveDetailsDAO dao() {
    DbConnection db = new DbConnection();
    return db.getConnect().onDemand(LeaveDetailsDAO.class);
  }
   /**
   * list employee leave history details by id.
   * @param empID id to get employee details.
   * @return LeaveDetails
   */
  public static LeaveDetails[] listByEmpId(final int empID) {
    List<LeaveDetails> ls = dao().findHistory(empID);
    return ls.toArray(new LeaveDetails[ls.size()]);
  }
   /**
   * list employee pending leave details by id.
   * @param empID id to get employee details.
   * @return LeaveDetails
   */
  public static LeaveDetails[] listPendingLeaves(final int empID) {
    List<LeaveDetails> ps = dao().findPending(empID);
    return ps.toArray(new LeaveDetails[ps.size()]);
  }
   /**
   * list the employee by id.
   * @param empID id to get leave details.
   * @return Leavedetails.
   */
  // public static LeaveDetails[] listById(final int empID) {
  //   List<LeaveDetails> ld = dao().find(empID);
  //   return ld.toArray(new LeaveDetails[ld.size()]);
  // }
   /**
   * list the manager's employees' pending details by id.
   * @param empID id to get employee details.
   * @return Employee
   */
  public static LeaveDetails[] listByManagerId(final int empID) {
    List<LeaveDetails> pl = dao().findManagerPending(empID);
    return pl.toArray(new LeaveDetails[pl.size()]);
  }
   /**
   * gets the leave id of employee.
   * @param leaveId to get leave id
   * @return details.
   */
  public static LeaveDetails fetchLeaveId(final int leaveId) {
    return dao().findLeaveId(leaveId);
  }
}
