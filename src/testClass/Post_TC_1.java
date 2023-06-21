package testClass;


import java.io.IOException;
import java.time.LocalDate;
import org.testng.Assert;
import commonFunction.API_Common_Function;
import commonFunction.utility_Common_Function;
import io.restassured.path.json.JsonPath;
import requestRepository.Post_req_repository;
public class Post_TC_1 {
public static void execute() throws IOException {
for(int i=0;i<5;i++)
{
String baseURI=Post_req_repository.base_URI();
String requestBody=Post_req_repository.post_req_TC1();
String resource=Post_req_repository.post_resource();
int statusCode=API_Common_Function.res_statusCode(baseURI, requestBody, resource);
if(statusCode==201)
{
	String responseBody=API_Common_Function.res_responseBody(baseURI, requestBody, resource);
System.out.println(responseBody);
Post_TC_1.validator(responseBody, statusCode, requestBody);
utility_Common_Function.evidencecreator("Post_TC_1", requestBody, responseBody);
break;
}
else 
{
System.out.println("correct status code not found hence retrying");
}
}
}
public static void validator(String responseBody,int statusCode,String requestBody) {
//Parse response body and its parameters
JsonPath jspres=new JsonPath(responseBody);
String res_name=jspres.getString("name");
String res_job=jspres.getString("job");
String res_id=jspres.getString("id");
String res_createdAt=jspres.getString("createdAt");
String currentdate=LocalDate.now().toString();
//parse request body and its parameters
JsonPath jspreq=new JsonPath ( requestBody);
String req_name=jspreq.getString("name");
String req_job=jspreq.getString("job");
//Validate the response
Assert.assertEquals(statusCode,201);
Assert.assertEquals(req_name, res_name);
Assert.assertEquals(req_job, res_job);
Assert.assertNotNull(res_id);
Assert.assertEquals(res_createdAt.substring(0,10), currentdate);

	}

}
