package lesson17;

import Driver.DriverFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

import java.time.Duration;

public class HandleMulltipleApp {
    public static void main(String[] args) {
        DriverFactory.startAppiumServer();
        try {
            AndroidDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();
            MobileElement loginLabel = androidDriver.findElementByAccessibilityId("Login");
            loginLabel.click();
            MobileElement EmailInputElem = androidDriver.findElementByXPath("//android.widget.EditText[@content-desc='input-email']");
            MobileElement PasswordInputElem = androidDriver.findElementByXPath("//android.widget.EditText[@content-desc='input-password']");
            MobileElement loginBtn = androidDriver.findElementByAccessibilityId("button-LOGIN");

            EmailInputElem.sendKeys("Teo@sth.com");
            PasswordInputElem.sendKeys("12345678");
            loginBtn.click();

            // Put app into background
            androidDriver.runAppInBackground(Duration.ofSeconds(-1));

            // Open settings app com.android.settings/com.android.settings.Settings
            androidDriver.activateApp("com.android.settings");

            // Do sth
            androidDriver.findElementByXPath("//*[@text='Wi-Fi']").click();
            MobileElement wifiSwitchBtnElem = androidDriver.findElementById("android:id/checkbox");
            MobileElement scanTextElem  = androidDriver.findElementByXPath("//*[@text='Scan']");
            boolean isWifiON = scanTextElem.isDisplayed();
            if(isWifiON){
                // Change to OFF
                wifiSwitchBtnElem.click();
                // Change to ON again
                wifiSwitchBtnElem.click();
            }
            else wifiSwitchBtnElem.click();



            // Reload webdriverIO app
            androidDriver.activateApp("com.wdiodemoapp");



        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DriverFactory.stopAppiumServer();
        }
    }
}

