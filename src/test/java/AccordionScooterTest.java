
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.JavascriptExecutor;
import pageScooter.HomePageScooter;
import static org.junit.Assert.assertEquals;

//Выпадающий список в разделе «Вопросы о важном». Когда нажимаешь на стрелочку, открывается соответствующий текст.
@RunWith(Parameterized.class)
public class AccordionScooterTest {
    //Вопросы о важном - меню
    private int elementIndex;
    //ожидаемый текст
    private String ValueText ;

    public AccordionScooterTest(int elementIndex, String ValueText) {
        this.elementIndex = elementIndex;
        this.ValueText = ValueText;
    }
//By.id("accordion__heading-0"),By.id("accordion__panel-0")
    @Parameterized.Parameters
    public static Object[][] getAccordionText() {
        //Тестовые данные
        return new Object[][] {
                { 0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                { 1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                { 2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                { 3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                { 4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                { 5, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                { 6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                { 7, "Да, обязательно. Всем самокатов! И Москве, и Московской области."}
        };
    }

    @Test
    public void clickAccordionTest() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        WebDriver driver = new ChromeDriver(options);
        // Открой страницу  стенда
        driver.get("https://qa-scooter.praktikum-services.ru/");
        // создай объект класса главной страницы
        HomePageScooter objHomePageScooter = new HomePageScooter(driver);
        //Куки
        objHomePageScooter.clickButtonCook();
        //Нажимаем на строчку
        objHomePageScooter.clickItemAccordionHead(elementIndex);
        //Сравниваем результат
        boolean result = objHomePageScooter.textItemAccordionPanel(elementIndex).equals(ValueText);
        assertEquals("Ожидается текст: "+ValueText,true, result );
        driver.quit();
    }
}