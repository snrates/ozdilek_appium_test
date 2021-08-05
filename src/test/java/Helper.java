import org.openqa.selenium.Dimension;

public class Helper extends BaseTest {

    public Helper() {
    }

    public void scrollDown() {

        for (int i = 0; i <27; i++) {
            Dimension dimension = appiumDriver.manage().window().getSize();
            Double scrollHeightStart = dimension.getHeight() * 0.5;
            int scroll = dimension.width/2;
            int scrollStart = scrollHeightStart.intValue();

            Double scrollHeightEnd = dimension.getWidth() * 0.2;
            int scrollEnd = scrollHeightEnd.intValue();
            appiumDriver.swipe(scroll, scrollStart, scroll, scrollEnd, 1100);
        }
    }
}
