package com.hexaware.ftp47.model;

import com.hexaware.ftp47.persistence.LeaveDetailsDAO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import mockit.Expectations;
import mockit.MockUp;
import mockit.Mocked;
import mockit.Mock;
import mockit.integration.junit4.JMockit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Test class for Leave Details.
 */
@RunWith(JMockit.class)
public class LeaveDetailsTest {

  /**
   * setup method.
   */
  @Before
  public void initInput() {

  }

  /**
   * Tests the default constructor of the employee class.
   */
  @Test
  public final void testLeaveContructor() {
    new MockUp<LeaveDetails>() {
      @Mock
      public void initInput() {
      }
    };
    LeaveDetails leave = new LeaveDetails();
    leave.setLeaveId(1);
    assertEquals(1, leave.getLeaveId());
  }

  /**
   * Tests the equals/hashcode methods of the employee class.
   */
  @Test
  public final void testLeave() {
    LeaveDetails l1 = new LeaveDetails(1);
    LeaveDetails l2 = new LeaveDetails(2);
    assertNotEquals(l1, null);
    assertNotEquals(l2, new Integer(1));
    assertEquals(l2, new LeaveDetails(2));
    assertNotEquals(l2, new LeaveDetails(1));
    assertEquals(l1.hashCode(), new LeaveDetails(1).hashCode());
    assertEquals(l2.getLeaveId(), new LeaveDetails(2).getLeaveId());
  }

  /**
   * Tests the equals/hashcode methods of the employee class.
   */
  @Test
  public final void testLeaveCheck() {
    LeaveDetails l1 = new LeaveDetails(1, 100, 2, "2018-06-20", "2018-06-21", LeaveType.EL, LeaveStatus.PENDING, "SICK",
        "2018-06-20", "APP");
    LeaveDetails l2 = new LeaveDetails(2, 200, 2, "2018-06-20", "2018-06-21", LeaveType.EL, LeaveStatus.APPROVED,
        "SICK", "2018-06-20", "APP");
    LeaveDetails l3 = l1;
    LeaveDetails l4 = null;
    assertNotEquals(l1, null);
    assertNotEquals(l2, null);
    assertNotEquals(l1, new LeaveDetails(2, 300, 2, "2018-06-20", "2018-06-21", LeaveType.EL, LeaveStatus.APPROVED,
        "SICK", "2018-06-20", "APP"));
    assertNotEquals(l2, new LeaveDetails(3, 300, 2, "2018-06-20", "2018-06-21", LeaveType.EL, LeaveStatus.APPROVED,
        "SICK", "2018-06-20", "APP"));
    assertEquals(l1.hashCode(), new LeaveDetails(1, 100, 2, "2018-06-20", "2018-06-21", LeaveType.EL,
        LeaveStatus.PENDING, "SICK", "2018-06-20", "APP").hashCode());
    assertEquals(l2.hashCode(), new LeaveDetails(2, 200, 2, "2018-06-20", "2018-06-21", LeaveType.EL,
        LeaveStatus.APPROVED, "SICK", "2018-06-20", "APP").hashCode());
    assertNotEquals(l1, l2);
    assertEquals(l1, l3);
    assertNull(l4);
  }

