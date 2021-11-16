package lesson16;

import Driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class HandleHybribContext {
    public static void main(String[] args) {
        DriverFactory.startAppiumServer();
        try {
            AppiumDriver<MobileElement> appiumDriver = DriverFactory.getAndroidDriver();
            MobileElement webViewLabel = appiumDriver.findElementByXPath("//android.widget.Button[@content-desc=\"Webview\"]");
            webViewLabel.click();
            appiumDriver.getContextHandles().forEach(context ->{
                System.out.println(context);
            });

            // Switch to webview
            appiumDriver.context("WEBVIEW_com.wdiodemoapp");
            WebElement navTogglebtnElem = appiumDriver.findElementByCssSelector(".navbar__toggle");
            navTogglebtnElem.click();

            List<MobileElement> menuItems = appiumDriver.findElementsByCssSelector(".menu__list-item a");
            List<MenuItem> menuItemList = new ArrayList<>();
            for (MobileElement menuItem : menuItems) {
                String menuText = menuItem.getText();
                String menuHyperLink = menuItem.getAttribute("href");
                if(StringUtils.isEmpty(menuText))
                    menuItemList.add(new MenuItem("GitHub", menuHyperLink));
                else
                menuItemList.add(new MenuItem(menuText, menuHyperLink));
            }
            menuItemList.forEach(System.out::println);

            // Switch to native
            appiumDriver.context("NATIVE_APP");
            MobileElement loginLabel = appiumDriver.findElementByAccessibilityId("Login");
            loginLabel.click();


        }catch (Exception e){
            e.printStackTrace();
        } finally {
            DriverFactory.stopAppiumServer();
        }
    }
    public static class MenuItem {
        private String text;
        private String hyperLink;

        public MenuItem(String text, String hyperLink) {
            this.text = text;
            this.hyperLink = hyperLink;
        }

        public String Text() {
            return text;
        }

        public String HyperLink() {
            return hyperLink;
        }

        @Override
        public String toString() {
            return "MenuItem{" +
                    "text='" + text + '\'' +
                    ", hyperLink='" + hyperLink + '\'' +
                    '}';
        }
    }
}
