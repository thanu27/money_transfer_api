package moneytransfer.integration;


import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import io.restassured.RestAssured;
import moneytransfer.Application;

public class ApiIntegrationTest {	
	@BeforeClass
	  public static void setUp() {    
	    RestAssured.baseURI = "http://localhost:7000";
	    RestAssured.basePath = "/";
	    startServer();
	    
	  }
	  private static void startServer() {
	    Application.main(new String[] {});
	  }
	 
	 
	 @Test
	  public void shouldtransferMoney() {
	  	    given()
	        .param("senderAccountNumber", "1111")
	        .and().param("receiverAccountNumber", "3333")
	        .and().param("amount", "10.00")
	        .when()
	        .post("/transaction")
	        .then()
	        .body(
	            "message",
	            equalTo("Amount transferred successfully")
	        )
	        .statusCode(201);
	  }	
}
 

