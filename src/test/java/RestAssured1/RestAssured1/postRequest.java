package RestAssured1.RestAssured1;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class postRequest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String name="sai";
		RestAssured.baseURI="https://reqres.in/";
		Response response= RestAssured
				.given().header("content-type", "application/json").body("{\r\n" + 
						"    \"name\": \""+name+"\",\r\n" + 
						"    \"job\": \"leader\"\r\n" + 
						"}")
				.when().post("/api/users")
				.then().extract().response();
		
		if(response.statusCode()==201)
		{
			System.out.println("created succesfully....");
		}
		else
		{
			System.out.println("created unsuccesfully....");
		}
		
		System.out.println("response creation validation...."+ response.jsonPath().getString("id"));//use jsonpath().getInt() to extract integer value
	}
	//if the json is nested array -> use size() to extract the size of the nested array eg.courses is the array -> courses.size()
    //how to insert a variable between a get method in jsonpath class ->JsonPath js=new JsonPath(response); js.get("courses["+i+"].title") here "i" is the variable in for loop 
}


//different ways to create payload
//1)dynamically build json payload with external data inputs
//2)parameterize the API tests with multiple test data sets (-> dataprovider concept in testng)
//3)sending static json file(payload) directly into post method of rest assured (thro json file) -> Files.readAllBytes(Paths.get(give location of file))
//4)feed json payload from using excel using hashmap

//if the scenario is like adding the book using its bookID , in that case , each time a new id needs to be generated
//-> sol: delete the book id created or pass the parameters to the method created for payload

//if you want to pass path parameter in api's post /get request 
//eg:given().pathParam("key","10101").body("")
//when().post("/rest/api/{key}/comment") -> here , key attribute is the pathparameter which is declared in the given()

//SessionFilter- we can use sessionfilter cookie i.e method to get the session cookie created at the previous request and use the same cookie session for the next request.
//syntax: SessionFilter session = new SessionFilter();
//given().header().body().filter(session).when().post().then().

//how to send the attachments in jira software for bus raising
//given().header("Content-Type","multipart/form-data")
//.multiPart("file",new File("jira.txt"))-> uploads the file in the jira software
//.when().post()

//path parameter vs query parameter
//path parameter navigates you to the particular resource 
//query parameter performs the sorting functions to drill down to one few attributes which helps us to see only the needed data instead of all 
//given().filter(session).pathParam("key","10101").queryParam("fields","comment").when().post()

//how to handle a website which does not have any proper ssl certificates to interact via api?
//use relaxedHTTPSValidations() to ignore all certificate validation issues i.e given().relaxedHTTPSValidations().header()

//outh2.0
//most commonly used authorization mechanism where it has multiple grant types such as "client credentials and authorization code " these two are the most commonly used grant types
//twitter uses outh1.0 authorization mechanism
//sign with google is one of the good examples for outh 2.0 
//client (i.e - bookmyshow etc..) will have to registered to theIr server to get their client id and client secret , client id will be public to the users and client secret is vice versa
//access token will be needed to get the first and last name , access token will be generated in back end server
//scope - within the limit , the api should be accessable like firstName ,lastName will be accessable.
//authurl - authentication url inorder to sign in to the application via api
//client id -
//response type - code ( code will be returned back once sign in via google account has been initiated)
//redirectURL- redirects to the application once the sign in is successful
//state is the optional parameter , application server's code should be matched with the code that we send
//after successful sign in , a url will be generated at the top of the google's page url , where you will find code , state etc ..which is generated from server
//authorization code will be generated in the url after sign in is successful, copy that code and paste it under next request under header's grant type as authoriztion code 
//authorization server url needs to be hit so that code will be genertaed at url , code needs to be passed in the next request to get access token in order to access the application

//mandatory fields for getAuthorization Code request:
//end point: authorization server url
//query params: scope, auth_url, client id, response type, redirect_url
//this operation is performed on browser 
//output: code 

//mandatory fields for getaccessToken request: ->/token
//end point :Access token url
//query param:code , client_id, client secret ,redirect_Url, grant type
//output: Access Token

//serialization: converting java objects to payload json
//public class Message{
//private String message;
//public String getMessage(){
//return message}
//public void setMessage(String message){
//this.message=message;}
//creating java objects -> Message msg = new Message();
//msg.setMessage("Hello")
//passing the java object to rest assured -> .given().body(msg).when().post("/message").then().extract().response()
//libraries used: jackson

//then().assertThat().statusCode(200).body("scope", equalTo("App"))-> it compares statuscode as well as body's scope (equalTo -> belongs to hamcrest package)

//PrintStream log= new PrintStream(new FileOutputStream("logging.txt"))
//RequestSpecBuilder().setBaseUri("https:rahul.com").addQueryParam("key","qa123").addFilter(RequestLoggingFilter.logRequestTo(log)).addFilter(ResponseLoggingFilter.logResponseTo(log)) // logs all the logging of request and response to a file

//if you want to run in different environments and if you have maintain different urls in the project use properties file
//pre-requisite -> create a file with .properties file
//Properties prop - new Properties();
//FileInputStream fis = new FileinputStream("loaction of the file");
//prop.load(fis);
//prop.getProperty("provide the property name eg:id (returns in the response body))

//for enum classes create a enum class.java ( place all the url for post or delete or get requests)
//public enum APIresource{
// AddPlaceAPI("/map/api/place/add/json"),
// getPlaceAPI("/map/api/place/get/json"),
// deletePlaceAPI("/map/api/place/delete/json")
// private String resource;
// APIrosurces(String resource)
//{
//this. resource= resource
//}
//public String getResource()
//{
//return resource;
//}
//}
//call the enum constants in other class
//APIresource.valueOf(resource)