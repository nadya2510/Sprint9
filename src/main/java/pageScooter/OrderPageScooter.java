package pageScooter;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.time.LocalDateTime;



// Класс страницы формы заказа
public class OrderPageScooter extends BasePage{

    //Имя формы "Для кого самокат"
    private By inputName = By.xpath(".//input[@placeholder='* Имя']");
    //Фамилия формы "Для кого самокат"
    private By inputSurname = By.xpath(".//input[@placeholder='* Фамилия']");
    //Адрес формы "Для кого самокат"
    private By inputAdres = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    //Станция метро формы "Для кого самокат"
    private By inputMetro = By.xpath(".//input[@placeholder='* Станция метро']");
    //Телефон формы "Для кого самокат"
    private By inputTelephone = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    //Кнопка "Далее" формы "Для кого самокат"
    private By buttonNext = By.xpath(".//button[text()='Далее']");

    //Когда привезти самокат формы "Про аренду"
    private By inputWhen = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    //Срок аренды формы "Про аренду"
    private By divPeriod = By.xpath("//div[starts-with(@class,'Dropdown-placeholder')]");
    private By spanPeriod = By.xpath(".//span[@class='Dropdown-arrow']");
    //Цвет самоката - черный формы "Про аренду"
    private By inputBlack = By.id("black");
    //Цвет самоката - серый формы "Про аренду"
    private By inputGrey = By.id("grey");
    //Комментарий формы "Про аренду"
    private By inputComment = By.xpath(".//input[@placeholder='Комментарий для курьера']");

    //Кнопка "Заказать" формы "Про аренду"
    private By buttonOrder = By.xpath(".//div[@class='Order_Buttons__1xGrp']//button[text()='Заказать']");
    //Кнопка "Назад" формы "Про аренду"
    private By buttonPrior = By.xpath(".//button[text()='Назад']");

    //Подтверждение оформления заказа- Вопрос
    private By divOrderQuestion = By.xpath(".//div[@class='Order_ModalHeader__3FDaJ']");
    //Подтверждение оформления заказа- кнопка "Да"
    private By buttonOrderYes = By.xpath(".//button[text()='Да']");
    //Подтверждение оформления заказа- кнопка "Нет"
    private By buttonOrderNo = By.xpath(".//button[text()='Нет']");

    //всплывающее окно с сообщением об успешном создании заказа- сообщение
    private By divOrderFinish = By.xpath(".//div[text()='Заказ оформлен']");
    //всплывающее окно с сообщением об успешном создании заказа- кнопка "Посмотреть статус"
    private By buttonOrderFinish = By.xpath(".//button[text()='Посмотреть статус']");

    //логотип самокат
    private By logoScooter = By.xpath(".//img[@alt='Scooter']");
    //логотип Яндекс
    private By logoYandex = By.xpath(".//a[@class='Header_LogoYandex__3TSOI']");


    public OrderPageScooter(WebDriver driver) {
        super(driver);
    }

    // метод для заполнения Имя на форме "Для кого самокат"
    public boolean setValueInName(String newName) {

        driver.findElement(inputName).clear();
        driver.findElement(inputName).sendKeys(newName);
        driver.findElement(inputName).sendKeys(Keys.TAB);
        return !driver.findElement(inputName).getAttribute("class").contains("_Error_");
    }



    // метод для заполнения Фамилия на форме "Для кого самокат"
    public boolean setValueInSurname(String newSurname) {
        driver.findElement(inputSurname).clear();
        driver.findElement(inputSurname).sendKeys(newSurname);
        driver.findElement(inputSurname).sendKeys(Keys.TAB);
        return !driver.findElement(inputSurname).getAttribute("class").contains("_Error_");
    }
    // метод для заполнения Адрес на форме "Для кого самокат"
    public boolean setValueInAdres(String newAdres) {
        driver.findElement(inputAdres).clear();
        driver.findElement(inputAdres).sendKeys(newAdres);
        driver.findElement(inputAdres).sendKeys(Keys.TAB);
        return !driver.findElement(inputAdres).getAttribute("class").contains("_Error_");
    }


    // метод для заполнения Метро на форме "Для кого самокат"
    public void setValueInMetro(String newMetro) {
        By inputMetroValue = By.xpath(".//div[text()='"+newMetro+"']");

        driver.findElement(inputMetro).click();
        driver.findElement(inputMetroValue).click();
    }

    // метод валидное значение Метро на форме "Для кого самокат"
    public boolean isValueInMetro() {
        return !driver.findElements(By.xpath(".//div[@class = 'Order_MetroError__1BtZb']")).isEmpty();
    }

    // метод для заполнения Телефон на форме "Для кого самокат"
    public boolean setValueInTelephone(String newTelephone) {
        driver.findElement(inputTelephone).clear();
        driver.findElement(inputTelephone).sendKeys(newTelephone);
        driver.findElement(inputTelephone).sendKeys(Keys.TAB);
        if (driver.findElement(inputTelephone).getAttribute("class").contains("_Error_")){
            return  false;
        } else {
            return  true;
        }
    }