  /**
   * Test class for Employee.
   */
  @Test
  public final void testLeaveDetails() {
    LeaveDetails leave = new LeaveDetails();
    leave.setLeaveId(1);
    leave.setEmpId(100);
    leave.setNoOfDays(2);
    leave.setStartDate("2018-06-19");
    leave.setEndDate("2018-06-20");
    leave.setLeaveType(LeaveType.EL);
    leave.setLeaveStatus(LeaveStatus.PENDING);
    leave.setLeaveReason("MOVIE");
    leave.setLeaveAppliedDate("2018-06-19");
    leave.setManagerComments("DENY");
    String string = (" \n" + leave.getLeaveId() + " " + leave.getEmpId() + " " + leave.getNoOfDays() + " "
        + leave.getStartDate() + " " + leave.getEndDate() + " " + leave.getLeaveType() + " " + leave.getLeaveStatus()
        + " " + leave.getLeaveReason() + " " + leave.getLeaveAppliedDate() + " " + leave.getManagerComments() + " ");
    assertEquals(1, leave.getLeaveId());
    assertEquals(100, leave.getEmpId());
    assertEquals(2, leave.getNoOfDays());
    assertEquals("2018-06-19", leave.getStartDate());
    assertEquals("2018-06-20", leave.getEndDate());
    assertEquals(LeaveType.EL, leave.getLeaveType());
    assertEquals(LeaveStatus.PENDING, leave.getLeaveStatus());
    assertEquals("MOVIE", leave.getLeaveReason());
    assertEquals("2018-06-19", leave.getLeaveAppliedDate());
    assertEquals("DENY", leave.getManagerComments());
    assertNotNull(string);
    assertEquals(string, leave.toString());
  }

  /**
   * setup method.
   * @param dao for leave id.
   */
  @Test
  public final void testfetchLeaveId(@Mocked final LeaveDetailsDAO dao) {
    LeaveType ltype = LeaveType.EL;
    LeaveStatus lstatus = LeaveStatus.PENDING;
    final LeaveDetails leaveid = new LeaveDetails(1, 1000, 4, "2018-06-15", "2018-06-19", ltype, lstatus,
        "FAMILY FUNCTION", "2018-06-10", null);
    new Expectations() {
      {
        dao.findLeaveId(1);
        result = leaveid;
      }
    };
    new MockUp<LeaveDetails>() {
      @Mock
      LeaveDetailsDAO dao() {
        return dao;
      }
    };
    LeaveDetails lid = LeaveDetails.fetchLeaveId(1);
    assertEquals(leaveid, lid);
    System.out.println("Leave id: " + leaveid);
  }

  /**
   * setup method.
   * @param dao for pending.
   */
  @Test
  public final void testlistByManagerId(@Mocked final LeaveDetailsDAO dao) {
    LeaveType ltype = LeaveType.EL;
    LeaveStatus lstatus = LeaveStatus.PENDING;
    new Expectations() {
      {
        ArrayList<LeaveDetails> manager = new ArrayList<LeaveDetails>();
        manager.add(new LeaveDetails(1000));
        // manager.add(new LeaveDetails(2000));
        dao.findManagerPending(1000);
        result = manager;
      }
    };
    new MockUp<LeaveDetails>() {
      @Mock
      LeaveDetailsDAO dao() {
        return dao;
      }
    };
    LeaveDetails[] manager = LeaveDetails.listByManagerId(1000);
    // assertEquals(1, manager.length);
    assertEquals(new LeaveDetails(1000), manager[0]);
    System.out.println("Pending details: " + manager[0]);
    // assertEquals(new Employee(2000), manager[1]);
  }

