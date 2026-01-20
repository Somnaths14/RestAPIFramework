package stepDifinitions;

import java.io.IOException;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.EnumClass;
import resources.TestDataBuilder;
import resources.Utility;

public class StepDefinations extends Utility {

	RequestSpecification reqst;
	ResponseSpecification res;
	Response response;
	TestDataBuilder td;
	static String placeId;

	@Given("User add the payload json with {string} , {string} and {string}")
	public void user_add_the_payload_json_with_and(String name, String address, String phone) throws IOException {

		td = new TestDataBuilder();

		reqst = given().spec(ReqstSpecificatn()).body(td.AddPlacePayload(name, address, phone));

	}

	@When("User calls the {string} API with http {string} method")
	public void user_calls_the_api_with_http_post_method(String apiName, String httpMethod) {

		String apiResource = EnumClass.valueOf(apiName).getResource();

		res = new ResponseSpecBuilder().expectStatusCode(200).build();
		if (httpMethod.equalsIgnoreCase("POST"))
			response = reqst.when().post(apiResource).then().spec(res).assertThat().extract().response();
		else if (httpMethod.equalsIgnoreCase("GET"))
			response = reqst.when().get(apiResource).then().spec(res).assertThat().extract().response();
		else if (httpMethod.equalsIgnoreCase("DELETE"))
			response = reqst.when().delete(apiResource).then().spec(res).assertThat().extract().response();

	}

	@Then("User get the response with status code {int}")
	public void user_get_the_response_with_status_code(int int1) {
		assertEquals(response.getStatusCode(), int1);

	}

	@And("User verify {string} is {string}")
	public void user_verify_is(String key, String val) {

		String actualvalue = ResponseValueGetter(response, key);
		System.out.println(actualvalue);
		assertEquals(val, actualvalue);
	}

	@Then("User validate the {string} has same {string} , {string} and {string}")
	public void user_validate_the_has_same_and(String place_id, String name, String address, String phone)
			throws IOException {

		placeId = ResponseValueGetter(response, place_id);
		reqst = given().spec(ReqstSpecificatn()).queryParam("place_id", placeId);

		user_calls_the_api_with_http_post_method("getPlaceAPI", "GET");
		user_get_the_response_with_status_code(200);
		user_verify_is("name", name);
		user_verify_is("address", address);
		user_verify_is("phone_number", phone);

	}

	@Given("User calls the {string} API with http {string} method with place_id")
	public void user_calls_the_api_with_http_method_with_place_id(String deleteAPI, String postmethod)
			throws IOException {
		reqst = given().spec(ReqstSpecificatn()).body(deleteJsonBody(placeId));
		user_calls_the_api_with_http_post_method(deleteAPI, postmethod);
		
	}

}
