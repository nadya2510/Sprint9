package pageScooter;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

//Класс главной страницы
public class HomePageScooter extends BasePage{

    //Кнопка на согласие использования куки
    private By buttonCook = By.id("rcc-confirm-button");
    //Кнопка "Заказать" вверху страницы
    private By buttonTop = By.xpath(".//button[@class='Button_Button__ra12g']");
    //Кнопка "Заказать" внизу страницы
    private By buttonBottom =  By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    //логотип самокат
    private By logoScooter = By.xpath(".//img[@alt='Scooter']");
    //главная страниц Самоката
    private By homePage = By.xpath(".//div[@class='Home_HomePage__ZXKIX']");
    //логотип Яндекс <a target="_blank" rel="noopener noreferrer" href="//yandex.ru" class="Header_LogoYandex__3TSOI"><img src="/assets/ya.svg" alt="Yandex"></a>
    private By logoYandex = By.xpath(".//a[@class='Header_LogoYandex__3TSOI']");
    //Кнопка "Статус заказа"
    private By buttonStatus =  By.xpath(".//button[@class='Header_Link__1TAG7']");
    //Поле "Введите номер заказа"
    private By inputStatus = By.xpath(".//input[@class='Input_Input__1iN_Z Header_Input__xIoUq']");
    //Кнопка "Go!"
    private By buttonGo = By.xpath(".//button[@class='Button_Button__ra12g Header_Button__28dPO']");

    private String itemAccordionHead = "accordion__heading-%d";
    private String itemAccordionPanel = "accordion__panel-%d";



    public HomePageScooter(WebDriver driver) {
        super(driver);
    }

    // метод для нажатия на кнопку "Заказать" вверху страницы
    public OrderPageScooter clickButtonTop() {
        driver.findElement(buttonTop).click();
        return new OrderPageScooter(driver);
    }

    // метод для нажатия на кнопку "Заказать" вверху страницы
    public OrderPageScooter clickButtonBottom() {
        driver.findElement(buttonBottom).click();
        return new OrderPageScooter(driver);
    }

    // метод для нажатия на кнопку согласие на использования куки
    public void clickButtonCook() {
        if (driver.findElement(buttonCook).isDisplayed()){
            driver.findElement(buttonCook).click();
        }
    }

    //нажать на логотип «Самоката»
    public void logoScooterClick() {
        driver.findElement(logoScooter).click();
    }

    public  boolean checkHomePage(){
        return !driver.findElements(homePage).isEmpty();
    }

    //мнажать на логотип Яндекса
    public String logoYandexHref() {
        return driver.findElement(logoYandex).getAttribute("href");
    }

    // метод для нажатия на кнопку "Статус заказа"
    public void clickButtonStatus() {
        driver.findElement(buttonStatus).click();
    }
    //заполняем поле "Введите номер заказа"
    public void enterValueInputStatus(String newStatus) {
        new WebDriverWait(driver,  Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(inputStatus));
        driver.findElement(inputStatus).clear();
        driver.findElement(inputStatus).sendKeys(newStatus);
    }

    // метод для нажатия на кнопку "Go!"
    public TrackPageSooter clickButtonGo() {
        driver.findElement(buttonGo).click();
        return new TrackPageSooter(driver);
    }


    public void clickItemAccordionHead(int itemIndex) {
        WebElement AccordionHead = driver.findElement(By.id(String.format(itemAccordionHead, itemIndex)));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", AccordionHead);
        AccordionHead.click();
    }

    public String textItemAccordionPanel(int itemIndex) {
        WebElement AccordionPane = driver.findElement(By.id(String.format(itemAccordionPanel, itemIndex)));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", AccordionPane);
        return AccordionPane.findElement(By.tagName("p")).getText();
    }

}
