package com.hexaware.ftp47.integration.test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

import javax.management.RuntimeErrorException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
public class CommonUtilLeave {
    public static final String host;
    public static final String port;
    public static final String webapp;
    public static final String uri_prefix;
    static {
        host = System.getProperty("service.host", "localhost");
        port = System.getProperty("service.port", "8080");
        webapp = System.getProperty("service.webapp", "ftp47");
        uri_prefix = "http://" + host + ":" + port + "/" + webapp;
    }
    public static URI getURI(String path) throws URISyntaxException {
        return new URI(uri_prefix + path);
    }
}

class LeaveDetails {
    public int leaveId;
    public int empId;
    public int noOfDays;
    public String startDate;
    public String endDate;
    public String leaveType;
    public String leaveStatus;
    public String leaveReason;
    public String leaveAppliedDate;
    public String managerComments;

    public LeaveDetails() {
    }

    public LeaveDetails(final int empId) {
      this.empId = empId;
    }

    public LeaveDetails(final int argLeaveId, final int argEmpId, final int argNoOfDays, final String argStartDate,
            final String argEndDate, final String argLeaveType, final String argLeaveStatus,
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
    public String toString() {
      try {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(this);
      } catch(Exception e) {
        throw new RuntimeException(e);
      }
    }
}