  /**
   * setup method.
   * @param dao for update status.
   */
  @Test
  public final void testupdateStatus(@Mocked final LeaveDetailsDAO dao) {
    LeaveType ltype = LeaveType.EL;
    final int lid = 1;
    final String lcomment = "LEAVE GIVEN";
    LeaveStatus lstatus = LeaveStatus.APPROVED;
    final LeaveDetails leavestatus = new LeaveDetails(lid, 1000, 4, "2018-06-15", "2018-06-19", ltype, lstatus,
        "FAMILY FUNCTION", "2018-06-10", lcomment);
    new Expectations() {
      {
        dao.updateLeaveStatus("APPROVED", "Approved", lid);
        // result = leavestatus;
      }
    };
    new MockUp<LeaveDetails>() {
      @Mock
      LeaveDetailsDAO dao() {
        return dao;
      }
    };
    String status = LeaveDetails.updateStatus(1, "Approved", 1);
    assertEquals("Leave Status Changed", status);
    System.out.println("Leave status: " + leavestatus);
  }
  /**
   * Tests that a list with some employees is handled correctly.
   * @param dao mocking the dao class
   */
  @Test
  public final void testListHistory(@Mocked final LeaveDetailsDAO dao) {
    new Expectations() {
      {
        ArrayList<LeaveDetails> es = new ArrayList<LeaveDetails>();
        es.add(new LeaveDetails(1, 100, 2, "2018-06-20", "2018-06-21", LeaveType.EL, LeaveStatus.PENDING, "SICK",
            "2018-06-20", "APP"));
        es.add(new LeaveDetails(2, 100, 2, "2018-06-20", "2018-06-21", LeaveType.EL, LeaveStatus.APPROVED, "SICK",
            "2018-06-20", "APP"));
        es.add(new LeaveDetails(3, 100, 2, "2018-06-20", "2018-06-21", LeaveType.EL, LeaveStatus.APPROVED, "SICK",
            "2018-06-20", "APP"));
        dao.findHistory(100);
        result = es;
      }
    };
    new MockUp<LeaveDetails>() {
      @Mock
      LeaveDetailsDAO dao() {
        return dao;
      }
    };
    LeaveDetails[] ls = LeaveDetails.listByEmpId(100);
    assertEquals(3, ls.length);
    assertEquals(new LeaveDetails(1, 100, 2, "2018-06-20", "2018-06-21", LeaveType.EL, LeaveStatus.PENDING, "SICK",
        "2018-06-20", "APP"), ls[0]);
    assertEquals(new LeaveDetails(2, 100, 2, "2018-06-20", "2018-06-21", LeaveType.EL, LeaveStatus.APPROVED, "SICK",
        "2018-06-20", "APP"), ls[1]);
    assertEquals(new LeaveDetails(3, 100, 2, "2018-06-20", "2018-06-21", LeaveType.EL, LeaveStatus.APPROVED, "SICK",
        "2018-06-20", "APP"), ls[2]);
  }

  /**
   * Tests that a list with some employees is handled correctly.
   * @param dao mocking the dao class
   */
  @Test
  public final void testListPending(@Mocked final LeaveDetailsDAO dao) {
    new Expectations() {
      {
        ArrayList<LeaveDetails> es = new ArrayList<LeaveDetails>();
        es.add(new LeaveDetails(1, 100, 2, "2018-06-20", "2018-06-21", LeaveType.EL, LeaveStatus.PENDING, "SICK",
            "2018-06-20", "WAIT"));
        es.add(new LeaveDetails(2, 100, 2, "2018-06-20", "2018-06-21", LeaveType.EL, LeaveStatus.PENDING, "SICK",
            "2018-06-20", "WAIT"));
        dao.findPending(100);
        result = es;
      }
    };
    new MockUp<LeaveDetails>() {
      @Mock
      LeaveDetailsDAO dao() {
        return dao;
      }
    };
    LeaveDetails[] ls = LeaveDetails.listPendingLeaves(100);
    assertEquals(2, ls.length);
    assertEquals(new LeaveDetails(1, 100, 2, "2018-06-20", "2018-06-21", LeaveType.EL, LeaveStatus.PENDING, "SICK",
        "2018-06-20", "WAIT"), ls[0]);
    assertEquals(new LeaveDetails(2, 100, 2, "2018-06-20", "2018-06-21", LeaveType.EL, LeaveStatus.PENDING, "SICK",
        "2018-06-20", "WAIT"), ls[1]);
  }

