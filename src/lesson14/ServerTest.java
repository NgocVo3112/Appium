package lesson14;

import caps.MobileCapabilityTypeEx;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class ServerTest {
    public static void main(String[] args) throws IOException {
        AppiumServiceBuilder appiumServiceBuilder = new AppiumServiceBuilder();
        appiumServiceBuilder.withIPAddress("127.0.0.1").usingAnyFreePort();

        AppiumDriverLocalService appiumService = AppiumDriverLocalService.buildService(appiumServiceBuilder);
        appiumService.start();

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityTypeEx.PLATFORM_NAME, "Android");
        desiredCapabilities.setCapability(MobileCapabilityTypeEx.AUTOMATION_NAME, "uiautomator2");
        desiredCapabilities.setCapability(MobileCapabilityTypeEx.UDID, "NFDIVKMV99999999");
        desiredCapabilities.setCapability(MobileCapabilityTypeEx.APP_PACKAGE, "com.wdiodemoapp");
        desiredCapabilities.setCapability(MobileCapabilityTypeEx.APP_ACTIVITY, "com.wdiodemoapp.MainActivity");
        desiredCapabilities.setCapability("noReset", "false");
        desiredCapabilities.setCapability(MobileCapabilityTypeEx.NEW_COMMAND_TIMEOUT, 120);

        AppiumDriver<MobileElement> appiumDriver = new AndroidDriver<>(appiumService.getUrl(), desiredCapabilities);
        appiumDriver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);

        MobileElement loginLabel = appiumDriver.findElementByAccessibilityId("Login");
        loginLabel.click();

        String killNodeWindowsCmd = "taskkill /F /IM node.exe";
        String killNodeLinuxCmd = "killall node";
        String currentOS = System.getProperty("os.name").toLowerCase(Locale.ROOT);
        String killNodeCmd = currentOS.startsWith("windows") ? killNodeWindowsCmd : killNodeLinuxCmd;
        Runtime runtime = Runtime.getRuntime();
        try{
            runtime.exec(killNodeCmd);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
