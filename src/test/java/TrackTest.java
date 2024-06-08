//Тесты на проверку статуса заказа
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.junit.Test;
import org.junit.After;
import pageScooter.HomePageScooter;
import pageScooter.TrackPageSooter;
import static org.junit.Assert.assertEquals;

public class TrackTest {
     private WebDriver driver;
     private TrackPageSooter objTrackScooter;

     @Test
     public void trackСlickButtonStatus() {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
            driver = new ChromeDriver(options);
            // Открой страницу  стенда
            driver.get("https://qa-scooter.praktikum-services.ru/");

            // создай объект класса Главной страницы
            HomePageScooter objHomePageScooter = new HomePageScooter(driver);
            //Куки- соглашаемся на использование
            objHomePageScooter.clickButtonCook();
            // ввести неправильный номер заказа
            objHomePageScooter.clickButtonStatus();
            objHomePageScooter.enterValueInputStatus("8888");
            objTrackScooter = objHomePageScooter.clickButtonGo();
            //Страница статус заказа
            boolean result = objTrackScooter.visibleImgNotFoundStatus();
            assertEquals("Ожидается сообщение 'Такого заказа нет' ",true, result);

     }

    @After
    public void teardown() {
        // Закрой браузер
        driver.quit();
    }
}
