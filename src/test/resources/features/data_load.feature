Feature: Data Load and Transformation

  Scenario: Process input files and generate output file
    Given the input files "C:\Users\ASUS\eclipse-workspace\test-automation-suite\src\test\resources\Test_data\InstrumentDetails.csv" and "C:\Users\ASUS\eclipse-workspace\test-automation-suite\src\test\resources\Test_data\PositionDetails.csv" are available
    When the data is processed
    Then the output file "PositionReport.csv" should be generated with correct data
