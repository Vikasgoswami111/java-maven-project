Feature: API Testing for ReqRes

  Scenario: Verify GET API Response
    Given I send a GET request to "/api/users/2"
    Then the response status should be 200
    And the response should contain first name "Janet" and last name "Weaver"

  Scenario: Verify User Creation via POST API
    Given I send a POST request to "/api/users" with data:
      """
      { "name": "morpheus", "job": "leader" }
      """
    Then the response status should be 201
    And the response should contain "id"
