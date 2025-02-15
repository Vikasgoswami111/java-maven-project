package Runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)  // This tells JUnit to run Cucumber
@CucumberOptions(
    features = "src/test/resources/features",  // Path to feature files
    glue = "steps",  // Package containing step definitions
    plugin = { "pretty", "html:target/cucumber-reports.html", "json:target/cucumber.json" },  // Reports
    monochrome = true  // Clean console output
    //tags = "@SmokeTest"  // Run only scenarios with this tag (optional)
)
public class TestRunner {
    // No need for methods; JUnit + Cucumber handles execution.
}
