package cn.zhangxin.project.po;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cn.zhangxin.project.util.Log4j;

import java.util.HashMap;
import java.util.List;


public class Po_main {
//    private By newsBy = By.linkText("新鲜事");

    //org.openqa.selenium.InvalidSelectorException: Compound class names are not supported. Consider searching for one class name and filtering the results or use CSS selectors.
    //private By homeLinkBy = By.className("app-nav-item app-nav-item-cur app-homepage").tagName("a");

    private By homeLinkTabBy = By.xpath("//div[@id=\"nxSlidebar\"]/descendant::span[text()=\"个人主页\"]");
    private By myPhotosTabBy = By.xpath("//*[@id=\"nxSlidebar\"]/div[1]/div/ul/li[4]/a/span");

    private By activityListBy = By.className("activity-list");

    private By settingButtonBy = By.className("account-more");
    private By logoffButtonBy = By.className("nv-drop-exit");

    private By searchInputBy = By.id("hd-search");
    private By searchButtonBy = By.className("hd-search-btn");

    HashMap<String,String> activityList = new HashMap<String,String>();

    //Selenium related objects
    private WebDriver driver;
    private WebDriverWait driverWait;

    //Log4j
    private Logger log4jLogger;



    /**
     * Constructor
     * @param driver
     */
    public Po_main(WebDriver driver) throws Exception{

        //Initialize log4j
        this.log4jLogger = Log4j.logger(Po_main.class.getName());

        //Initialize driver
        this.driver = driver;
        this.driverWait = new WebDriverWait(driver, 180);

        //Check page is navigated to Application -> Browser tab
        driverWait.until(ExpectedConditions.presenceOfElementLocated(homeLinkTabBy));
    }

    public Po_homePage clickHomeLinkTab() throws Exception {

        driver.findElement(homeLinkTabBy).click();

        return new Po_homePage(driver);
    }

    public void clickMyPhotosTab() throws Exception {
        driver.findElement(myPhotosTabBy).click();
    }

    public void clickLogoffButton() throws Exception {
        driver.findElement(settingButtonBy).click();
        driver.findElement(logoffButtonBy).findElement(By.tagName("a")).click();
    }

    public void inputSearchWords(String searchWords) throws Exception {
        driver.findElement(searchInputBy).clear();
        driver.findElement(searchInputBy).sendKeys(searchWords);
    }

    public void clickSearchButton() throws Exception {
        driver.findElement(searchButtonBy).click();
    }

    public HashMap<String,String> getActivity() {
        List<WebElement> lis = driver.findElement(activityListBy).findElements(By.tagName("li"));
        for(int i = 1 ; i < lis.size() ; i++){
            WebElement list = driver.findElement(activityListBy);
            String date = list.findElement(By.xpath("//*[@id=\"nxContainer\"]/div[2]/div[1]/div/div[3]/ul/li["+i+"]/span")).getText().trim().toString();
            String content = list.findElement(By.xpath("//*[@id=\"nxContainer\"]/div[2]/div[1]/div/div[3]/ul/li["+i+"]/a")).getText().trim().toString();
            activityList.put(date, content);
        }
        return activityList;
    }
}
