import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.junit.Test;
import org.junit.After;
import pageScooter.HomePageScooter;
import pageScooter.OrderPageScooter;
import java.time.LocalDateTime;
import static org.junit.Assert.assertEquals;

//Тесты на проверку оформления заказа
public class OrderTest {

    private WebDriver driver;
    private HomePageScooter objHomePageScooter;
    private OrderPageScooter objOrderScooter;

    @Before
    public void tearup() {
       // WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        // Открой страницу  стенда
        driver.get("https://qa-scooter.praktikum-services.ru/");
        // объект класса Главной страницы
        objHomePageScooter = new HomePageScooter(driver);
        //Куки- соглашаемся на использование
        objHomePageScooter.clickButtonCook();
    }

    @Test
    public void orderButtonTopClick() {
        //Кнопка "Заказать" вернее меню
        objOrderScooter = objHomePageScooter.clickButtonTop();
        //"Для кого самокат" заполним все поля валидными данными
        objOrderScooter.setValueInWho("Надежда", "Николаева", "Москва", "Черкизовская", "89278888888");
        //Кнопка "Далее"
        objOrderScooter.nextClick();
        //"Про аренду", заказ на завтра
        LocalDateTime tomorrow = LocalDateTime.now().plusDays(1);
        objOrderScooter.setValueInRent(tomorrow, "сутки", false,false, "");
        //Кнопка "Заказать"
        objOrderScooter.orderClick();
        assertEquals("Ожидается окно с сообщением о подтверждении  заказа ",true, objOrderScooter.divOrderQuestionIsEmpty());
        //Подтверждаем оформление заказа
        objOrderScooter.buttonOrderYesClick();
        assertEquals("Ожидается окно с сообщением об успешном создании заказа ",true, objOrderScooter.divOrderFinishIsEmpty());


    }


    @Test
    public void orderButtonBottomClick() {
        //Нижняя кнопка Заказать
        objOrderScooter = objHomePageScooter.clickButtonBottom();
        //"Для кого самокат"
        objOrderScooter.setValueInWho("Дмитрий", "Николаев", "Москва", "Черкизовская", "89278888888");
        //Кнопка "Далее"
        objOrderScooter.nextClick();
        //"Про аренду" заказ на сегодня
        LocalDateTime tomorrow = LocalDateTime.now();
        objOrderScooter.setValueInRent(tomorrow, "двое суток", false,false, "");
        //Кнопка "Заказать"
        objOrderScooter.orderClick();
        assertEquals("Ожидается окно с сообщением о подтверждении  заказа ",true, objOrderScooter.divOrderQuestionIsEmpty());
        //Подтверждаем оформление заказа
        objOrderScooter.buttonOrderYesClick();
        assertEquals("Ожидается окно с сообщением об успешном создании заказа ",true, objOrderScooter.divOrderFinishIsEmpty());

    }


    @Test
    public void orderLogoScooterClick() {
        //Нижняя кнопка "Заказать" перешли на страницу заказа
        objOrderScooter = objHomePageScooter.clickButtonBottom();
        objOrderScooter.logoScooterClick();
        //если нажать на логотип «Самоката», попадёшь на главную страницу «Самоката»
        boolean result = !driver.findElements(By.xpath(".//div[@class='Home_HomePage__ZXKIX']")).isEmpty();
        assertEquals("Ожидается окно главной страницы «Самоката» ",true, result);

    }

    @Test
    public void orderLogoYandexClick() {
        //Нижняя кнопка "Заказать" перешли на страницу заказа
        objOrderScooter = objHomePageScooter.clickButtonBottom();
        //если нажать на логотип Яндекса, в новом окне откроется главная страница Яндекса.
        String result = objOrderScooter.logoYandexHref();
        assertEquals("Ожидается переход на главную страницы «Яндекс» ",result, "//ya.ru");

    }

    @After
    public void teardown() {
        // Закрой браузер
        driver.quit();
    }
}