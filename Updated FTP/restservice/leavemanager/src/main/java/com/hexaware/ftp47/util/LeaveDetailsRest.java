package com.hexaware.ftp47.util;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.hexaware.ftp47.model.Employee;
import com.hexaware.ftp47.model.LeaveDetails;
import com.hexaware.ftp47.model.LeaveStatus;

/**
 * This class provides a REST interface for the employee entity.
 */
@Path("/leave")
public class LeaveDetailsRest {

    /**
     * Returns a list of all the employees.
     * @return a list of all the employees
     * @param id empId.
     */
  @GET
  @Path("{id}/history")
  @Produces(MediaType.APPLICATION_JSON)
  public final LeaveDetails[] leaveHistory(@PathParam("id") final int id) {
    final LeaveDetails[] leave = LeaveDetails.listByEmpId(id);
    if (leave.length == 0) {
      throw new NotFoundException("No leaves found for employee: " + id);
    }
    return leave;
  }

    /**
     * Returns a specific employee's details.
     * @param id the id of the employee
     * @return the employee details
     */
  @GET
  @Path("{id}/pending")
  @Produces(MediaType.APPLICATION_JSON)
  public final LeaveDetails[] leavePending(@PathParam("id") final int id) {
    final LeaveDetails[] leave = LeaveDetails.listPendingLeaves(id);
    if (leave.length == 0) {
      throw new NotFoundException("No pending leaves found for employee: " + id);
    }
    return leave;
  }
  /**
     * Returns a specific employee's details.
     * @param id the id of the employee
     * @param leave leave
     * @return the employee details
     */
  @POST
  @Path("{id}/apply")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public final String leaveApply(@PathParam("id") final int id,
      final LeaveDetails leave) {
    String leaveType = String.valueOf(leave.getLeaveType());
    Employee  emp = Employee.listById(id);
    if (emp == null) {
      throw new NotFoundException("No pending leaves found for employee: " + id);
    }
    String apply = LeaveDetails.insertLeaveDetails(leave.getEmpId(), leave.getStartDate(), leave.getEndDate(),
          leave.getLeaveReason(), leaveType);
    return apply;
  }

  //   /**
  //    * Returns a specific employee's details.
  //    * @param empid the id of the employee
  //    * @param leaveid leaveid
  //    * @param leave leave
  //    * @return the employee details
  //    */
  // @POST
  // @Path("{leaveid}/{empid}/apply")
  // @Produces(MediaType.APPLICATION_JSON)
  // @Consumes(MediaType.APPLICATION_JSON)
  // public final String updateLeaveApply(@PathParam("empid") final int empid, @PathParam("leaveid") final int leaveid,
  //     final LeaveDetails leave) {
  //   Employee emp = Employee.listById(empid);
  //   if (emp == null) {
  //     throw new NotFoundException("No pending leaves found for employee: " + empid);
  //   }
  //   String apply = LeaveDetails.updateLeaveDetails(empid, leaveid, leave.getStartDate(), leave.getEndDate(),
  //       leave.getLeaveReason());
  //   return apply;
  // }

  /**
   * Returns a specific employee's details.
   * @param id the id of the employee
   * @return the employee details
   */
  @GET
  @Path("{id}/managerpending")
  @Produces(MediaType.APPLICATION_JSON)
  public final LeaveDetails[] managerleavePending(@PathParam("id") final int id) {
    final LeaveDetails[] leave = LeaveDetails.listByManagerId(id);
    if (leave.length == 0) {
      throw new NotFoundException("No pending leaves found! ");
    }
    return leave;
  }

/**
  * Returns a specific employee's details.
  * @param empId the id of the employee
  * @param leave leave
  * @param leaveStatus leaveStatus
  * @return the employee details
  */
  @PUT
  @Path("{empId}/{leaveStatus}/approvedeny")
  @Produces(MediaType.APPLICATION_JSON)

  public final String leaveApproveDeny(@PathParam("empId") final int empId, @PathParam("leaveStatus") final
      LeaveStatus leaveStatus, final LeaveDetails leave) {
    Employee  emp = Employee.listById(empId);
    if (emp == null) {
      throw new NotFoundException("No pending leaves found for employee: " + empId);
    }
    if (leaveStatus == LeaveStatus.APPROVED) {
      int chooseOption = 1;
      String apply = LeaveDetails.updateStatus(chooseOption, leave.getManagerComments(), leave.getLeaveId());
      return apply;
    } else if (leaveStatus == LeaveStatus.DENIED) {
      int chooseOption = 2;
      String apply = LeaveDetails.updateStatus(chooseOption, leave.getManagerComments(), leave.getLeaveId());
      return apply;
    }
    return "Updated";
  }
  /**
   * Returns a specific employee's details.
   * @param id the id of the employee
   * @return the employee details
   */
  @GET
  @Path("{id}/leaveid")
  @Produces(MediaType.APPLICATION_JSON)
  public final LeaveDetails leaveIdDetails(@PathParam("id") final int id) {
    final LeaveDetails leave = LeaveDetails.fetchLeaveId(id);
    if (leave == null) {
      throw new NotFoundException("No pending leaves found! ");
    }
    return leave;
  }
}
