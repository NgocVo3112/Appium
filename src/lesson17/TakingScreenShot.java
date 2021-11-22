package lesson17;

import Driver.DriverFactory;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.List;

public class TakingScreenShot {
    public static void main(String[] args) {
        DriverFactory.startAppiumServer();
        try {
            AndroidDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();
            MobileElement loginLabel = androidDriver.findElementByAccessibilityId("Login");
            loginLabel.click();
            MobileElement loginBtn = androidDriver.findElementByAccessibilityId("button-LOGIN");

            // Wait until we are on Login screen
            WebDriverWait wait = new WebDriverWait(androidDriver, 5L);
            wait.until(ExpectedConditions.visibilityOf(loginBtn));

            //  Taking whole screen
            File base64Screenshot = androidDriver.getScreenshotAs(OutputType.FILE);
            String fileLocation = System.getProperty("User.dir").concat("/screenshot/").concat("loginForm.png");
            FileUtils.copyFile(base64Screenshot, new File(fileLocation));

            // Taking Login element button screenshot
            File base64LoginBtnScreenshot = loginBtn.getScreenshotAs(OutputType.FILE);
            String loginBtnImgFileLocation = System.getProperty("User.dir").concat("/screenshot/").concat("LoginBtnScreenshot.png");
            FileUtils.copyFile(base64LoginBtnScreenshot, new File(loginBtnImgFileLocation));

            // Taking area screenshot
            List<MobileElement> viewGroupElems = androidDriver.findElementsByXPath("//android.view.ViewGroup/android.view.ViewGroup[2]");
            if(!viewGroupElems.isEmpty()) {
                File base64ViewGroupScreenshot = viewGroupElems.get(viewGroupElems.size()-1).getScreenshotAs(OutputType.FILE);
                String ViewGroupImgFileLocation = System.getProperty("User.dir").concat("/screenshot/").concat("ViewGroupScreenshot.png");
                FileUtils.copyFile(base64ViewGroupScreenshot, new File(ViewGroupImgFileLocation));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DriverFactory.stopAppiumServer();
        }
    }
}

