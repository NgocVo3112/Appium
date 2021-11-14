package lesson15;

import Driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SwipeUntil {
    public static void main(String[] args) {
        DriverFactory.startAppiumServer();
        try {
            AppiumDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();
            MobileElement fromLabelElem = androidDriver.findElementByAccessibilityId("Swipe");
            fromLabelElem.click();

            WebDriverWait webDriverWait = new WebDriverWait(androidDriver,30L);
            webDriverWait.until(ExpectedConditions.visibilityOf(androidDriver.findElementByXPath("//*[@text='Swipe horizontal']")));

        // Get mobile window size
            Dimension windownSize = androidDriver.manage().window().getSize();
            int screenHeight = windownSize.getHeight();
            int screenWidth = windownSize.getWidth();

            int xStartPoint = 50* screenWidth / 100;
            int xEndPoint = 10* screenWidth / 100;
            int yStartPoint = 50 * screenHeight / 100;
            int yEndPoint = yStartPoint;

            PointOption startPoint = new PointOption().withCoordinates(xStartPoint, yStartPoint);
            PointOption endPoint = new PointOption().withCoordinates(xEndPoint, yEndPoint);

            TouchAction touchAction = new TouchAction(androidDriver);
            int MAX_SWIPE_TIME = 5;
            int swipeTime = 0;

            while( swipeTime < MAX_SWIPE_TIME){
                List<MobileElement> matchedCard = androidDriver.findElementsByXPath("//*[@text='EXTENDABLE']");
                if (!matchedCard.isEmpty()) break;
                touchAction
                           .press(startPoint)
                            .waitAction( new WaitOptions().withDuration(Duration.ofSeconds(1)))
                            .moveTo(endPoint)
                            .release()
                            .perform();
                swipeTime ++;
            }

        }catch (Exception e){
            e.printStackTrace();
        } finally {
            DriverFactory.stopAppiumServer();
        }
    }
}
