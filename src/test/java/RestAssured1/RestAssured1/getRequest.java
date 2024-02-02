package RestAssured1.RestAssured1;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class getRequest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI="https://reqres.in/";
		Response response= RestAssured
				.given().queryParam("page","2").header("content-type", "application/json")
				.when().get("/api/users")
				.then().extract().response();
		
		//JsonPath jp=new JsonPath(response.toString());
		if(response.statusCode()==200) {
			System.out.println("get request posted successfully.......");
		}
		else
		{
			System.out.println("get request posted unsuccessfully.......");
		}
		System.out.println("response value : "+response.asPrettyString());
		System.out.println(response.jsonPath().getString("data[0].email"));
	}
}
