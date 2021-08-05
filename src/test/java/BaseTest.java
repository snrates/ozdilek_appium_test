import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Selector;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.FluentWait;

public class BaseTest {
    protected static AppiumDriver<MobileElement> appiumDriver;
    protected static FluentWait<AppiumDriver<MobileElement>> appiumFluentWait;
    protected boolean localAndroid = true;
    protected static Selector selector;

    public BaseTest() {
    }

    @BeforeScenario
    public void beforeScenario() throws MalformedURLException {
        System.out.println("******ANDROID TEST STARTING******");
        if (StringUtils.isEmpty(System.getenv("key"))) {
            DesiredCapabilities desiredCapabilities;
            URL url;
            if (this.localAndroid) {
                desiredCapabilities = new DesiredCapabilities();
                desiredCapabilities.setCapability("platformName", "Android");
                desiredCapabilities.setCapability("deviceName", "emulator-5554");
                desiredCapabilities.setCapability("appPackage", "com.ozdilek.ozdilekteyim");
                desiredCapabilities.setCapability("appActivity", "com.ozdilek.ozdilekteyim.MainActivity");
                desiredCapabilities.setCapability("automationName", "uiautomator2");
                desiredCapabilities.setCapability("newCommandTimeout", 3000);
                url = new URL("http://127.0.0.1:4723/wd/hub");
                appiumDriver = new AndroidDriver(url, desiredCapabilities);
            } else {
                desiredCapabilities = new DesiredCapabilities();
                desiredCapabilities.setCapability("platformName", "iOS");
                desiredCapabilities.setCapability("automationName", "XCUITest");
                desiredCapabilities.setCapability("udid", "lokalinizde bağlı olan telefonun udid bilgisini gir");
                desiredCapabilities.setCapability("bundleId", "bundle id bilgisini gir");
                desiredCapabilities.setCapability("deviceName", "lokaldeki telefonun ismini gir");
                desiredCapabilities.setCapability("platformVersion", "lokaldeki telefon version bilgisini gir");
                desiredCapabilities.setCapability("newCommandTimeout", 300);
                url = new URL("http://127.0.0.1:4723/wd/hub");
                appiumDriver = new IOSDriver(url, desiredCapabilities);
            }
        } else {
            String hubURL = "http://hub.testinium.io/wd/hub";
            DesiredCapabilities capabilities = new DesiredCapabilities();
            System.out.println("key:" + System.getenv("key"));
            System.out.println("platform" + System.getenv("platform"));
            System.out.println("version" + System.getenv("version"));
            if (System.getenv("platform").equals("ANDROID")) {
                capabilities.setCapability("key", System.getenv("key"));
                capabilities.setCapability("appPackage", "com.ozdilek.ozdilekteyimy");
                capabilities.setCapability("appActivity", "com.ozdilek.ozdilekteyim.MainActivity");
                capabilities.setCapability("automationName", "uiautomator2");
                capabilities.setCapability("noReset", true);
                capabilities.setCapability("fullReset", false);
                capabilities.setCapability("unicodeKeyboard", true);
                capabilities.setCapability("resetKeyboard", true);
                appiumDriver = new AndroidDriver(new URL(hubURL), capabilities);
                this.localAndroid = true;
            } else {
                this.localAndroid = false;
                System.out.println("******IOS TEST STARTING******");
                capabilities.setCapability("usePrebuiltWDA", true);
                capabilities.setCapability("key", System.getenv("key"));
                capabilities.setCapability("waitForAppScript", "$.delay(1000);");
                capabilities.setCapability("bundleId", "com.turkishairlines.mobile.ios");
                capabilities.setCapability("usePrebuiltWDA", true);
                capabilities.setCapability("useNewWDA", false);
                capabilities.setCapability("autoAcceptAlerts", true);
                appiumDriver = new IOSDriver(new URL(hubURL), capabilities);
            }
        }

    }

    @AfterScenario
    public void afterScenario() {
        if (appiumDriver != null) {
            appiumDriver.quit();
        }

    }
}
