package cn.zhangxin.project.po;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cn.zhangxin.project.util.Log4j;

public class Po_home_status {
    private By statusListBy = By.id("my-status-con");
    private By myhomePageBy = By.linkText("我的主页");

    HashMap<String,String> statusList = new HashMap<String,String>();

    private WebDriver driver;
    private WebDriverWait driverWait;

    //Log4j
    private Logger log4jLogger;

    public Po_home_status(WebDriver driver) throws Exception {

        //Initialize log4j
        this.log4jLogger = Log4j.logger(Po_homePage.class.getName());

        //Initialize driver
        this.driver = driver;
        this.driverWait = new WebDriverWait(driver, 180);

        //Check page is navigated to Application -> Browser tab
        driverWait.until(ExpectedConditions.presenceOfElementLocated(myhomePageBy));
    }

    public HashMap<String,String> getStatus() {

        List<WebElement> lis = driver.findElement(statusListBy).findElements(By.tagName("li"));
        for(int i = 1 ; i < lis.size() ; i++){
            WebElement list = driver.findElement(statusListBy);
            WebElement calender1 = list.findElement(By.xpath("//ul/li["+i+"]/div/div[1]/span[1]"));
            WebElement calender2 = list.findElement(By.xpath("//ul/li["+i+"]/div/div[1]/span[2]"));
            String date = calender1.getText().trim().toString() + "-" + calender2.getText().trim().toString();
            String content = list.findElement(By.xpath("//ul/li["+i+"]/div/div[2]/div[2]")).getText().trim().toString();
            statusList.put(date, content);
        }
        return statusList;
    }
}
