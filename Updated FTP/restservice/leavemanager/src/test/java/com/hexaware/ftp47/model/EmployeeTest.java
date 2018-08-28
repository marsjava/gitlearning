package com.hexaware.ftp47.model;

import com.hexaware.ftp47.persistence.EmployeeDAO;

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

import java.util.ArrayList;

/**
 * Test class for Employee.
 */
@RunWith(JMockit.class)
public class EmployeeTest {

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
  public final void testEmployeeContructor() {
    new MockUp<Employee>() {
      @Mock
        public void initInput() {
      }
    };
    Employee emp = new Employee();
    emp.setEmpId(1000);
    assertEquals(1000, emp.getEmpId());
  }
  /**
   * Tests the equals/hashcode methods of the employee class.
   */
  @Test
  public final void testEmployee() {
    Employee e100 = new Employee(100);
    Employee e101 = new Employee(101);
    Employee e102 = new Employee(102, "SAI", "2018-06-19", 100, 9912140507L, "IT", "KETHU.SAIKUMAR@GMAIL.COM", 30);
    assertNotEquals(e100, null);
    assertNotEquals(e100, new Integer(100));
    assertEquals(e100, new Employee(100));
    assertNotEquals(e101, new Employee(100));
    assertEquals(e100.hashCode(), new Employee(100).hashCode());
    assertEquals(e100.getEmpId(), new Employee(100).getEmpId());
    e101.setEmpId(100);
    assertEquals("2018-06-19", e102.getEmpDate());
  }
  /**
   * Tests the equals/hashcode methods of the employee class.
   */
  @Test
  public final void testEmployeeCheck() {
    Employee e100 = new Employee(100, "SAI", "2018-06-19", 100, 9912140507L, "IT", "KETHU.SAIKUMAR@GMAIL.COM", 30);
    Employee e101 = new Employee(101, "KUMAR", "2018-06-20", 100, 9912140508L, "HR", "KUMAR@GMAIL.COM", 30);
    Employee e102 = e100;
    Employee e103 = null;
    assertNotEquals(e100, null);
    assertNotEquals(e101, null);
    assertNotEquals(e100, new Employee(102, "SAI", "2018-06-19", 100, 9912140507L,
        "IT", "KETHU.SAIKUMAR@GMAIL.COM", 30));
    assertNotEquals(e100, new Employee(103, "KUMAR", "2018-06-20", 100, 9912140508L,
        "HR", "KUMAR@GMAIL.COM", 30));
    assertEquals(e100.hashCode(), new Employee(100, "SAI", "2018-06-19", 100, 9912140507L,
        "IT", "KETHU.SAIKUMAR@GMAIL.COM", 30).hashCode());
    assertEquals(e101.hashCode(), new Employee(101, "KUMAR", "2018-06-20", 100, 9912140508L,
        "HR", "KUMAR@GMAIL.COM", 30).hashCode());
    assertNotEquals(e100, e101);
    assertEquals(e100, e102);
    assertNull(e103);
    assertEquals(e100, new Employee(100, "SAI", "2018-06-19", 100, 9912140507L, "IT", "KETHU.SAIKUMAR@GMAIL.COM", 30));
  }

   /**
   * tests that empty employee list is handled correctly.
   * @param dao mocking the dao class
   */
  @Test
  public final void testListAllEmpty(@Mocked final EmployeeDAO dao) {
    new Expectations() {
      {
        dao.list(); result = new ArrayList<Employee>();
      }
    };
    new MockUp<Employee>() {
      @Mock
      EmployeeDAO dao() {
        return dao;
      }
    };
    Employee[] es = Employee.listAll();
    assertEquals(0, es.length);
  }

  /**
   * tests that a list with some employees is handled correctly.
   * @param dao mocking the dao class
   */
  @Test
  public final void testupdateLeaves(@Mocked final EmployeeDAO dao) {
    final Employee emp = new Employee(100, "SAI", "2018-06-19", 100, 9912140507L, "IT", "KETHU.SAIKUMAR@GMAIL.COM", 30);
    new Expectations() {
      {
        dao.updateLeaves(30, 100);
      }
    };
    new MockUp<Employee>() {
      @Mock
      EmployeeDAO dao() {
        return dao;
      }
    };
    assertEquals("LeaveBalanceUpdated", Employee.updateLeavesAvailable(30, 100));
  }
  /**
   * Tests that a list with some employees is handled correctly.
   * @param dao mocking the dao class
   */
  @Test
  public final void testListAllSome(@Mocked final EmployeeDAO dao) {
    new Expectations() {
      {
        ArrayList<Employee> es = new ArrayList<Employee>();
        es.add(new Employee(1));
        es.add(new Employee(10));
        es.add(new Employee(100));
        dao.list(); result = es;
      }
    };
    new MockUp<Employee>() {
      @Mock
      EmployeeDAO dao() {
        return dao;
      }
    };
    Employee[] es = Employee.listAll();
    assertEquals(3, es.length);
    assertEquals(new Employee(1), es[0]);
    assertEquals(new Employee(10), es[1]);
    assertEquals(new Employee(100), es[2]);
  }

