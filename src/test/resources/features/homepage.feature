Feature: Home Page Interaction

  Scenario: Verify the API request types and endpoints on the home page
    Given I open the application home page
    When I list different request types and their endpoints
    Then I should see the request types and endpoints displayed correctly
 

   Scenario: Verify sample request and response after selecting an option
    Given I open the application home page
    When I select the "GET SINGLE USER NOT FOUND" option
    Then I should see the sample request as "/api/users/23"
    And I should see the sample response as "404 {}"
    
    Scenario: Verify navigation to the Support page
    Given I open the application home page
    When I click on the "Support ReqRes" button
    Then I should be redirected to the support page
    

   Scenario: Verify support options on the Support page
    Given I navigate to the Support page
    When I check the available support options
    Then I should see the option for one-time support
    And I should see the option for monthly support

  Scenario: Verify Upgrade option
    Given I navigate to the Support page
    When I check for the upgrade option
    Then I should see an upgrade button labeled "Upgrade"
    
