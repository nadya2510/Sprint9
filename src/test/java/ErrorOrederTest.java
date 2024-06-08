
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageScooter.HomePageScooter;
import pageScooter.OrderPageScooter;
import java.time.LocalDateTime;
import static org.junit.Assert.assertEquals;

//Проверить ошибки для всех полей формы заказа.
@RunWith(Parameterized.class)
public class ErrorOrederTest {
        private  int numberElement;
        //1-Имя Только русские буквы, пробел, тире. Длина не менее 2 и не более 15 символов.
        //2-Фамилия Только русские буквы. Длина не менее 2 и не более 15 символов.
        //3-Адрес Только русские буквы, цифры, пробел, тире, точка, запятая. Длина не менее 5 и не более 50 символов
        //4-Станцция не пустое
        //5-Телефон Только цифры и знак +. Длина не менее 10 и не более 12 символов.
        //6-Когда Выбрать дату можно только с завтрашнего дня. На календаре открывается текущий месяц.
        //7-Срок не пустое
        //8-Комментарий Только русские буквы, цифры, пробел, тире, точка, запятая. Длина не более 24 символов.
        private  String ValueText;


        public ErrorOrederTest(int numberElement, String ValueText) {
            this.numberElement = numberElement;
            this.ValueText = ValueText;
        }

        @Parameterized.Parameters
        public static Object[][] getErrorOrederTest() {
            //Тестовые данные
            return new Object[][]{
                    {1, "Н"},
                    {1, "НадеждаНадеждаНа"},
                    {1, "НадеждаНадеждаНад"},
                    {1, "НадеждаНадеждаНадеждаНадежда"},
                    {1, "Nadya"},
                    {1, "Надежда25"},
                    {1, "Надя - Надя"},
                    {1, "Надя-"},
                    {1, "-Надя"},
                    {1, "Надежда."},
                    {1, "Надежда,"},
                    {1, "Надежда@"},
                    {1, ""},
                    {2, "Н"},
                    {2, "НадеждаНадеждаНа"},
                    {2, "НадеждаНадеждаНад"},
                    {2, "НадеждаНадеждаНадеждаНадежда"},
                    {2, "Nadya"},
                    {2, "Надежда25"},
                    {2, "Надежда- Надежда"},
                    {2, "Надежда."},
                    {2, "Надежда,"},
                    {2, "Надежда@"},
                    {2, " Надежда"},
                    {2, "Наде жда"},
                    {2, "Надежда "},
                    {2, ""},
                    {3, "М"},
                    {3, "Мо"},
                    {3, "Мос"},
                    {3, "Моск"},
                    {3, "МоскваМоскваМоскваМоскваМо"},
                    {3, "МоскваМоскваМоскваМоскваМос"},
                    {3, "МоскваМоскваМоскваМоскваМоскваМосква"},
                    {3, "Moscow"},
                    {3, "Москва@"},
                    {3, ""},
                    {4, ""},
                    {5, "+"},
                    {5, "+7865"},
                    {5, "+7865432"},
                    {5, "+78654321"},
                    {5, "+78945612378945612378945"},
                    {5, "+786543212345"},
                    {5, "+7865432123456"},
                    {5, "мойтелефон"},
                    {5, "mytelephone"},
                    {5, "77654321231"},
                    {5, "+776-432123"},
                    {5, "+7876.43212"},
                    {5, "+776,432123"},
                    {5, "+7876@43212"},
                    {5, " +787654321"},
                    {5, "+7876 43212"},
                    {5, "+787654321 "},
                    {6, ""},
                    {7, ""},
                    {8, "ВеснаВеснаВеснаВеснаВесна"},
                    {8, "ВеснаВеснаВеснаВеснаВеснаВ"},
                    {8, "ВеснаВеснаВеснаВеснаВеснаВеснаВесна"},
                    {8, "Spring"}

            };
        }

        @Test
        public void setValueErrorNameTest() {
            OrderPageScooter objOrderScooter;
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
            WebDriver driver = new ChromeDriver(options);
            // Открой страницу  стенда
            driver.get("https://qa-scooter.praktikum-services.ru/");

            // создай объект класса главной страницы
            HomePageScooter objHomePageScooter = new HomePageScooter(driver);
            //Куки
            objHomePageScooter.clickButtonCook();
            //"Заказать" вернее меню
            objOrderScooter = objHomePageScooter.clickButtonTop();

            boolean result;
            if (numberElement > 5){
                //Для второй страницы заполним первую страницу валидными значениями + кнопка "Далее"
                objOrderScooter.setValueInWho("Надежда", "Николаева", "Москва", "Черкизовская", "89278888888");
                objOrderScooter.nextClick();
            }
            if (numberElement == 1) {
                result = objOrderScooter.setValueInName(ValueText);
                assertEquals("Ожидается ошибка ввода поля *Имя: " + ValueText, false, result);
            }
            if (numberElement == 2) {
                result = objOrderScooter.setValueInSurname(ValueText);
                assertEquals("Ожидается ошибка ввода поля *Фамилия: " + ValueText, false, result);
            }
            if (numberElement == 3) {
                result = objOrderScooter.setValueInAdres(ValueText);
                assertEquals("Ожидается ошибка ввода поля *Адрес: " + ValueText, false, result);
            }
            if (numberElement == 4) {
                objOrderScooter.setValueInName("Надежда");
                objOrderScooter.setValueInSurname("Николаева");
                objOrderScooter.setValueInAdres("Москва");
                objOrderScooter.setValueInTelephone("89278888888");
                objOrderScooter.nextClick();
                result = objOrderScooter.isValueInMetro();
                assertEquals("Ожидается ошибка ввода поля *Станция: " + ValueText, false, result);
            }
            if (numberElement == 5) {
                result = objOrderScooter.setValueInTelephone(ValueText);
                assertEquals("Ожидается ошибка ввода поля *Телефон: " + ValueText, false, result);
            }
            if (numberElement == 6) {

                objOrderScooter.setValueInPeriod("двое суток");
                objOrderScooter.orderClick();
                result = objOrderScooter.isValueInPeriod();
                assertEquals("Ожидается ошибка ввода поля *Срок: ", false, result);
            }
            if (numberElement == 7) {
                LocalDateTime tomorrow = LocalDateTime.now();
                objOrderScooter.setValueInWhen(tomorrow);
                objOrderScooter.orderClick();
                result = objOrderScooter.isValueInPeriod();
                assertEquals("Ожидается ошибка ввода поля *Когда: ", false, result);
            }
            if (numberElement == 8) {
                result = objOrderScooter.setValueInComment(ValueText);
                assertEquals("Ожидается ошибка ввода поля Комментарий: " + ValueText, false, result);
            }

            driver.quit();
        }
    }

