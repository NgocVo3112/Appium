package lesson15;

import Driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SwipeVertically {
    public static void main(String[] args) {
        DriverFactory.startAppiumServer();
        try {
            AppiumDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();
            MobileElement fromLabelElem = androidDriver.findElementByAccessibilityId("Forms");
            fromLabelElem.click();

            WebDriverWait webDriverWait = new WebDriverWait(androidDriver,30L);
            webDriverWait.until(ExpectedConditions.visibilityOf(androidDriver.findElementByAccessibilityId("switch")));

            Dimension windownSize = androidDriver.manage().window().getSize();
            int screenHeight = windownSize.getHeight();
            int screenWidth = windownSize.getWidth();
            int xStartPoint = 50* screenWidth / 100;
            int xEndPoint = xStartPoint;
            int yStartPoint = 90 * screenHeight / 100;
            int yEndPoint = 10 * screenHeight / 100;

            PointOption startPoint = new PointOption().withCoordinates(xStartPoint, yStartPoint);
            PointOption endPoint = new PointOption().withCoordinates(xEndPoint, yEndPoint);

            TouchAction touchAction = new TouchAction(androidDriver);

            // Swipe Up
            touchAction
                    .press(startPoint)
                    .waitAction( new WaitOptions().withDuration(Duration.ofSeconds(1)))
                    .moveTo(endPoint)
                    .release()
                    .perform();

            // Swipe Dowm
            touchAction
                    .press(endPoint)
                    .waitAction( new WaitOptions().withDuration(Duration.ofSeconds(1)))
                    .moveTo(startPoint)
                    .release()
                    .perform();

            // Swipe Up
            touchAction
                    .press(startPoint)
                    .waitAction( new WaitOptions().withDuration(Duration.ofSeconds(1)))
                    .moveTo(endPoint)
                    .release()
                    .perform();

            MobileElement activeBtnElem = androidDriver.findElementByAccessibilityId("button-Active");
            activeBtnElem.click();
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            DriverFactory.stopAppiumServer();
        }
    }
}
