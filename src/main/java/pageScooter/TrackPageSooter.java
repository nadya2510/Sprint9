package pageScooter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TrackPageSooter extends BasePage{

    private By imgNotFoundStatus = By.xpath(".//img[@alt = 'Not found' ]");

    public TrackPageSooter(WebDriver driver) {
      super(driver);
    }
    public boolean visibleImgNotFoundStatus(){
        new WebDriverWait(driver,  Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(imgNotFoundStatus));
        return driver.findElement(imgNotFoundStatus).isDisplayed();
    }
}
