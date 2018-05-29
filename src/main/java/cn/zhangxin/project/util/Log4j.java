package cn.zhangxin.project.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log4j {
    static private boolean isLog4jConfigLoaded = false;

    static private Properties log4jConfigFileProperties = new Properties();;

    /**
     * @param className*
     * @return
     */

    static public Logger logger(String className) {

        // Only load log4j configuration file only for one time;
        if (!isLog4jConfigLoaded) {
            String path = Paths.get(System.getProperty("user.dir"), "src/profiles",
                    "log4j.properties").toString();
            try (FileInputStream log4jConfigFileInputStream = new FileInputStream(
                    path)) {

                log4jConfigFileProperties.load(log4jConfigFileInputStream);

                // Define the absolute path of log4j log file, for example:
                // if define log.txt in log4j.properties file, it will be mapped
                // to absolute path here.

                String logFileName = log4jConfigFileProperties
                        .getProperty("log4j.appender.file.File");
                String pathlog = Paths.get(System.getProperty("user.dir"),
                        "log", logFileName).toString();
                log4jConfigFileProperties.setProperty(
                        "log4j.appender.file.File", pathlog);

                PropertyConfigurator.configure(log4jConfigFileProperties);

                isLog4jConfigLoaded = true;

            }

            catch (Exception e) {

                String pathlog = Paths.get(System.getProperty("user.dir"),
                        "log", "log.txt").toString();
                log4jConfigFileProperties = new Properties();
                log4jConfigFileProperties.setProperty("log4j.rootLogger",
                        "INFO,console,file");
                log4jConfigFileProperties.setProperty("log4j.appender.file",
                        "org.apache.log4j.RollingFileAppender");
                log4jConfigFileProperties.setProperty(
                        "log4j.appender.file.File", pathlog);
                log4jConfigFileProperties.setProperty(
                        "log4j.appender.file.MaxFileSize", "1024KB");
                log4jConfigFileProperties.setProperty(
                        "log4j.appender.file.MaxBackupIndex", "10");
                log4jConfigFileProperties.setProperty(
                        "log4j.appender.file.layout",
                        "org.apache.log4j.PatternLayout");
                log4jConfigFileProperties.setProperty(
                        "log4j.appender.file.layout.ConversionPattern",
                        "%-5p %d{MMM dd HH:mm:ss} %c:%L - %m%n");



                log4jConfigFileProperties.setProperty("log4j.appender.console",
                        "org.apache.log4j.ConsoleAppender");
                log4jConfigFileProperties.setProperty(
                        "log4j.appender.console.layout",
                        "org.apache.log4j.PatternLayout");
                log4jConfigFileProperties.setProperty(
                        "log4j.appender.console.layout.ConversionPattern",
                        "%5p (%F:%L) - %m%n");
                PropertyConfigurator.configure(log4jConfigFileProperties);
                isLog4jConfigLoaded = true;

            }
        }

        return Logger.getLogger(className);

    }

}
