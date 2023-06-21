package commonFunction;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

public class API_Common_Function {
	
	public static int res_statusCode(String baseURI,String requestBody,String resource) {
		
		RestAssured.baseURI=baseURI;
		int status_Code=given().header("Content-Type","application/json").body(requestBody).post(resource).then().extract().statusCode();
		return status_Code;
	}
	
	public static String res_responseBody(String baseURI,String requestBody,String resource) {
		
		RestAssured.baseURI=baseURI;
		String responseBody=given().header("Content-Type","application/json").body(requestBody).post(resource).then().extract().response().asString();
		return responseBody;
			
					
		}

}
