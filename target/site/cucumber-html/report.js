$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:src/test/resources/com/alg/Portfolio.feature");
formatter.feature({
  "name": "Login",
  "description": "",
  "keyword": "Feature",
  "tags": [
    {
      "name": "@Portfolio"
    }
  ]
});
formatter.background({
  "name": "",
  "description": "",
  "keyword": "Background"
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "open Chrome",
  "keyword": "Given "
});
formatter.match({
  "location": "GenericSteps.openBrowser(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Navigate to loginURL",
  "keyword": "And "
});
formatter.match({
  "location": "GenericSteps.navigate(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Login into Application",
  "rows": [
    {
      "cells": [
        "Username",
        "ram1624@gmail.com"
      ]
    },
    {
      "cells": [
        "Password",
        "Flower24"
      ]
    }
  ],
  "keyword": "And "
});
formatter.match({
  "location": "ApplicationSteps.login(String,String\u003e)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Login should be Success",
  "keyword": "Then "
});
formatter.match({
  "location": "ApplicationSteps.validateLogin(String)"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "creating a portfolio",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@Portfolio"
    },
    {
      "name": "@verifyPortfolio"
    }
  ]
});
formatter.step({
  "name": "Click on InvestmentTool",
  "keyword": "And "
});
formatter.match({
  "location": "GenericSteps.click(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "verify txtRealTimeQuote",
  "keyword": "Then "
});
formatter.match({
  "location": "ApplicationSteps.verifyPage(String)"
});
formatter.result({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
});