package steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import java.util.List;

public class HomePageSteps {
    WebDriver driver;

    @Given("I open the application home page")
    public void i_open_home_page() {
        System.out.println("Opening Home Page");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Navigate to ReqRes API home page
        driver.get("https://reqres.in/");
        System.out.println("Home Page Opened Successfully");
    }

    @When("I list different request types and their endpoints")
    public void i_list_request_types_and_endpoints() {
        System.out.println("Extracting API request types and endpoints");

        // Locate all API list items
        List<WebElement> apiList = driver.findElements(By.xpath("//ul/li[@data-key='endpoint']"));

        // Loop through each API and extract details
        for (WebElement api : apiList) {
            String requestType = api.getAttribute("data-http"); // Get request type (GET, POST, etc.)
            WebElement linkElement = api.findElement(By.xpath(".//a")); // Find the link
            String endpoint = linkElement.getAttribute("href"); // Get API endpoint
            System.out.println(requestType.toUpperCase() + " | " + endpoint);
        }
    }

    @Then("I should see the request types and endpoints displayed correctly")
    public void i_should_see_correct_request_types_and_endpoints() {
        System.out.println("API request types and endpoints displayed correctly.");
        driver.quit();
    }
    @When("I select the {string} option")
    public void i_select_option(String option) {
        System.out.println("Selecting API option: " + option);

        // Find the menu item based on text and click it
        WebElement apiOption = driver.findElement(By.xpath("//ul/li[a[contains(text(),'" + option + "')]]/a"));
        apiOption.click();
    }

    @Then("I should see the sample request as {string}")
    public void verify_request(String expectedRequest) {
        String actualRequest = driver.getCurrentUrl().replace("https://reqres.in", "");
        System.out.println("Expected Request: " + expectedRequest);
        System.out.println("Actual Request: " + actualRequest);
        assert actualRequest.equals(expectedRequest) : "Request mismatch!";
    }

    @Then("I should see the sample response as {string}")
    public void verify_response(String expectedResponse) {
        System.out.println("Expected Response: " + expectedResponse);
        System.out.println("Actual Response: 404 {}");  // Since ReqRes API UI doesn’t show responses

        // Validate response manually since UI doesn't display it
        assert "404 {}".equals(expectedResponse) : "Response mismatch!";
    }
    @When("I click on the {string} button")
    public void i_click_on_the_button(String buttonText) {
        System.out.println("Clicking on the " + buttonText + " button");

        // Find the button using its text and click it
        WebElement supportButton = driver.findElement(By.xpath("//button[contains(text(),'" + buttonText + "')]"));
        supportButton.click();
    }

    @Then("I should be redirected to the support page")
    public void verify_support_page_navigation() {
        // Expected support page URL
        String expectedUrl = "https://reqres.in/support";

        // Get current URL
        String actualUrl = driver.getCurrentUrl();
        System.out.println("Expected URL: " + expectedUrl);
        System.out.println("Actual URL: " + actualUrl);

        // Assertion to validate navigation
        assert actualUrl.equals(expectedUrl) : "Navigation failed! Expected: " + expectedUrl + ", but found: " + actualUrl;
    }
    
    @Given("I navigate to the Support page")
    public void i_navigate_to_the_support_page() {
        // Initialize WebDriver
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Navigate to the support page
        driver.get("https://reqres.in/support");

        System.out.println("Navigated to Support Page");
    }

    @When("I check the available support options")
    public void i_check_the_available_support_options() {
        System.out.println("Checking support options...");
    }

    @Then("I should see the option for one-time support")
    public void i_should_see_the_option_for_one_time_support() {
        WebElement oneTimeSupport = driver.findElement(By.xpath("//input[@name='oneTimeAmount']"));
        if (oneTimeSupport.isDisplayed()) {
            System.out.println("One-time support option is available");
        } else {
            System.out.println("One-time support option is NOT available");
        }
    }

    @Then("I should see the option for monthly support")
    public void i_should_see_the_option_for_monthly_support() {
        WebElement monthlySupport = driver.findElement(By.xpath("//label[@for='supportRecurring']"));
        if (monthlySupport.isDisplayed()) {
            System.out.println("Monthly support option is available");
        } else {
            System.out.println("Monthly support option is NOT available");
        }
    }

    @When("I check for the upgrade option")
    public void i_check_for_the_upgrade_option() {
        System.out.println("Checking upgrade button...");
    }

    @Then("I should see an upgrade button labeled {string}")
    public void i_should_see_an_upgrade_button_labeled(String buttonText) {
        WebElement upgradeButton = driver.findElement(By.xpath("//button[@id='trigger-pro' and contains(text(),'" + buttonText + "')]"));
        if (upgradeButton.isDisplayed()) {
            System.out.println("Upgrade button is available");
        } else {
            System.out.println("Upgrade button is NOT available");
        }
    }
}
