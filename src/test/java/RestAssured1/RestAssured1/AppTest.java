package RestAssured1.RestAssured1;

import io.restassured.RestAssured;
import io.restassured.response.Response;

/**
 * Unit test for simple App.
 */
public class AppTest {
	public static void main(String[] args) {
RestAssured.baseURI="https://dummyjson.com/";
Response res=RestAssured
		 .given().header("content-json", "application/json")
		 .when().get("/products")
		 .then().extract().response();

if(res.statusCode()==200)
{
	 System.out.println("Response code received 200");
}
else
{
	 System.out.println("Failed to receive response code as 200");
}
}
}
