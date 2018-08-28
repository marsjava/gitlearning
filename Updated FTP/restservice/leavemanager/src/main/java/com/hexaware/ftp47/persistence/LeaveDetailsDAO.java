package com.hexaware.ftp47.persistence;

import com.hexaware.ftp47.model.LeaveDetails;
import com.hexaware.ftp47.model.LeaveStatus;
import com.hexaware.ftp47.model.LeaveType;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.Date;
import java.util.List;
/**
 * The DAO class for employee.
 */
public interface LeaveDetailsDAO  {

  /**
   * return all the leave id details of the selected employee.
   * @param leaveID for the id of the employee
   * @return the leave details object
   */
  @SqlQuery("SELECT * FROM LEAVE_DETAILS WHERE LEAVE_ID = :leaveID")
  @Mapper(LeaveDetailsMapper.class)
  LeaveDetails findLeaveId(@Bind("leaveID") int leaveID);
  /**
   * return all the leave history details of the selected employee.
   * @param empID for the id of the employee
   * @return the employee object
   */
  @SqlQuery("SELECT * FROM LEAVE_DETAILS WHERE EMP_ID = :empID")
  @Mapper(LeaveDetailsMapper.class)
  List<LeaveDetails> findHistory(@Bind("empID") int empID);

  /**
   * return all the leave pending details of the selected employee.
   * @param empID for the id of the employee
   * @return the employee object
   */
  @SqlQuery("SELECT * FROM LEAVE_DETAILS WHERE EMP_ID = :empID  AND LEAVE_STATUS = 'PENDING'")
  @Mapper(LeaveDetailsMapper.class)
  List<LeaveDetails> findPending(@Bind("empID") int empID);

  /**
   * Return all the pending leave details of the selected employee for manager.
   * @param empID for the id of the leave details.
   * @return the leave details object
   */
  @SqlQuery("SELECT * FROM LEAVE_DETAILS WHERE (EMP_ID) IN"
      + "(SELECT EMP_ID FROM EMPLOYEE WHERE MANAGER_ID=:empID) AND LEAVE_STATUS='PENDING' ORDER BY EMP_ID, START_DATE")
  @Mapper(LeaveDetailsMapper.class)
  List<LeaveDetails> findManagerPending(@Bind("empID") int empID);
   /**
   * returns all the leave details of the selected employee.
   * @param empID for the id of the employee
   * @param startDATE for the id of the employee
   * @param endDATE for the id of the employee
   * @param leaveTYPE for the id of the employee
   * @param leaveSTATUS for the id of the employee
   * @param leaveREASON for id of the employee
   * @param noofDAYS for the id of the employe
   */
  @SqlUpdate("INSERT INTO LEAVE_DETAILS(EMP_ID,START_DATE,END_DATE,LEAVE_STATUS,LEAVE_REASON,"
      + "LEAVE_APPLIED_DATE,LEAVE_TYPE, NO_OF_DAYS) VALUES (:empID,:startDATE,:endDATE,:leaveSTATUS,:leaveREASON,"
      + "(select curdate()),:leaveTYPE,:noofDAYS)")
  void  enterLeaveDetails(@Bind("empID") int empID, @Bind("startDATE") Date startDATE, @Bind("endDATE")
      Date endDATE,  @Bind("leaveTYPE")
      String leaveTYPE, @Bind("leaveSTATUS") LeaveStatus leaveSTATUS,
      @Bind("leaveREASON") String leaveREASON, @Bind("noofDAYS") int noofDAYS);
  /**
   * update all leave details of the selected employee.
   * @param leaveSTATUS for the status of the leave applied.
   * @param managerCOMMENT for the comment of manager.
   * @param leaveID for leave id.
   */
  @SqlUpdate("UPDATE LEAVE_DETAILS SET LEAVE_STATUS= :leaveSTATUS,"
      + " MANAGER_COMMENTS= :managerCOMMENT WHERE LEAVE_ID= :leaveID")
  @Mapper(LeaveDetailsMapper.class)
  void updateLeaveStatus(@Bind("leaveSTATUS") String leaveSTATUS, @Bind("managerCOMMENT")
        String managerCOMMENT, @Bind("leaveID") int leaveID);
    /**
   * returns all the leave details of the selected employee.
   * @param empID for the id of the employee
   * @param startDATE for the id of the employee
   * @param endDATE for the id of the employee
   * @param leaveTYPE for the id of the employee
   * @param leaveSTATUS for the id of the employee
   * @param leaveREASON for id of the employee
   * @param noofDAYS for the id of the employe
   * @param leaveID for leave id.
   */
  @SqlUpdate("UPDATE LEAVE_DETAILS SET START_DATE= :startDATE,END_DATE=:endDATE,LEAVE_STATUS=:leaveSTATUS,"
        + " LEAVE_REASON=:leaveREASON,LEAVE_TYPE=:leaveTYPE WHERE LEAVE_ID= :leaveID")
  @Mapper(LeaveDetailsMapper.class)
  void updateLeaveDetails(@Bind("leaveID") int leaveID, @Bind("empID") int empID, @Bind("startDATE") Date startDATE,
        @Bind("endDATE") Date endDATE,  @Bind("leaveTYPE")LeaveType leaveTYPE, @Bind("leaveSTATUS")
        LeaveStatus leaveSTATUS, @Bind("leaveREASON") String leaveREASON, @Bind("noofDAYS") int noofDAYS);
  /**
  * close with no args is used to close the connection.
  */
  void close();
}