  /**
   * Tests that a list with some employees is handled correctly.
   */
  @Test
  public final void testIncrementLeaves() {
    final Employee emp = new Employee(100, "SAI", "2018-06-19", 100, 9912140507L, "IT", "KETHU.SAIKUMAR@GMAIL.COM", 30);
    LeaveDetails l1 = new LeaveDetails(1, 100, 2, "2019-06-20", "2019-06-21", LeaveType.EL, LeaveStatus.PENDING, "SICK",
        "2019-06-20", "APP");
    LeaveDetails.incrementLeaveBalance(2);
    assertEquals("LeaveBalanceUpdated", Employee.updateLeavesAvailable(30, 100));
  }
  //  /**
  //  * Test class for LeaveDetails.
  //  * @throws ParseException pe.
  //  */
  // @Test
  // public final void testcheckdate() throws ParseException {
  //   LeaveDetails l1 = new LeaveDetails(1, 100, 2, "2019-06-25", "2019-06-21",
  // LeaveType.EL, LeaveStatus.PENDING, "SICK",
  //       "2019-06-20", "APP");
  //   SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
  //   String d = "2019-06-25";
  //   final Date startDate = dateFormat.parse(d);
  //   LeaveDetails.checkDate(d);
  // }
  /**
   * Test class for LeaveDetails.
   * @throws ParseException pe
   * @param dao dao.
   */
  @Test
  public final void testApplyLeave(@Mocked final LeaveDetailsDAO dao) throws ParseException {
    SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
    String start = "2019-06-24";
    String end = "2019-06-25";
    final Date startDate = s.parse(start);
    final Date endDate = s.parse(end);
    final LeaveDetails l1 = new LeaveDetails(1, 1000, 2, "2019-06-24", "2019-06-25", LeaveType.EL, LeaveStatus.PENDING,
        "SICK", "2019-06-21", "APP");
    Employee emp = new Employee(1000, "SAI", "2018-06-19", 100, 9912140507L,
        "IT", "KETHU.SAIKUMAR@GMAIL.COM", 30);
    new Expectations() {
      {
        dao.enterLeaveDetails(1000, startDate, endDate, "EL", LeaveStatus.PENDING, "SICK", 2);
      }
    };
    new MockUp<LeaveDetails>() {
      @Mock
      LeaveDetailsDAO dao() {
        return dao;
      }
    };
    String apply = LeaveDetails.insertLeaveDetails(1000, start, end, "SICK", "EL");
    String leave = Employee.updateLeavesAvailable(18, 1000);
    assertEquals("Leave Applied", apply);
    assertEquals("LeaveBalanceUpdated", leave);
  }

  // /**
  //  * Test class for LeaveDetails.
  //  * @throws ParseException pe
  //  * @param dao dao.
  //  */
  // @Test
  // public final void testUpdateApplyLeave(@Mocked final LeaveDetailsDAO dao) throws ParseException {
  //   SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
  //   String start = "2019-06-24";
  //   String end = "2019-06-25";
  //   final Date startDate = s.parse(start);
  //   final Date endDate = s.parse(end);
  //   final LeaveDetails l1 = new LeaveDetails(1, 1000, 2, "2019-06-24", "2019-06-25",
  // LeaveType.EL, LeaveStatus.PENDING,
  //       "SICK", "2019-06-21", "APP");
  //   Employee emp = new Employee(1000, "SAI", "2019-06-19", 100, 9912140507L,
  //       "IT", "KETHU.SAIKUMAR@GMAIL.COM", 30);
  //   new Expectations() {
  //     {
  //       dao.updateLeaveDetails(1000, 1, startDate, endDate, LeaveType.EL, LeaveStatus.PENDING, "SICK", 2);
  //     }
  //   };
  //   new MockUp<LeaveDetails>() {
  //     @Mock
  //     LeaveDetailsDAO dao() {
  //       return dao;
  //     }
  //   };
  //   String update = LeaveDetails.updateLeaveDetails(1000, 1, start, end, "SICK");
  //   String leaves = Employee.updateLeavesAvailable(18, 1000);
  //   assertEquals("Leave Updated", update);
  //   assertEquals("LeaveBalanceUpdated", leaves);
  // }
}
