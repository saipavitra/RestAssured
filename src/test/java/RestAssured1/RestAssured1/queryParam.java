package RestAssured1.RestAssured1;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class queryParam {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		RestAssured.baseURI="https://openweathermap.org";
		Response rsp = RestAssured.given()
				.queryParam("lat", "35")
				.queryParam("lon", "139")
				.queryParam("appid","83bf5aa7a3ac12050979565e61d32042")
				.when()
				.get("/data/2.5/aggregated/year")
				.then()
				.extract().response();
				
				System.out.println(rsp.asPrettyString());
				if(rsp.statusCode()==200)
				{
					System.out.println("Query param Passed");
				}
				else
				{
					System.out.println("Query Param Failed");
				}
	}

}
