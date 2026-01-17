package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;

import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utility {
	
	private Response response;
	private JsonPath js;
	RequestSpecification req;
	public RequestSpecification ReqstSpecificatn() throws IOException {

		PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
       
		req = new RequestSpecBuilder()
				.setBaseUri(getPropertyData("baseURI"))
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.addQueryParam("key", "qaclick123").build();
		return req;
       
	}

	public static String getPropertyData(String key) throws IOException {

		Properties prop = new Properties();
		FileInputStream file = new FileInputStream(
				"C:\\Users\\SoSaha\\Desktop\\RestAPIFramework\\src\\main\\java\\resources\\GlobalData.properties");
		prop.load(file);
		return prop.getProperty(key);
	}
	
	public static String ResponseValueGetter(Response response, String key)
	{
		JsonPath js = new JsonPath(response.asString());
		String value =js.get(key);
		return value;
		
	}
	public String deleteJsonBody(String placeId)
	{
		return "{\r\n"
				+ "    \"place_id\":\""+placeId+"\"\r\n"
				+ "}";
	}
	
	

}
