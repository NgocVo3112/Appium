package lesson13;

import caps.MobileCapabilityTypeEx;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class LaunchApp {
    public static void main(String[] args) throws MalformedURLException {
        AppiumDriver<MobileElement> appiumDriver = null;

        try {
            // Setup DesiredCapabilities
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability(MobileCapabilityTypeEx.PLATFORM_NAME, "Android");
            desiredCapabilities.setCapability(MobileCapabilityTypeEx.AUTOMATION_NAME, "uiautomator2");
            desiredCapabilities.setCapability(MobileCapabilityTypeEx.UDID, "NFDIVKMV99999999");
            desiredCapabilities.setCapability(MobileCapabilityTypeEx.APP_PACKAGE, "com.wdiodemoapp");
            desiredCapabilities.setCapability(MobileCapabilityTypeEx.APP_ACTIVITY, "com.wdiodemoapp.MainActivity");
            desiredCapabilities.setCapability(MobileCapabilityTypeEx.NEW_COMMAND_TIMEOUT, 120);

            //Connect to appium server {capabilities} -----> Appium server:4723
            URL appiumServer = new URL("http://localhost:4723/wd/hub");
            appiumDriver = new AppiumDriver<>(appiumServer, desiredCapabilities);
        } catch (Exception ignore){}

        //Interact with Mobile Element
        if(appiumDriver !=null){
            appiumDriver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
            System.out.println("Connected to appium server and launched target app!");
            MobileElement loginLabel = appiumDriver.findElementByAccessibilityId("Login");
            loginLabel.click();
        }else {
            System.out.println("Error when connecting with Appium driver");
        }



    }
}
