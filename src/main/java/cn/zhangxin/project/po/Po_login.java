package cn.zhangxin.project.po;

import org.apache.log4j.Logger;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cn.zhangxin.project.webdriver.DriverDefine;
import cn.zhangxin.project.util.*;

public class Po_login {
    private By usernameBy = By.id("email");
    private By passwordBy = By.id("password");
    private By loginButtonBy = By.id("login");

    private DriverDefine driverManager = new DriverDefine();
    //Selenium related objects
    private WebDriver driver;
    private WebDriverWait driverWait;

    //Log4j
    private Logger log4jLogger;


    // Entered data
    private String logonUsernameStr;

    private String logonPasswordStr;

    private String url;



    /**
     * Constructor
     * @param
     */
    public Po_login() throws Exception{

        this.log4jLogger = Log4j.logger(Po_login.class.getName());


        // Read parameters from property file
        this.logonUsernameStr = ProjectFile.read("src/profiles", "system.properties","username");

        this.logonPasswordStr = ProjectFile.read("src/profiles", "system.properties","password");

        this.url = ProjectFile.read("src/profiles", "system.properties","url");

        //this.driverManager = driverManager;
        this.driver = driverManager.getDriver();
        this.driverWait = new WebDriverWait(driver, 15);


        //Access manager page.
        driver.get("http://" + url);

        // Ensure logon page is loaded
        driverWait.until(ExpectedConditions.elementToBeClickable(loginButtonBy));
        driver.manage().window().maximize();
    }

    public void inputUsername(){
        driver.findElement(usernameBy).clear();
        driver.findElement(usernameBy).sendKeys(logonUsernameStr);
    }

    public void inputPassword(){
        driver.findElement(passwordBy).clear();
        driver.findElement(passwordBy).sendKeys(logonPasswordStr);
    }

    public void clickLoginButton(){

        driver.findElement(loginButtonBy).click();
    }

    public WebDriver getDriver() {
        return driver;
    }

}
