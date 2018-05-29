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

public class case_home_status {
    private Po_login loginManager;
    private Po_home_status statusManager;

    private Bo_home bo_home;
    private Bo_login bo_login;
    private Bo_home_status bo_home_status;

    private Logger caseLogger;

    @BeforeClass(alwaysRun = true)
    public void initilize() throws Exception {
        caseLogger = Log4j.logger(case_home_status.class.getName());

        statusManager = case_homePage.statusManager;
        loginManager = case_main.loginManager;

        bo_home_status = new Bo_home_status(statusManager);
        bo_login = new Bo_login(loginManager);
    }

    @AfterClass(alwaysRun = true)
    public void logoffManager() throws Exception {
        bo_login.logoff();
    }

    @Test(enabled = true, alwaysRun = true, priority = 4)
    public void testHomeStatus() throws Exception {

        HashMap<String , String> status = bo_home_status.getStatus();

        Set<String> expKey = status.keySet();
        Iterator<String> itKey= expKey.iterator();
        while( itKey.hasNext()){
            String key = itKey.next();
            caseLogger.info(key + " : " + status.get(key));
        }
    }
}
