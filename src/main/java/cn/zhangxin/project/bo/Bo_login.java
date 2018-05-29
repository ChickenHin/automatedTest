package cn.zhangxin.project.bo;

import org.apache.log4j.Logger;
import cn.zhangxin.project.po.Po_login;
import cn.zhangxin.project.po.Po_main;
import cn.zhangxin.project.util.Log4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Bo_login {
    private Logger log4jLogger;
    private Po_main mainManager;
    private Po_login loginManager;

    private WebDriver driver;
    private WebDriverWait driverWait;

    public Bo_login(Po_login loginManager) throws Exception{

        //Initialize log4j
        this.log4jLogger = Log4j.logger(Bo_home.class.getName());

        //Initialize WSE Manager Page
        this.loginManager = loginManager;
        this.driver = loginManager.getDriver();
        this.driverWait = new WebDriverWait(driver, 15);

    }

    public Po_main logon() throws Exception {
        loginManager.inputUsername();
        loginManager.inputPassword();
        loginManager.clickLoginButton();
        mainManager = new Po_main(driver);
        return mainManager;
    }

    public void logoff() throws Exception {
        mainManager.clickLogoffButton();
    }
}
