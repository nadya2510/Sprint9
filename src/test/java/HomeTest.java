
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.junit.Test;
import org.junit.After;
import pageScooter.HomePageScooter;
import static org.junit.Assert.assertEquals;

//Тесты на проверку Главной страницы
public class HomeTest {
    private WebDriver driver;
    private HomePageScooter objHomePageScooter;

    @Before
    public void tearup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        // Открой страницу  стенда
        driver.get("https://qa-scooter.praktikum-services.ru/");
        // создай объект класса Главной страницы
        objHomePageScooter = new HomePageScooter(driver);
        //Куки- соглашаемся на использование
        objHomePageScooter.clickButtonCook();
    }
    @Test
    public void homeLogoScooterClick() {
        //если нажать на логотип «Самоката», попадёшь на главную страницу «Самоката»
        objHomePageScooter.logoScooterClick();
        boolean result = objHomePageScooter.checkHomePage();
        assertEquals("Ожидается окно главной страницы «Самоката» ",true, result);
    }

    @Test
    public void homeLogoYandexClick() {
        //если нажать на логотип "Яндекса", в новом окне откроется главная страница "Яндекса".
        String result = objHomePageScooter.logoYandexHref();
        assertEquals("Ожидается окно главной страницы «Яндекс» ",result, "//ya.ru");
    }

    @After
    public void teardown() {
        // Закрой браузер
        driver.quit();
    }
}
