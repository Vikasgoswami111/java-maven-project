QE Tech Assessment - ReadMe

#Prerequisites:-
Ensure the following software is installed:

Java (JDK 8 or later)

Apache Maven

Eclipse IDE (or any preferred Java IDE)

#Git
Project Setup
Clone the repository from GitHub:
git clone <repository-url>
cd <repository-folder>

Open Eclipse and import the project:
File > Import > Maven > Existing Maven Projects
Select the cloned repository folder
Click Finish

#Verify Maven dependencies by running:

mvn clean install

Running the Tests

Execute tests using Maven:

To run all tests:

mvn test

To run specific tests:

mvn -Dtest=<TestClassName> test

To run tests with Cucumber:

mvn verify

Understanding Test Reports

Reports are generated automatically after execution:

HTML Report:

Location: target/cucumber-reports/index.html

Open in any browser to view test execution details.

Useful for CI/CD integration.

Configuration (pom.xml)

Ensure the pom.xml contains the required dependencies:

<dependencies>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-api</artifactId>
        <version>5.7.0</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>io.cucumber</groupId>
        <artifactId>cucumber-java</artifactId>
        <version>7.0.0</version>
    </dependency>
    <dependency>
        <groupId>io.cucumber</groupId>
        <artifactId>cucumber-junit</artifactId>
        <version>7.0.0</version>
    </dependency>
</dependencies>

Additional Notes

Ensure all test data is placed in the correct input directory (/app/in).

Review src/test/resources for test feature files and step definitions.
