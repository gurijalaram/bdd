package com.alg.webdriver;

import com.alg.reports.ExtentManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sun.rmi.log.LogHandler;
import static org.assertj.core.api.Assertions.*;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class WebConnector {
    WebDriver driver;
    public String name;
    public Properties prop;
    public ExtentReports extentReport;
    public ExtentTest extentTest;

    public WebConnector(){
        if(prop==null){
            try {
                prop = new Properties();
                FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\project.properties");
                prop.load(fis);
            }catch(Exception e){
                    e.printStackTrace();
                }
        }
    }



    public void openBrowser(String browserName){
        if(browserName.equalsIgnoreCase("Mozilla"))
            driver = new FirefoxDriver();
        else if(browserName.equalsIgnoreCase("Chrome")) {
            WebDriverManager.chromedriver().setup();
            HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
            chromePrefs.put("profile.default_content_settings.popups", 0);
           //chromePrefs.put("download.default_directory", new LogHandler().getLocalLogFilePath());
            List<String> chromeArgs = Arrays.asList("start-maximized");
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments(chromeArgs);
            chromeOptions.setAcceptInsecureCerts(true);
            chromeOptions.setExperimentalOption("prefs", chromePrefs);
            this.driver = new ChromeDriver(chromeOptions);
        }
        else if(browserName.equalsIgnoreCase("IE"))
            driver = new InternetExplorerDriver();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    public void navigate(String url) {
        System.out.println(prop.getProperty(url));
        driver.get(prop.getProperty(url));
    }

    public void click(String cssSelector){
        getObject(cssSelector).click();
    }

    public void type(String data,String cssSelector){
        getObject(cssSelector).sendKeys(data);
    }

    public WebElement getObject(String cssSelector){
        WebElement element = null;
        try{
            element = driver.findElement(By.cssSelector(prop.getProperty(cssSelector)));
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(prop.getProperty(cssSelector))));
        }catch(Exception ex){
            reportFailure(ex.getMessage());
        }
        return element;
    }

    public void login(String username, String password){
        infoLog("Enter Username " + username);
        type(username, "txtUsername");
        infoLog("Click continue button ");
       // click("btnContinue");
        infoLog("Enter Password" );
        type(password, "txtPassword");
        click("chkRememberMe");
        infoLog("Click Submit button ");
        click("btnloginSubmit");

    }

    public boolean isElementPresent(String cssSelector){
        List<WebElement> elements = null;
        elements = driver.findElements(By.cssSelector(prop.getProperty(cssSelector)));
        return (elements.size()==0) ? false : true;
    }

    public void validateLogin(String expectedResult){
        boolean result = isElementPresent("lblPortfolioID");
        String actualResult="";
        if(result)
            actualResult="Success";
        else
            actualResult="Failure";

        if(!expectedResult.equalsIgnoreCase(actualResult))
            System.out.println("Scenario Failure");
        else
            System.out.println("Logged in successfullly!!");
    }

    public void acceptAlerts(){
        try {
                if(isAlertBoxPresent()){
                    driver.switchTo().alert().accept();
                    driver.switchTo().defaultContent();
                }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Checks if Alert box is Present - waits up to the timeout value in seconds (default = 10)
     * for an alert box to be detected.
     * @return Boolean true if present. Catches NoAlertPresentException and returns false.
     */
    public boolean isAlertBoxPresent() {
        try {
            WebDriverWait wait = new WebDriverWait (driver, 20);
            wait.until (ExpectedConditions.alertIsPresent ());
            return true;
        } catch (NoAlertPresentException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Takes screen capture of current browser window provided that WebdriverBase has an active, non-null instance. <br><br>
     * Screen capture is named: CURRENTLYRUNNING
     * {@link com.alegeus.framework.testextensions.testcases.AlegeusTestCase#getJobStepID() JOBSTEPID}.PNG.
     */
    public void takeScreenCapture() {
        Date d = new Date();
        String screenShotFile = d.toString().replace(":","_").replace(" ","_")+".png";
        File targetFile = new File(ExtentManager.screenShotFolderPath+screenShotFile);
            try {
                File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(screenshot, targetFile);
                extentTest.log(Status.FAIL,"screenshot -> " + extentTest.addScreenCaptureFromPath(ExtentManager.screenShotFolderPath+screenShotFile));
            }
            catch (Exception ex) {
                infoLog(String.format("Error taking screen capture %s ", targetFile));
            }
        }


    /********************************LOGGING FUNCTIONS ************************/
    public void infoLog(String msg){
        extentTest.log(Status.INFO, msg);
    }

    public void reportFailure(String errMsg){

        extentTest.log(Status.FAIL, errMsg);
        takeScreenCapture();
        assertThat(false);
    }

    /*******************************REPORTING FUNCTIONS ***********************************************/

    public void initReports(String scenarioName){
        extentReport = ExtentManager.getInstance(prop.getProperty("reportPath"));
        extentTest = extentReport.createTest(scenarioName);
        extentTest.log(Status.INFO, "Starting " + scenarioName);
    }

    public void quit(){
        if(extentReport!=null){
            extentReport.flush();
        }
    }


}
