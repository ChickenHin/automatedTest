package cn.zhangxin.project.testcase;

import org.apache.log4j.Logger;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import cn.zhangxin.project.bo.*;
import cn.zhangxin.project.po.*;
import cn.zhangxin.project.util.*;

public class case_homePage {
    @Autowired
    private Po_homePage homeManager;
    public static Po_home_status statusManager;

    private Bo_home bo_home;

    private Logger caseLogger;

    @BeforeClass(alwaysRun = true)
    public void initilize() throws Exception {

        caseLogger = Log4j.logger(case_homePage.class.getName());
        homeManager = case_main.homeManager;
        bo_home = new Bo_home(homeManager);
    }

    @Test(alwaysRun = true, enabled = true, priority = 2, dataProvider="statusWordsDP")
    public void testPublishStatus(String statusWords) throws Exception {
        bo_home.publishStatus(statusWords);
    }
    @DataProvider(name="statusWordsDP")
    private Object[][] statusWordsDP() {
        return new Object[][] {
                {"哈哈哈"},
                {"开心"}
        };
    }

    @Test(alwaysRun = true, enabled = true, priority = 3)
    public void testHomeNavigation() throws Exception {
        bo_home.navToHomePhotos();
        statusManager = bo_home.navToHomeStatus();
    }
}
