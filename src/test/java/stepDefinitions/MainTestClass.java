package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuilder;
import pojo.AddPlace;
import pojo.Location;
import resources.Utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class MainTestClass extends Utils {

    RequestSpecification reqSpecTwo;
    ResponseSpecification respSpec;
    Response responseObj;
    public static String place_id;

    TestDataBuilder testBuilder = new TestDataBuilder();

    @Given("Add place payload with {string} {string} {string}")
    public void add_place_payload_with(String name, String language, String address) throws IOException {
        reqSpecTwo = given().spec(requestSpecification()).body(testBuilder.addPlacePayLoad(name, language, address));
    }


    @When("user calls {string} with {string} Http request")
    public void user_calls_with_http_request(String resource, String httpMethod){

        APIResources resourceAPI = APIResources.valueOf(resource);
        System.out.println(resourceAPI.getResource());

        respSpec = new ResponseSpecBuilder().
                expectStatusCode(200).expectContentType(ContentType.JSON).build();

        if(httpMethod.equalsIgnoreCase("POST")){
            responseObj = reqSpecTwo.when().post(resourceAPI.getResource());
            System.out.println("Post Method Called");
        }
        else if(httpMethod.equalsIgnoreCase("GET")){
            responseObj = reqSpecTwo.when().get(resourceAPI.getResource());
            System.out.println("Get Method Called");
        }
        else if(httpMethod.equalsIgnoreCase("DELETE")){
            responseObj = reqSpecTwo.when().delete(resourceAPI.getResource());
            System.out.println("Delete Method Called");
        }

    }


   @Then("the API call is success with the status code {int}")
    public void the_api_call_is_success_with_the_status_code(Integer int1) {
        assertEquals(responseObj.getStatusCode(), 200);
    }


    @Then("{string} in response body is {string}")
    public void status_in_response_body_is_ok(String keyValue, String expectedValue) {
        assertEquals(getJsonPath(responseObj, keyValue), expectedValue);
    }

    @Then("verify place_Id created maps to {string} using {string}")
    public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {

        place_id = getJsonPath(responseObj, "place_id");
        reqSpecTwo = given().spec(requestSpecification()).queryParam("place_id", place_id);
        user_calls_with_http_request(resource, "GET");
        String actualName = getJsonPath(responseObj, "name");
        assertEquals(expectedName, actualName);
    }

    @Given("DeletePlace Payload")
    public void delete_place_payload() throws IOException {
        reqSpecTwo = given().spec(requestSpecification()).body(testBuilder.deletePlacePayload(place_id));
    }
}
