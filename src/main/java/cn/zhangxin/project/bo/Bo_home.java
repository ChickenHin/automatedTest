package cn.zhangxin.project.bo;

import cn.zhangxin.project.po.Po_home_status;
import org.apache.log4j.Logger;
import cn.zhangxin.project.po.Po_homePage;
import cn.zhangxin.project.po.Po_main;
import cn.zhangxin.project.util.Log4j;

public class Bo_home {
    private Logger log4jLogger;
    private Po_main mainManager;
    private Po_homePage homeManager;
    private Po_home_status statusManager;


    public Bo_home(Po_homePage homeManager) throws Exception{

        //Initialize log4j
        this.log4jLogger = Log4j.logger(Bo_home.class.getName());

        //Initialize WSE Manager Page
        this.homeManager = homeManager;

    }

    public void publishStatus(String statusWords) throws Exception {
        homeManager.clickStatusButton();
        homeManager.inputStatusText(statusWords);
        homeManager.clickStatusSubmitButton();
    }

    public void navToHomePhotos() throws Exception {
        homeManager.clickHomePhotosTab();
    }

    public Po_home_status navToHomeStatus() throws Exception {
        statusManager = homeManager.clickHomeStatusTab();
        return statusManager;
    }
}
