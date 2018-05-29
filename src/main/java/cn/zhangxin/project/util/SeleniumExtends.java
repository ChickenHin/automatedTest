package cn.zhangxin.project.util;

import java.util.Set;



import org.openqa.selenium.By;

import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.NoSuchElementException;

import org.openqa.selenium.NoSuchWindowException;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.internal.WrapsDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;


public class SeleniumExtends {
    /**

     * Switch to designated frame.

     * @param driver

     * @param frameLocator :Frame locator (ID or Name).

     * @throws Exception

     */

    public static void switchToFrame(WebDriver driver, By frameLocator) throws Exception{

        try {

            WebDriverWait driverWait = new WebDriverWait(driver, 15);

            driverWait.until(ExpectedConditions.presenceOfElementLocated(frameLocator));

            driver.switchTo().frame(driver.findElement(frameLocator));

        } catch (Exception e) {

            e.printStackTrace();

            throw new Exception("Fail to switch to frame: " + frameLocator.toString());

        }

    }



    /**

     * Alter the attribute value of a designated attribute.

     * @param element

     * @param attributeName

     * @param attributeValue

     */

    public static void setAttribute(WebElement element, String attributeName, String attributeValue){

        WrapsDriver wrappedElement = (WrapsDriver) element;

        JavascriptExecutor javascriptDriver = (JavascriptExecutor) wrappedElement.getWrappedDriver();



        javascriptDriver.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, attributeName, attributeValue);



    }





    /**

     * Click a Web Element by JavaScript.

     * @param element

     */

    public static void clickElementByJavascript(WebElement element){

        WrapsDriver wrappedElement = (WrapsDriver) element;

        JavascriptExecutor javascriptDriver = (JavascriptExecutor) wrappedElement.getWrappedDriver();



        javascriptDriver.executeScript("arguments[0].click();", element);

    }



    /**

     * Remove the designated attribute.

     * @param element

     * @param attributeName

     */

    public static void removeAttribute(WebElement element, String attributeName){

        WrapsDriver wrappedElement = (WrapsDriver) element;

        JavascriptExecutor javascriptDriver = (JavascriptExecutor) wrappedElement.getWrappedDriver();



        javascriptDriver.executeScript("arguments[0].removeAttribute(arguments[1])", element, attributeName);

    }



    /**

     * Check the element exist or not

     * @param driver

     * @param locator

     * @return

     */



    public static boolean isElementExsit(WebDriver driver, By locator) {

        boolean flag = false;

        try {

            WebElement element=driver.findElement(locator);

            flag=null!=element;



        } catch (NoSuchElementException e) {



        }

        return flag;

    }



    public static boolean ButtonDisableStatus(WebDriver driver,By Locator) throws Exception{

        boolean flag=false;



        try {

            String ElementStatus=driver.findElement(Locator).findElement(By.cssSelector("span")).getAttribute("style");

            if (ElementStatus.equals("color: gray;"))

            {

                flag=true;

                return flag;

            }

            return flag;

        }

        catch (Exception e)

        {

            return flag;

        }



    }





    public static boolean CheckBoxDisableStatus(WebDriver driver,By Locator) throws Exception{

        boolean flag=false;



        try {



            String[] ElementString=driver.findElement(Locator).getAttribute("src").split("\\/");

            String ElementStatus=ElementString[ElementString.length-1];

            if ((ElementStatus.equals("disabled_checked.gif"))||((ElementStatus.equals("disabled_unchecked.gif"))))

            {

                flag=true;

                return flag;

            }

            return flag;

        }

        catch (Exception e)

        {

            return flag;

        }



    }



    public static boolean RadioButtonDisableStatus(WebDriver driver,By Locator) throws Exception{

        boolean flag=false;



        try {



            boolean ElementStatus=driver.findElement(Locator).isEnabled();

            flag=!ElementStatus;

            return flag;



        }

        catch (Exception e) {

            return flag;

        }



    }



    public static WebDriver switchToWindow(WebDriver driver){

        WebDriver newDriver =null;

        try {

            String currentHandle = driver.getWindowHandle();

            Set<String> handles = driver.getWindowHandles();

            for (String s : handles) {

                if (s.equals(currentHandle))

                    continue;

                else {

                    newDriver=driver.switchTo().window(s);



                }

            }

        }catch (NoSuchWindowException e) {

            e.printStackTrace();

        }

        return newDriver;

    }

}
