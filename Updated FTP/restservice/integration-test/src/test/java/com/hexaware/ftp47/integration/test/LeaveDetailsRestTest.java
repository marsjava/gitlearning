package com.hexaware.ftp47.integration.test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import org.junit.Test;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.JsonPath;
import static com.jayway.restassured.RestAssured.given;

public class LeaveDetailsRestTest {
    @Test
    
	public void testLeaveHistory() throws AssertionError, URISyntaxException {
		LeaveDetails[] res = given().accept(ContentType.JSON).when()
                .get(CommonUtilLeave.getURI("/api/leave/1000/history")).getBody().as(LeaveDetails[].class);
    LeaveDetails l1 = res[0];
    LeaveDetails l2 = res[1];
    assertEquals(new LeaveDetails(1, 1000, 2, "2018-07-25", "2018-07-26","EL", "PENDING",
                    "asasasas", "2018-07-23", "waiting"), l1);		
    assertEquals(new LeaveDetails(2, 1000, 2, "2018-08-16", "2018-08-17", "EL", "PENDING",
                    "asasasas", "2018-07-23", "waiting"),l2);	
    }
  
  @Test
	public void testLeaveById404() throws AssertionError, URISyntaxException {
		given().accept(ContentType.JSON).when()
				.get(CommonUtilLeave.getURI("/api/leave/9999")).then().assertThat().statusCode(404);
  }
  
  @Test

  public void testLeaveApply() throws Exception {
      given().formParameters("empId", 1000 , "startDate", "2019-08-03", "endDate", "2019-08-04", 
      "leaveReason", "resta", "leaveType", "EL").expect().body("leaveReason", equalTo("resta"))
      .accept(ContentType.JSON).when().post(CommonUtilLeave.getURI("/api/leave/1000/apply"));
  } 
}

