package RestAssured1.RestAssured1;

import java.util.HashMap;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class OuthAuth {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
       RestAssured.baseURI="http://coop.apps.symfonycasts.com";
       Map<String, String> authData =new HashMap();
       authData.put("client_id", "ApiHandsOn");
       authData.put("client_secret","4f1457c5c87169158f740598d6769eb7");
       authData.put("grant_type","client_credentials");
       
       //Response rsp = RestAssured.given().auth().oauth(arg0, arg1, arg2, arg3)-> outh1.0
       
       Response rsp = RestAssured.given().formParams(authData)
    		   .when().post("/token")
    		   .then().statusCode(200).extract().response();
       System.out.println(rsp.asPrettyString());
       
       
       String accessToken=rsp.jsonPath().getString("access_token");
       System.out.println(accessToken);
      
       Response outhAuthrsp = RestAssured.given().header("Authorization", "Bearer "+accessToken)
    		   .when().get("/api/me")
    		   .then().statusCode(200).extract().response();
       System.out.println(outhAuthrsp.asPrettyString());
       
       
       if(outhAuthrsp.statusCode()==200)
       {
    	   System.out.println("OuthAuth Validations passed");
       }
       else
       {
    	   System.out.println("Failed to validate OuthAuth");
       }

	}

}
