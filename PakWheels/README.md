# Installation Guide- Android Automation through Appium


**1- JAVA:**    
---
1- Go to this website and download jdk file: 

   **Website Link:** https://login.oracle.com/mysso/signon.jsp

2- Install java (Don’t change destination folder)

3- Setup environment variable. For that Go to This PC > right click > properties > advanced system settings > environment variable. Now, add path in system variable as

   * **Name:** JAVA_HOME

   * **Value:** C:\Program Files\Java\jdk1.8.0_291

4- Also, edit the “path” in the system variable as:

   * **Path:** C:\Program Files\Java\jdk1.8.0_291\bin

5- Go to cmd and write “java -version” to know if java is installed.

   **Note:** To uninstall java, go to control panel > programs > Search java > Uninstall




**2- Android Studio**
----
1- Go to this website and download: 

* **Website Link:** https://developer.android.com/studio?gclid=Cj0KCQiAip-PBhDVARIsAPP2xc29H20LpmsFgY6R7qnBg1-AqoFLo3YU-XM1SrMVcvw_BnFK1BHFkyQaAm9AEALw_wcB&gclsrc=aw.ds

2- Install it by clicking next

3- After installing, select do not import settings

4- Select standard

5- Set system variables

* **Name:** ANDROID_HOME

* **Value:** C:\Users\PakWheels\AppData\Local\Android\Sdk

6- Edit in path variable under system variables and add these:

* **Path:** C:\Users\PakWheels\AppData\Local\Android\Sdk\tools\bin

* **Path:** C:\Users\PakWheels\AppData\Local\Android\Sdk\tools

* **Path:** C:\Users\PakWheels\AppData\Local\Android\Sdk\platform-tools




**3. Appium**
---
There are two ways to install appium

**1- Appium through cmd**

1. First we need to install Node

2. Go to website and Download .msi file

  * **Website Link:** https://nodejs.org/en/download/

3. Click next and install node

4. Set system variables

   * **Name:** NODE_HOME

   * **Value:** C:\Program Files\nodejs

5. Edit in path: C:\Program Files\nodejs\node_modules\npm\bin

6. Go to cmd (administrator mode)

   * **Run command:** npm install -g appium

   * **Rum command:** appium (Allow access if needed > Port will be shown)

**Note:** To uninstall appium from command line, Run Command: npm uninstall -g appium

**2- Appium Client**

1. Go to link and download appium client exe file

* **Website Link:** https://github.com/appium/appium-desktop/releases/tag/v1.22.0

2. Install appium server


**Appium Inspector:**
For Appium own locator/inspector: https://github.com/appium/appium-inspector/releases

Article link: https://www.automationtestinghub.com/appium-inspector-download-install/
1. Install .exe file
2. Set same capabilities as of code
3. Save and start session

**4. Eclipse**
---
1- Download eclipse for java developers: 

* **Website Link:** https://www.eclipse.org/downloads/packages/

**Note:** To uninstall eclipse, remove every file from PC. For example: Desktop/ Downloads or C:\Users\PakWheels\.eclipse or workspace




**5. Vysor:**
---
1- Install vysor on phone and laptop

* **Desktop app link:**  https://www.vysor.io/download/

2- On the desktop app, start the play button and no need to do anything with mobile application.

**Note:** Mobile and laptop will be connected through a usb cable. 




**6. Setup Maven**
---
1- Go to link: https://mvnrepository.com/

2- Download dependencies (Copy code)

 * **Java client:** https://mvnrepository.com/artifact/io.appium/java-client

 * **Selenium Java:** https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java
 
 * **TestNG:** https://mvnrepository.com/artifact/org.testng/testng 

3- Add dependencies in pom.xml under <dependencies> </dependencies>

4- Select project from headers > build automatically

5- Right click on project name > maven > update project > ok

**Note:** Never use beta version



**7. TestNG**
---
There are two ways to install testng.

1- Maven dependency

   * **Article Link:** https://testng.org/doc/download.html

2-. Eclipse new software

1. Help > install new software > Enter Site URL

   * **Site URL:** https://testng.org/testng-eclipse-update-site 

2. Press enter, it will be installed shortly




**8. Connect Real Android Device**
---
* **Article:** https://developer.chrome.com/docs/devtools/remote-debugging/

1- Enabling developer options: Settings > About phone > Build number (Tap 7 times)

2- Enable USB debugging: Developer options > Check USB debugging > OK



**Getting started with Project:**
---
1- Right click on eclipse > New > Maven project > Enter project name/ ID

2- Right click src/test.java > new > class

3- Copy .apk file and paste in src


4- To check if device is connected, in cmd (as administrator) run command

* **Command:** adb devices

5- To run appium from cmd (as administrator), run command

* **Command:** appium

6- For opening Ui locator, in another cmd (as administrator) run command

* **Command:** uiautomatorviewer

**Note:** For java class, Delete module-info.java to remove errors

**9. GitHub**
---
1- Go to github > Create account > Create new repository 

* **Website Link:** https://github.com/

**10. Git**
---
1- Download git

* **Link:** https://git-scm.com/download/win

2- Select next > Windows command prompt > enter "git --version"

3- Go to eclipse workspace: C:\Users\PakWheels\eclipse-workspace\project-name

4- Go into that folder using cd command

**Commands**

* git init (hidden file will be created)

* git config –global user.name “Iqra”

* git config –global user.email “iqra.habib@pakeventures.com”

* git remote add origin “Github URL”

* git add -A OR  git add *

* git status

* git commit -a OR git commit -m ”Comments”

* git push origin master





**11. Extent Reports:**
---
1- Add maven dependency

* **Website Link:** https://mvnrepository.com/artifact/com.aventstack/extentreports
