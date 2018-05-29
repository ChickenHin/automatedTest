package cn.zhangxin.project.webdriver;

import cn.zhangxin.project.util.Log4j;
import cn.zhangxin.project.util.ProjectFile;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeDriverService;

public class DriverDefine {
    private WebDriver driver;
//    private DesiredCapabilities capabilities;
    private String driverType;
    private String driverPath;
    //Log4j
    private Logger log4jLogger;

    public DriverDefine() throws Exception {
        this.log4jLogger = Log4j.logger(DriverDefine.class.getName());

        this.driverType = ProjectFile.read("src/profiles", "system.properties","DriverType");

        if (driverType.equals("Firefox")) {

            // 通过ssl认证
            ProfilesIni profilesIni = new ProfilesIni();
            FirefoxProfile profile = profilesIni.getProfile("default");
//            profile.setPreference("browser.helperapps.neverAsk.saveToDisk" , "application/csv;text/csv;");
//            profile.setPreference("browser.helperApps.alwaysAsk.force", false);
//            profile.setPreference("browser.download.manager.showWhenStarting",false);
//            profile.setPreference("browser.download.folderList", 2);
//            profile.setPreference("browser.download.dir",System.getProperty("user.home").toString() + "\\Downloads");
              profile.setAcceptUntrustedCertificates(true);
              profile.setAssumeUntrustedCertificateIssuer(false);

            // 创建Firefox driver
            driver = new FirefoxDriver(profile);

        } else if (driverType.equals("IE")) {
            // IEdriverserver存放路径
            this.driverPath = ProjectFile.read("src/profiles", "system.properties","IEDriverPath");
            System.setProperty("webdriver.ie.driver", driverPath);

            // 创建IE driver
            this.driver = new InternetExplorerDriver();

            // 通过ssl认证
            driver.navigate().to("javascript:document.getElementById('overridelink').click()");

        } else if (driverType.equals("Chrome")) {
            // chromedriver存放路径
            this.driverPath = ProjectFile.read("src/profiles", "system.properties","ChromeDriverPath");
            System.setProperty("webdriver.chrome.driver", driverPath);

            // 通过ssl认证
            DesiredCapabilities capability = DesiredCapabilities.chrome();
            capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

            // 创建Chrome driver
            this.driver = new ChromeDriver(capability);

        } else {
            throw new Exception(

                    "Invalid value of 'DriverType'. Must be 'Firefox | IE | Chrome'.");
        }

    }

    public WebDriver getDriver() {
        return driver;
    }
}
