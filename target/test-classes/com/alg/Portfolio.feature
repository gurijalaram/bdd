@Portfolio
Feature:Login
    Background:
    Given open Chrome
    And Navigate to loginURL
    And Login into Application
    |Username|ram1624@gmail.com|
    |Password|Flower24|
    Then Login should be Success

    @verifyPortfolio
    Scenario: creating a portfolio
      And Click on InvestmentTool
      Then verify txtRealTimeQuote

