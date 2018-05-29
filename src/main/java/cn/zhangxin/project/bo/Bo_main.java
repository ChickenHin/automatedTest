package cn.zhangxin.project.bo;

import org.apache.log4j.Logger;
import cn.zhangxin.project.po.Po_homePage;
import cn.zhangxin.project.po.Po_main;
import cn.zhangxin.project.util.Log4j;

import java.util.HashMap;

public class Bo_main {
    private Logger log4jLogger;
    private Po_main mainManager;
    private Po_homePage homeManager;

    public Bo_main(Po_main mainManager) {
        this.log4jLogger = Log4j.logger(Bo_home.class.getName());

        this.mainManager = mainManager;
    }

    public Po_homePage navToHomePage() throws Exception {
        homeManager = mainManager.clickHomeLinkTab();
        return homeManager;
    }

    public void navToMyPhotos() throws Exception {
        mainManager.clickMyPhotosTab();
    }

//    public void logoff(String...args) throws Exception {
//        mainManager.clickLogoffButton();
//    }

    public int mainSearch(String searchWords) throws Exception {
        int status = 1;
        try {
            mainManager.inputSearchWords(searchWords);
            mainManager.clickSearchButton();
        } catch (Exception e) {
            e.printStackTrace();
            log4jLogger.info("get exception when search poi by keyword: " + searchWords);
            status = -1;
        }
        return status;
    }

    public HashMap<String, String> getActivity() throws Exception {
        return mainManager.getActivity();
    }

}