  /**
   * Tests that a fetch of a specific employee works correctly.
   * @param dao mocking the dao class
   */
  @Test
  public final void testListById(@Mocked final EmployeeDAO dao) {
    final Employee e100 = new Employee(100);
    new Expectations() {
      {
        dao.find(100); result = e100;
        dao.find(-1); result = null;
      }
    };
    new MockUp<Employee>() {
      @Mock
      EmployeeDAO dao() {
        return dao;
      }
    };
    Employee e = Employee.listById(100);
    assertEquals(e100, e);

    e = Employee.listById(-1);
    assertNull(e);
  }

  /**
  * Test class for Default Constructor.
  */
  @Test
  public final void testEmployeeCons() {
    new MockUp<Employee>() {
      @Mock
      public void initInput() {
      }
    };
    Employee emp = new Employee();
    int empId = emp.getEmpId();
    assertNotEquals(100, empId);
  }

  /**
  * Test class for Default Constructor.
  */

  @Test
  public final void testEmployeeParam() {
    new MockUp<Employee>() {
      @Mock
      public void initInput(final int empId, final String empName, final String empDate,
          final int managerId, final long empPhone, final String empDept, final String empEmail, final int emp) {
      }
    };
    Employee emp = new Employee(100, "SAI", "2018-06-19", 100, 9912140507L, "IT", "KETHU.SAIKUMAR@GMAIL.COM", 30);
    int empId = emp.getEmpId();
    String empDate = emp.getEmpDate();
    assertNotNull(empId);
    assertEquals(100, empId);
    assertEquals("2018-06-19", empDate);
  }


 /**
  * Test class for Employee.
  */
  @Test
  public final void testEmployeeDetails() {
    Employee emp = new Employee();
    emp.setEmpId(1000);
    emp.setEmpName("SAi");
    emp.setEmpDate("2018-06-19");
    emp.setManagerId(1000);
    emp.setEmpPhone(9912140507L);
    emp.setEmpDept("IT");
    emp.setEmpEmail("kethu.saikumar@gmail.com");
    emp.setEmpLeaveAvailable(20);
    assertEquals(1000, emp.getEmpId());
    assertNotEquals("SAI", emp.getEmpName());
    assertEquals("2018-06-19", emp.getEmpDate());
    assertEquals(1000, emp.getManagerId());
    assertEquals(9912140507L, emp.getEmpPhone());
    assertEquals("IT", emp.getEmpDept());
    assertEquals(20, emp.getEmpLeaveAvailable());
    assertEquals("kethu.saikumar@gmail.com", emp.getEmpEmail());
    String string = ("\n" + emp.getEmpId() + "  " + emp.getEmpName() + "  " + emp.getEmpDate() + "  "
        + emp.getManagerId() + "  " + emp.getEmpPhone() + "  " + emp.getEmpDept() + "  " + emp.getEmpEmail() + "  "
        + emp.getEmpLeaveAvailable() + "  ");
    assertNotNull(string);
    assertEquals(string, emp.toString());
  }

 /**
 * Test class for Employee.
 * @param dao for checking manager id.
 */
  @Test
  public final void testcheckManager(@Mocked final EmployeeDAO dao) {
    final Employee managerid = new Employee(2000, "ANKITA", "1995-10-14", 1000, 9987456122L,
        "ECE", "ANKITARAY@GMAIL.COM", 20);
    new Expectations() {
      {
        dao.findManager(2000);
        result = managerid;
        dao.findManager(-1);
        result = null;
      }
    };
    new MockUp<Employee>() {
      @Mock
      EmployeeDAO dao() {
        return dao;
      }
    };
    Employee manager = Employee.checkManager(2000);
    assertEquals(managerid, manager);
    manager = Employee.checkManager(-1);
    assertNull(manager);
  }
  /**
   * setup method.
   *@param dao for mocking.
   */
  @Test
  public final void testUpdateLeaveBalance(@Mocked final EmployeeDAO dao) {
    new Expectations() {
      {
        dao.updateLeaves(12, 2000);
      }
    };
    new MockUp<Employee>() {
      @Mock
      EmployeeDAO dao() {
        return dao;
      }
    };
    String s = Employee.updateLeavesAvailable(12, 2000);
    assertEquals("LeaveBalanceUpdated", s);
  }
}
