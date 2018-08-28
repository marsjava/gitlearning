package com.hexaware.ftp47.util;

import java.util.Scanner;

import com.hexaware.ftp47.model.Employee;
import com.hexaware.ftp47.model.LeaveDetails;
// import com.hexaware.ftp47.model.LeaveType;
// import com.hexaware.ftp47.model.LeaveType;

/**
 * Class CliMain provides the command line interface to the leavemanagement
 * application.
 */
public class CliMain {
  private Scanner option = new Scanner(System.in, "UTF-8");
/**
 * mainMenu displays the options for leave management.
 * Displays all employees info.
 * Displays selected employee's info.
 * Displays the employee's leave history.
 * Displays the employee's pending leaves.
 * Displays option for employee to apply a leave.
 * Displays option for manager to approve or deny employee's leave.
 * Displays option for exit.
 */

  private void mainMenu() {
    System.out.println("Leave Management System");
    System.out.println("-----------------------");
    System.out.println("1. List All Employees Info");
    System.out.println("2. Display Employee Info");
    System.out.println("3. Display the Leave History");
    System.out.println("4. Pending Leaves");
    System.out.println("5. Apply for Leave");
    System.out.println("6. Approval/Denial By Manager");
    System.out.println("7. Exit");
    System.out.println("Enter your choice:");
    int menuOption = option.nextInt();
    mainMenuDetails(menuOption);
  }
  private void mainMenuDetails(final int selectedOption) {
    switch (selectedOption) {
      case 1:
        listEmployeesDetails();
        break;
      case 2:
        listEmployeeDetail();
        break;
      case 3:
        listLeaveHistory();
        break;
      case 4:
        listPendingLeaves();
        break;
      case 5:
        applyLeave();
        break;
      case 6:
        leaveApproval();
        break;
      case 7:
        // halt since normal exit throws a stacktrace due to jdbc threads not responding
        Runtime.getRuntime().halt(0);
      default:
        System.out.println("Choose either 1, 2, 3 or 4");
    }
    mainMenu();
  }
/**
 * Displays all the details of all employees.
 */
  private void listEmployeeDetail() {
    System.out.println("Enter an Employee Id");
    int empId = option.nextInt();
    Employee employee = Employee.listById(empId);
    if (employee == null) {
      System.out.println("Sorry, No such employee");
    } else {
      System.out.println(employee.toString());
    }
  }

 /**
 * Displays all the details of selected employees.
 */
  private void listEmployeesDetails() {
    Employee[] employee = Employee.listAll();
    for (Employee e : employee) {
      System.out.println(e.toString());
    }
  }
/**
 * Displays all the leave history details of the employee.
 */
  private void listLeaveHistory() {
    System.out.println("Enter an Employee Id");
    int empId = option.nextInt();
    Employee employee = Employee.listById(empId);
    //LeaveDetails[] leaveemp = LeaveDetails.listByEmpId(empId);
    if (employee == null) {
      System.out.println("Sorry, No such employee");
    } else {
      LeaveDetails[] leaveemp = LeaveDetails.listByEmpId(empId);
      if (leaveemp.length == 0) {
        System.out.println("No Leaves Applied");
      } else {
        for (LeaveDetails l : leaveemp) {
          System.out.println(l.toString());
        }
      }
    }
  }
/**
 * Displays all the pending leave details of the employee.
 */
  private void listPendingLeaves() {
    System.out.println("Enter an Employee Id");
    int empId = option.nextInt();
    Employee employee = Employee.listById(empId);
    //LeaveDetails[] leaveemp = LeaveDetails.listByEmpId(empId);
    if (employee == null) {
      System.out.println("Sorry, No such employee");
    } else {
      LeaveDetails[] leavepending = LeaveDetails.listPendingLeaves(empId);
      if (leavepending.length == 0) {
        System.out.println("No Pending Leaves");
      } else {
        for (LeaveDetails p : leavepending) {
          System.out.println(p.toString());
        }
      }
    }
  }
/**
 * Displays all the leave details to apply  leave for the employee.
 */
  private void applyLeave() {
    System.out.println("Enter your Employee Id");
    int empId = option.nextInt();
    Employee  employee = Employee.listById(empId);
    if (employee == null) {
      System.out.println("Sorry, No such employee");
    } else {
      try {
        int leaveBalance = employee.getEmpLeaveAvailable();
        System.out.println("Your Leave Balance is : " + leaveBalance);
        System.out.println("Enter start date(yyyy-MM-dd) ");
        String startDate = option.next();
        System.out.println("Enter end date(yyyy-MM-dd) ");
        String endDate = option.next();
        System.out.println("Enter the type");
        String leaveType = option.next();
        System.out.println("Enter the reason");
        String leaveReason = option.next();
        leaveReason += option.nextLine();
        System.out.println("choose the type of leave");
        LeaveDetails.insertLeaveDetails(empId, startDate, endDate, leaveReason, leaveType);
        System.out.println("succesful");
      } catch (IllegalArgumentException i) {
        System.out.println(i.getMessage());
        System.out.println("Please enter your Leave Details again");
        applyLeave();
      }
    }
  }
  
/**
 * Displays all the details to approve or deny the leave of the employee for manager.
 */
  private void leaveApproval() {
    int leaveId = 0;
    String managerComment = "";
    System.out.println("Enter an Manager Id");
    int empId = option.nextInt();
    Employee emp = Employee.checkManager(empId);
    if (emp == null) {
      System.out.println("Not a Manager");
    } else {
      LeaveDetails[] leavedetails = LeaveDetails.listByManagerId(empId);
      if (leavedetails.length == 0) {
        System.out.println("No pending leaves");
      } else {
        try {
          for (LeaveDetails l : leavedetails) {
            System.out.println(l.toString());
          }
          System.out.println("Enter the leave Id: ");
          leaveId = option.nextInt();
          System.out.println("Choose 1 to approve and 2 to deny");
          int chooseOption = option.nextInt();
          System.out.println("Enter the Comments");
          managerComment = option.next();
          managerComment += option.nextLine();
          LeaveDetails.updateStatus(chooseOption, managerComment, leaveId);
          System.out.println("Status Updated Successfully");
        } catch (IllegalArgumentException i) {
          System.out.println(i.getMessage());
          System.out.println(" Please enter your ManagerId again");
          leaveApproval();
        }
      }
    }
  }
  /**
   * The main entry point.
   * @param ar the list of arguments
   */
  public static void main(final String[] ar) {
    final CliMain mainObj = new CliMain();
    mainObj.mainMenu();
  }
}
