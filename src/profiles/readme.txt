

ANY FILE CREATED IN THIS FOLDER SHOULD NOT SUBMIT TO PERFORCE.
ANY FILE CREATED IN THIS FOLDER SHOULD NOT SUBMIT TO PERFORCE.
ANY FILE CREATED IN THIS FOLDER SHOULD NOT SUBMIT TO PERFORCE.

The already existed files will be removed in future.

This folder is only for Debug cases in IDE with Run As/Debug As TestNG Suite. 
If you start testing from ant , please ignore this one.



0. Create empty file named system.properties 

1. find an properties for feature in properties\example, copy its content to system.properties
	if you do not know which feature example to use, please choose full_system.properties

2. updated system.properties to meet you configuration

3. download supplemental package from cmweb.websense.com
	If this machine is Windows, please download the Windows version, and extract it to C:\WSE_Auto_Tools\Supplemental
	If this machine is Linux, please download the Linux version, and extract it to /opt/WSE_Auto_Tools/Supplemental

4. If WebDriverExecutionType is Local, DriverType is Chrome or IE, please download chromedriver or ie driver from http://www.seleniumhq.org/download/, and set ChromeDriverPath or IEDriverServerPath

5. If WebDriverExecutionType is GRID , DriverType is Firefox ,   

	Start Selenium Grid hub and node:		a. java -jar selenium-server-standalone-2.40.0.jar -role hub		b. java -jar selenium-server-standalone-2.40.0.jar -role webdriver -timeout 600000 -browser "browserName=firefox,version=x,firefox_binary=C:\Program Files (x86)\Mozilla Firefox\firefox.exe,maxInstances=5,platform=WINDOWS" -remoteHost "http://127.0.0.1:8989" -hubHost localhost -port 8989	

	
	And if you run Reporting User Agent cases in Selenium Grid, please start your grid node as:		java -jar selenium-server-standalone-2.40.0.jar -Dwebdriver.firefox.profile=TestUA -role webdriver -port 8989 -hub http://127.0.0.1:4444/grid/register -browser browserName="firefox",version=x,platform=WINDOWS,maxInstances=5
	
	You need create a custom firefox profile named "TestUA", the profile should: Export one user agent report manually and enable ¡°Do this automatically for files like this form mow on¡±. And make sure the default download path is ¡°C:\Users\%users.home%\Downloads¡±	Please refer http://automatictester.co.uk/2013/04/07/selenium-running-custom-firefox-profile/ for how to create custom profile	
	 selenium-server-standalone-2.40.0.jar in above commnds could be update to latest one in http://www.seleniumhq.org/download/, and the the firefox version should be supported by selenium-server-standalone