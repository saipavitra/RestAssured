package RestAssured1.RestAssured1;

import java.util.HashMap;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Spec {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI="http://coop.apps.symfonycasts.com";
	       Map<String, String> authData =new HashMap();
	       authData.put("client_id", "ApiHandsOn");
	       authData.put("client_secret","4f1457c5c87169158f740598d6769eb7");
	       authData.put("grant_type","client_credentials");
	       
	       Response rsp = RestAssured.given().formParams(authData)
	    		   .when().post("/token")
	    		   .then().statusCode(200).extract().response();
	       System.out.println(rsp.asPrettyString());
	       
	       String accessToken=rsp.jsonPath().getString("access_token");
	       System.out.println(accessToken);
	       
	       RequestSpecification outhAuthspec = new RequestSpecBuilder()
	    		   .addHeader("Authorization","Bearer "+accessToken)
	    		   .addHeader("Content-type", "application/json")
	    		   .build();
	       
	       ResponseSpecification outhAuthResSpec= new ResponseSpecBuilder()
	    		   .expectStatusCode(200)
	    		   .expectContentType("application/json")
	    		   .build();
	       
	       Response rsp1=RestAssured.given()
	    		   .spec(outhAuthspec)
	    		   .when()
	    		   .get("/api/me")
	    		   .then()
	    		   .spec(outhAuthResSpec)
	    		   .extract().response();
	       System.out.println(rsp1.asPrettyString());
	       
	       if(rsp1.statusCode()==200)
	       {
	    	   System.out.println("AuthOuth passed via spec");
	       }
	       else
	       {
	    	   System.out.println("AuthOuth failed via spec");
	       }
	    		   
	}

}
