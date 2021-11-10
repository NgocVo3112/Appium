package lesson14;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverFactoryTest {
    public static void main(String[] args) {
        DriverFactory.startAppiumServer();

        try{
            AndroidDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();
            MobileElement loginLabel = androidDriver.findElementByAccessibilityId("Login");
            loginLabel.click();
            MobileElement EmailInputElem = androidDriver.findElementByXPath("//android.widget.EditText[@content-desc='input-email']");
            MobileElement PasswordInputElem = androidDriver.findElementByXPath("//android.widget.EditText[@content-desc='input-password']");
            MobileElement loginBtn = androidDriver.findElementByAccessibilityId("button-LOGIN");

            EmailInputElem.sendKeys("Teo@sth.com");
            PasswordInputElem.sendKeys("12345678");
            loginBtn.click();

            MobileElement loginFeatureDescElem = androidDriver.findElementByXPath("//*[contains(@text,'When the device')]");
            MobileElement loginFeatureDescElemUiSel = androidDriver.findElementByAndroidUIAutomator ("new UiSelector().textContains(\"When the device\").className(\"android.widget.TextView\")");
            System.out.println(loginFeatureDescElemUiSel.getText());

            WebDriverWait wait = new WebDriverWait(androidDriver,45);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("android:id/alertTitle")));
            MobileElement loginResultDialogElem = androidDriver.findElementById("android:id/alertTitle");
            System.out.println(loginResultDialogElem.getText());

        }catch (Exception ignored){

        }finally {
            DriverFactory.stopAppiumServer();
        }

    }
}
