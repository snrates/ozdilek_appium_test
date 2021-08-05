import com.thoughtworks.gauge.Step;
import org.junit.Assert;
import org.openqa.selenium.By;

public class StepImplementation extends BaseTest {
    Helper helper=new Helper();
    public StepImplementation() {
    }

    @Step({"<key> id li elemente tıkla"})
    public void clickBYid(String key)  {
        (appiumDriver.findElement(By.id(key))).click();
    }

    @Step({"<key> id li elemente <text>  değerini yaz"})
    public void sendkeyBYid(String key, String text) {
        (appiumDriver.findElement(By.id(key))).sendKeys(new CharSequence[]{text});
    }

    @Step({"<key> xpath li elemente tıkla"})
    public void clickBYxpath(String key) {
        (appiumDriver.findElement(By.xpath(key))).click();
    }

    @Step({"<number> saniye bekle"})
    public void waitForSecond(int number) throws InterruptedException {
        Thread.sleep((long) (number * 1000));
    }

    @Step({"<key> id li element <text> değerini  içerdiğini kontrol et"})
    public void checkElement(String key, String text) {
        Assert.assertTrue((appiumDriver.findElement(By.id(key))).getText().equals(text));
    }

    @Step({"<key> xpath li element <text> değerini  içerdiğini kontrol et"})
    public void checkXpathElement(String key, String text) {
        Assert.assertTrue(( appiumDriver.findElement(By.xpath(key))).getText().equals(text));
    }

    @Step("<key> id li elementin değerini getir")
    public int getProductNumber(String key){
        String toNumber=(appiumDriver.findElement(By.id(key))).getText();
        int number=Integer.parseInt(toNumber);
        return number;
    }

    @Step("aşağı kaydır")
    public void scroll(){
        helper.scrollDown();
    }
//    public void scrollDown() {
//        Dimension dimension = appiumDriver.manage().window().getSize();
//
//        Double scrollHeightStart = dimension.getHeight() * 0.5;
//        int scroll= dimension.width;
//        int scrollStart = scrollHeightStart.intValue();
//
//        Double scrollHeightEnd = dimension.getWidth() * 0.2;
//        int scrollEnd = scrollHeightEnd.intValue();
//        appiumDriver.swipe(scroll,scrollStart,scroll,scrollEnd,1000);
//
//
//    }
}