    // метод для заполнения формы "Для кого самокат"
    public void setValueInWho(String newName, String newSurname, String newAdres, String newMetro, String newTelephone) {
        setValueInMetro(newMetro);
        setValueInName(newName);
        setValueInSurname(newSurname);
        setValueInAdres(newAdres);
        setValueInTelephone(newTelephone);
    }
    // метод для заполнения Когда на форме "Про аренду"
    public void setValueInWhen(LocalDateTime newWhen) {
        int day_value = newWhen.getDayOfMonth();
        String month_value;
        if (newWhen.getMonthValue()<10){
            month_value = '0'+String.valueOf(newWhen.getMonthValue());
        } else {
            month_value = String.valueOf(newWhen.getMonthValue());
        }
        int god_value = newWhen.getYear();

        By valueWhen = By.xpath("//.//div[@aria-label='month  "+god_value+"-"+month_value+"']//div[text()="+day_value+"]");
        driver.findElement(inputWhen).click();
        driver.findElement(valueWhen).click();
    }

    // метод для заполнения Срок аренды на форме "Про аренду"
    public void setValueInPeriod(String newPeriod) {
        By inputPeriodValue = By.xpath(".//div[text()='"+newPeriod+"']");
        driver.findElement(spanPeriod).click();
        driver.findElement(inputPeriodValue).click();

    }

    // метод валидное значение Срок аренды на форме "Про аренду"
    public boolean isValueInPeriod() {
        return !driver.findElements(By.xpath(".//div[contains(@class,'_Error_')]")).isEmpty();
    }

    // метод для заполнения Комментарий на форме "Про аренду"
    public boolean setValueInComment(String newComment) {
        driver.findElement(inputComment).clear();
        driver.findElement(inputComment).sendKeys(newComment);
        driver.findElement(inputComment).sendKeys(Keys.TAB);
        if (driver.findElement(inputComment).getAttribute("class").contains("_Error_")){
            return  false;
        } else {
            return  true;
        }
    }

    // метод для выбора Checkbox
    public void setCheckbox(By elementName) {
        driver.findElement(elementName).click();
    }

    // метод для заполнения формы "Про аренду"
    public void setValueInRent(LocalDateTime newWhen, String newPeriod, boolean newBlack, boolean newGrey, String newComment) {
        setValueInWhen(newWhen);
        setValueInPeriod(newPeriod);
        if (newBlack) {
            setCheckbox(inputBlack);
        };
        if (newGrey) {
            setCheckbox(inputGrey);
        };

        setValueInComment(newComment);
    }

    //метод для нажатия на кнопку "Далее" на форме "Для кого самокат"
    public void nextClick() {
        driver.findElement(buttonNext).click();

    }

    //метод для нажатия на кнопку "Заказать" на форме "Про аренду"
    public void orderClick() {
        driver.findElement(buttonOrder).click();

    }

    //метод для нажатия на кнопку "Назад" на форме "Про аренду"
    public void priorClick() {
         driver.findElement(buttonPrior).click();

    }

    //метод для нажатия на кнопку "Да" окно с сообщением об подтверждение  заказа
    public void buttonOrderYesClick() {
         driver.findElement(buttonOrderYes).click();

    }

    //метод для нажатия на кнопку "Нет" окно с сообщением об подтверждение  заказа
    public void buttonOrderNoClick() {
        driver.findElement(buttonOrderNo).click();

    }

    //метод для нажатия на кнопку "Посмотреть статус" окно с сообщением об успешном создании заказа
    public void buttonOrderFinishClick() {
       driver.findElement(buttonOrderFinish).click();

    }

    //метод для проверки, что появилось всплывающее окно с сообщением об подтверждение  заказа
    public boolean divOrderQuestionIsEmpty() {
        new WebDriverWait(driver,  Duration.ofSeconds(5))
                 .until(ExpectedConditions.visibilityOfElementLocated(divOrderQuestion));
        return driver.findElements(divOrderQuestion).isEmpty();
    }

    //метод для проверки, что появилось всплывающее окно с сообщением об успешном создании заказа
    public boolean divOrderFinishIsEmpty() {
        new WebDriverWait(driver,  Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(divOrderFinish));
        return driver.findElements(divOrderFinish).isEmpty();
    }

    //метод для проверки, что появилось всплывающее окно с сообщением об успешном создании заказа
    public void logoScooterClick() {
        driver.findElement(logoScooter).click();
    }

    //метод для проверки, что появилось всплывающее окно с сообщением об успешном создании заказа
    public String logoYandexHref() {
       return  driver.findElement(logoYandex).getAttribute("href");
    }
}


