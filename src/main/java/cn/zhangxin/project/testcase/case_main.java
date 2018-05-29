package cn.zhangxin.project.testcase;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import cn.zhangxin.project.bo.*;
import cn.zhangxin.project.po.*;
import cn.zhangxin.project.util.*;
public class case_main {
    public static Po_login loginManager;
    private Po_main mainManager;
    public static Po_homePage homeManager;

    private Bo_main bo_main;
    private Bo_login bo_login;

    private Logger caseLogger;

    @BeforeClass(alwaysRun = true)
    public void logonManagerAndInitilize() throws Exception {

        caseLogger = Log4j.logger(case_main.class.getName());

        loginManager = new Po_login();

        bo_login = new Bo_login(loginManager);
        mainManager = bo_login.logon();
        bo_main = new Bo_main(mainManager);
    }

//    @AfterClass(alwaysRun = true)
//    public void logoffManager() throws Exception {
//        bo_login.logoff();
//    }

    @Test(enabled = true, alwaysRun = true, priority = 0)
    public void testGetActivity() throws Exception {
        HashMap<String , String> activityList = bo_main.getActivity();

        Set<String> expKey = activityList.keySet();
        Iterator<String> itKey= expKey.iterator();
        while( itKey.hasNext()){
            String key = itKey.next();
            caseLogger.info(key + " : " + activityList.get(key));
        }
    }

    @Test(enabled = true, alwaysRun = true,priority = 1)
    public void testNavigation() throws Exception {
        bo_main.navToMyPhotos();
        homeManager = bo_main.navToHomePage();
    }
}
