package com.hexaware.ftp47.integration.test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

import javax.management.RuntimeErrorException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
public class CommonUtilEmployee {
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

class Employee {
  public int empId;
	public String empName;
	public String empDate;
	public int managerId;
	public long empPhone;
	public String empDept;
	public String empEmail;
	public int empLeaveAvailable;

    public Employee() {
    }

    public Employee(final int empId) {
      this.empId = empId;
    }

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
    public String toString() {
      try {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(this);
      } catch(Exception e) {
        throw new RuntimeException(e);
      }
    }
}
