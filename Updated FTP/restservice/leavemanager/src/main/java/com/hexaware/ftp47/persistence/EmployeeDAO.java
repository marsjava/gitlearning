package com.hexaware.ftp47.persistence;

import com.hexaware.ftp47.model.Employee;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;

/**
 * The DAO class for employee.
 */
public interface EmployeeDAO  {
  /**
   * return all the details of all the employees.
   * @return the employee array
   */
  @SqlQuery("SELECT * FROM EMPLOYEE")
  @Mapper(EmployeeMapper.class)
  List<Employee> list();

  /**
   * return all the details of the selected employee.
   * @param empID the id of the employee
   * @return the employee object
   */
  @SqlQuery("SELECT * FROM EMPLOYEE WHERE EMP_ID = :empID")
  @Mapper(EmployeeMapper.class)
  Employee find(@Bind("empID") int empID);
 /**
   * update leave details.
   * @param leaveAVAILABLE the status of the leave applied.
   * @param empID the comment of manager
   */
  @SqlUpdate("UPDATE EMPLOYEE SET EMP_LEAVE_AVAILABLE= :leaveAVAILABLE WHERE EMP_ID= :empID")
  void updateLeaves(@Bind("leaveAVAILABLE") int leaveAVAILABLE,
      @Bind("empID") int empID);
  /**
   * checks if manager id is valid.
   * @return the manager id
   * @param empID for binding emp id
   */
  @SqlQuery("SELECT * FROM EMPLOYEE WHERE MANAGER_ID = :empID")
  @Mapper(EmployeeMapper.class)
  Employee findManager(@Bind("empID") int empID);

  /**
  * close with no args is used to close the connection.
  */
  void close();
}
