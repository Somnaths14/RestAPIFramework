package stepDifinitions;

import java.util.ArrayList;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;

public class StepDefinations {

	RequestSpecification reqst;
	ResponseSpecification res;
	Response response;

	@Given("User add the payload json")
	public void user_add_the_payload_json() {

		// creating object of POJO classes
		AddPlace ap = new AddPlace();
		Location l = new Location();

		l.setLat(60);
		l.setLng(60);

		ap.setLocation(l);
		ap.setAccuracy(50);
		ap.setName("Somnath");
		ap.setPhone_number("7358079540");
		ap.setAddress("one 10");

		ArrayList<String> al = new ArrayList<>();
		al.add("shoe park");
		al.add("shop");
		ap.setTypes(al); // As Types is List of string

		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addQueryParam("key", "qaclick123").build();

		reqst = given().spec(req).body(ap);

	}

	@When("User calls the {string} API with http method")
	public void user_calls_the_api_with_http_method(String string) {

		res = new ResponseSpecBuilder().expectStatusCode(200).build();
		response = reqst.when().post("/maps/api/place/add/json").then().spec(res).assertThat().extract().response();
	}

	@Then("User get the response with status code {int}")
	public void user_get_the_response_with_status_code(int int1) {
		assertEquals(response.getStatusCode(), int1);

	}

	@And("User verify {string} is {string}")
	public void user_verify_is(String key, String val) {
		String responseString = response.asString();
		JsonPath js = new JsonPath(responseString);

		String actualvalue = js.getString(key);

		assertEquals(val, actualvalue);
	}
}
