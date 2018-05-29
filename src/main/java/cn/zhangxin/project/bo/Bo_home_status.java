package cn.zhangxin.project.bo;

import java.util.HashMap;
import org.apache.log4j.Logger;
import cn.zhangxin.project.po.Po_homePage;
import cn.zhangxin.project.po.Po_main;
import cn.zhangxin.project.po.Po_home_status;
import cn.zhangxin.project.util.Log4j;

public class Bo_home_status {
    private Logger log4jLogger;
    private Po_main mainManager;
    private Po_homePage homeManager;
    private Po_home_status statusManager;

    public Bo_home_status(Po_home_status statusManager) {
        //Initialize log4j
        this.log4jLogger = Log4j.logger(Bo_home.class.getName());

        //Initialize WSE Manager Page
        this.statusManager = statusManager;
    }

    public HashMap<String,String> getStatus(){
        //statusManager.clickStatus();
        return statusManager.getStatus();
    }

//    public void navigateToHome() throws Exception{
//        homeManager = mainManager.clickPersonalLinkBy();
//    }
}
