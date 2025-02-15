package steps;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.junit.Assert.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import static io.restassured.RestAssured.given;
import java.util.Map;

import org.junit.jupiter.api.Assertions;

public class ApiSteps {
    private Response response;

    @Given("I send a GET request to {string}")
    public void i_send_get_request(String endpoint) {
        response = RestAssured.get("https://reqres.in" + endpoint);
    }

    @Then("the response status should be {int}")
    public void the_response_status_should_be(int statusCode) {
        assertEquals(statusCode, response.getStatusCode());
    }

    @Then("the response should contain first name {string} and last name {string}")
    public void the_response_should_contain(String firstname,String lastname) {
    	
    	 // Convert response to JSON
        String firstName = response.jsonPath().getString("data.first_name");
        String lastName = response.jsonPath().getString("data.last_name");

        // Validate values using assertions
        assertEquals(firstname, firstName, "First name does not match!");
        assertEquals(lastname, lastName, "Last name does not match!");

    }
    
    @Given("I send a POST request to {string} with data:")
    public void i_send_post_request(String endpoint, String requestBody) {
        // Set Base URL
        RestAssured.baseURI = "https://reqres.in";

        // Send POST request and store response
        response = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post(endpoint);
    }


    @Then("the response should contain {string}")
    public void verify_response_contains(String key) {
        // Parse JSON response as a Map
        Map<String, Object> responseMap = response.jsonPath().getMap("$");

        // Verify response contains expected key
        Assertions.assertTrue(responseMap.containsKey(key), "Response does not contain key: " + key);
    }
}
