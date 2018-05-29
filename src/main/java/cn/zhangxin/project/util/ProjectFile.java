package cn.zhangxin.project.util;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Paths;
import java.util.Properties;

public class ProjectFile {
    /**

     * Get absolute path of project folder on Windows and Linux

     * @param folderRelativePath :Relative folder path for file, for example: "conf", "postdata", ...

     * @return :Absolute path of project file on Windows and Linux

     */

    public static String returnPath(String folderRelativePath) {
        return Paths.get(System.getProperty("user.dir"),folderRelativePath).toString();
    }

    /**

     * Get absolute path of project file on Windows and Linux

     * @param folderRelativePath :Relative folder path for file, for example: "conf", "postdata", ...

     * @param fileName :Name of file. If name==null, return the path of folder.

     * @return :Absolute path of project file on Windows and Linux

     */

    public static String returnPath(String folderRelativePath, String fileName) {
        return Paths.get(System.getProperty("user.dir"),folderRelativePath,fileName).toString();

    }



    /**

     * Get the value of key from property file.

     * @param folderRelativePath :Relative folder path for file, for example: "conf", "postdata", ...

     * @param fileName :Name of file. If name==null, return the path of folder.

     * @param keyName

     * @return

     * @throws Exception

     */

    public static String read(String folderRelativePath, String fileName, String keyName) throws Exception{

        FileInputStream fileInputStream = null;



        try {

            Properties projectFileProperties = new Properties();

            fileInputStream = new FileInputStream(returnPath(folderRelativePath, fileName));

            projectFileProperties.load(fileInputStream);

            String value = projectFileProperties.getProperty(keyName);

            if (value != null){

                return value;

            } else {
                //throw new Exception("NULL: the value of key: " + keyName + "; in file: " + fileName);
                //do not throw exception here. Sometimes NULL may be as expected, e.g., if Log Server is not installed, LogServerIP will not be specified. The value may be used for checking is log server is installed.
                return null;
            }

        } catch (Exception e) {

            e.printStackTrace();

            throw new Exception("Fail to read the value of key: " + keyName + "; from file: " + fileName);

        } finally{

            fileInputStream.close();

        }



    }

    public static String readWithTrim(String folderRelativePath, String fileName, String keyName) throws Exception{

        return read( folderRelativePath,  fileName,  keyName).trim();
    }

    public static Properties getPropertiesObj(String folderRelativePath, String fileName) throws Exception {

        Properties projectFileProperties = new Properties();
        FileInputStream fileInputStream = null;

        // Load configuration from "conf/system.properties"

        try {
            fileInputStream = new FileInputStream(returnPath(folderRelativePath, fileName));

            projectFileProperties.load(fileInputStream);

            return projectFileProperties;


        } catch (Exception e) {

            e.printStackTrace();

            throw new Exception("Fail to load file: " + fileName);

        }finally{

            fileInputStream.close();

        }

    }

    /**

     * @param key :Read "Key" from system.properties

     * @return	:Return value of "Key"

     * @throws Exception

     */

    public static String readFromSystemProperties(String key) throws Exception {

        try {


            Properties sysProperties = getPropertiesObj("conf", "system.properties");

            String value = sysProperties.getProperty(key);

            return value;

        } catch (Exception e) {

            e.printStackTrace();

            throw new Exception("Fail to read the " + key

                    + " from system.properties.");

        }
    }

    /**
     * @param key :Read "Key" from xidconfig.properties
     * @return	:Return value of "Key"
     * @throws Exception
     */
    public static String readFromXidConfigProperties(String key) throws Exception {
        try {


            Properties xidProperties = getPropertiesObj("conf", "xidconfig.properties");
            String value = xidProperties.getProperty(key);

            return value;

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Fail to read the " + key
                    + " from xidconfig.properties.");
        }
    }

}
