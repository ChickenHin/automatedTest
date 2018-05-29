package cn.zhangxin.project.po;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cn.zhangxin.project.util.Log4j;

public class Po_homePage {
    private By home_PageBy = By.linkText("我的主页");
    private By home_statusBy = By.xpath("//*[@id=\"frameFixedNav\"]/div/ul/li[5]/a");
    private By home_photosBy = By.xpath("//*[@id=\"frameFixedNav\"]/div/ul/li[3]/a");

    private By statusButtonBy = By.className("global-publisher-status-trigger");
    private By statusTextareaBy = By.xpath("//*[@id=\"global-publisher-status\"]/section/div/form/div/div/div[1]/textarea");
    private By statusSubmitButtonBy = By.xpath("//*[@id=\"global-publisher-status\"]/section/div/form/div/div/div[3]/div[2]/input[2]");

    //Selenium related objects
    private WebDriver driver;
    private WebDriverWait driverWait;

    //Log4j
    private Logger log4jLogger;

    public Po_homePage(WebDriver driver) throws Exception{

        //Initialize log4j
        this.log4jLogger = Log4j.logger(Po_homePage.class.getName());

        //Initialize driver
        this.driver = driver;
        this.driverWait = new WebDriverWait(driver, 180);

        //Check page is navigated to Application -> Browser tab
        driverWait.until(ExpectedConditions.presenceOfElementLocated(home_PageBy));
    }

    public void clickStatusButton() throws Exception {
        driver.findElement(statusButtonBy).click();
        driverWait.until(ExpectedConditions.presenceOfElementLocated(statusButtonBy));
    }

    public void inputStatusText(String statusWords) throws Exception {
        driver.findElement(statusTextareaBy).clear();
        driver.findElement(statusTextareaBy).sendKeys(statusWords);
    }

    public void clickStatusSubmitButton() throws Exception {
        driver.findElement(statusSubmitButtonBy).click();
    }

    public Po_home_status clickHomeStatusTab() throws Exception {
        driver.findElement(home_statusBy).click();
        return new Po_home_status(driver);
    }

    public void clickHomePhotosTab() throws Exception {
        driver.findElement(home_photosBy).click();
    }

}
