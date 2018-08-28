package com.hexaware.ftp47.integration.test;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import org.junit.Test;
import static org.junit.Assert.*;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.JsonPath;
import static com.jayway.restassured.RestAssured.given;

public class EmployeeRestTest {
	
	@Test
	public void testEmployeesList() throws AssertionError, URISyntaxException {
		Employee[] res = given().accept(ContentType.JSON).when()
				.get(CommonUtilEmployee.getURI("/api/employees")).getBody().as(Employee[].class);
		for (Employee e: res) {
			switch (e.empId) {
				case 1000:
					assertEquals(new Employee(1000,"SAI","1997-03-24",1000,9912140507L,"IT","KETHU.SAIKUMAR@GMAIL.COM",7), e);
					break;
				case 2000:
					assertEquals(new Employee(2000,"ANKITA","1995-10-14",1000,9987456122L,"ECE","ANKITARAY@GMAIL.COM",18), e);
					break;				
				case 2001:
					assertEquals(new Employee(2001,"SHALLINI","1996-01-01",1000,9913140507L,"CSE","SHALINI@GMAIL.COM",98), e);
					break;				
				case 3000:
					assertEquals(new Employee(3000,"SACHIN","1995-03-03",2000,9914140507L,"IT","SACHIN@GMAIL.COM",100), e);
					break;				
				case 3001:
					assertEquals(new Employee(3001,"MIKE","1997-04-04",2001,9915140507L,"IT","VISWA@GMAIL.COM",91), e);
					break;			
				default:
					fail("Unknown employee with id:" + e);	
			}
		}
	}

	@Test
	public void testEmployeeById() throws AssertionError, URISyntaxException {
		Employee res = given().accept(ContentType.JSON).when()
				.get(CommonUtilEmployee.getURI("/api/employees/1000")).getBody().as(Employee.class);
		assertEquals(new Employee(1000,"SAI","1997-03-24",1000,9912140507L,"IT","KETHU.SAIKUMAR@GMAIL.COM",7), res);
  }

  
  @Test
	public void testEmployeeById404() throws AssertionError, URISyntaxException {
		given().accept(ContentType.JSON).when()
				.get(CommonUtilEmployee.getURI("/api/employees/9999")).then().assertThat().statusCode(404);
	}
}
