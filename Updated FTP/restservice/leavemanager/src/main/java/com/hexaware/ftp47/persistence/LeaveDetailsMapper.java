package com.hexaware.ftp47.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.tweak.ResultSetMapper;
import org.skife.jdbi.v2.StatementContext;
import com.hexaware.ftp47.model.LeaveStatus;
import com.hexaware.ftp47.model.LeaveType;
import com.hexaware.ftp47.model.LeaveDetails;
/**
 * Mapper class to map from result set to employee object.
 */
public class LeaveDetailsMapper implements ResultSetMapper<LeaveDetails> {
  /**
   * @param idx the index
   * @param rs the resultset
   * @param ctx the context
   * @return the mapped employee object
   * @throws SQLException in case there is an error in fetching data from the resultset
   */
  public final LeaveDetails map(final int idx, final ResultSet rs, final StatementContext ctx) throws SQLException {
    /**
     *@return LeaveDetails
     */
    return new LeaveDetails(rs.getInt("LEAVE_ID"), rs.getInt("EMP_ID"), rs.getInt("NO_OF_DAYS"),
    rs.getString("START_DATE"), rs.getString("END_DATE"), LeaveType.valueOf(rs.getString("LEAVE_TYPE")),
    LeaveStatus.valueOf(rs.getString("LEAVE_STATUS")),
    rs.getString("LEAVE_REASON"), rs.getString("LEAVE_APPLIED_DATE"), rs.getString("MANAGER_COMMENTS"));

  }
}
