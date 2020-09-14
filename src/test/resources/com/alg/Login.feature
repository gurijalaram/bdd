Feature: Login

@Login @Porfolio
Scenario Outline: Login into Rediff Money
    Given open <Browser>
    And Navigate to loginURL
    And Login into Application
    |Username|ram1624@gmail.com|
    |Password|Flower24|
    Then Login should be <Result>

    Examples:
        |Browser    |   Result  |
        |Chrome     |   Success |

