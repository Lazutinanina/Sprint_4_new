package pageObject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertTrue;


@RunWith(Parameterized.class)
public class OrderTest extends SettingForTests{
    private WebDriver driver;
    private final String name;
    private final String surname;
    private final String address;
    private final String subway;
    private final String phoneNumber;
    private final String date;
    private final String rentalPeriod;
    private final String color;
    private final String comment;


    public OrderTest(String name, String surname, String address, String subway, String phoneNumber, String date, String rentalPeriod, String color, String comment) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.subway = subway;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.rentalPeriod = rentalPeriod;
        this.color = color;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getDateSetForOrder() {
        return new Object[][] {
                {"Нина", "Лазутина", "пр. Энергетиков, д.54", "Владыкино", "89177214587", "01.01.2040", "сутки", "чёрный жемчуг", "Ужас"},
                {"Татьяна", "Ларкина", "проспект Маяковского 6", "Маяковская", "+79052255961", "10.10.2039", "четверо суток", "серая безысходность", "Тлен"},
        };
    }

    @Test
    public void OrderPositiveTest() throws InterruptedException {
        // Создать веб-драйвер для Firefox
        driver = new FirefoxDriver();
        // Открыть страницу заказа Яндекс Самокат
        driver.get("https://qa-scooter.praktikum-services.ru");
        // Создать объект класса с домашней страницей
        OrderPageScooter objHomePage = new OrderPageScooter(driver);
        // Нажать на кнопку Заказать в шапке
         objHomePage.clickHeaderOrderButton();
        // Создать объект класса со страницей заказа
        OrderPageScooter objOrderPage = new OrderPageScooter(driver);
        // Принять куки
        objOrderPage.acceptCookieButtonClick();
        objOrderPage.setName(name);
        objOrderPage.setSurname(surname);
        objOrderPage.setAddress(address);
        objOrderPage.setSubway(subway);
        objOrderPage.setPhoneNumber(phoneNumber);
        objOrderPage.clickOrderNextButton();
        objOrderPage.setDate(date);
        objOrderPage.setRentalPeriod(rentalPeriod);
        objOrderPage.setColor(color);
        objOrderPage.setComment(comment);
        objOrderPage.clickOrderCreateButton();
        objOrderPage.clickOrderConfirmButton();
        // Проверить, что открылась страница успешного создания заказа
        assertTrue("Ошибка: страница успешного создания заказа не открылась", objOrderPage.checkModal());
    }

        @Test
        public void OrderPageOpenHeaderButtonClick() {
            // Создать веб-драйвер для Google Chrome
            driver = new ChromeDriver();
            // Открыть страницу Яндекс Самокат
            driver.get("https://qa-scooter.praktikum-services.ru");
            // Создать объект класса с домашней страницей
            OrderPageScooter objHomePage = new OrderPageScooter(driver);
            objHomePage.clickHeaderOrderButton();
            OrderPageScooter objOrderPage = new OrderPageScooter(driver);
            // Проверить, что открылась страница заказа
            objOrderPage.isPageOpen(objOrderPage.getOrderHeader() ,OrderPageScooter.order);
        }

        @Test
        public void OrderPageOpenAfterPageButtonClick() {
            // Создать веб-драйвер для Google Chrome
            driver = new ChromeDriver();
            // Открыть страницу Яндекс Самокат
            driver.get("https://qa-scooter.praktikum-services.ru");
            // Создать объект класса с домашней страницей
            OrderPageScooter objHomePage = new OrderPageScooter(driver);
            objHomePage.clickPageOrderButton();
            OrderPageScooter objOrderPage = new OrderPageScooter(driver);
            // Проверить, что открылась страница заказа
            objOrderPage.isPageOpen(objOrderPage.getOrderHeader() ,OrderPageScooter.order);
        }

        @Test
        public void sendOrderChromeUpbutton() throws InterruptedException {  //TODO Найден баг при отправке формы в Хроме
            OrderPageScooter orderPage = new OrderPageScooter(driver);
            orderPage.clickOrderButtonUp();
            orderPage.firstPageForm(name, surname, address, subway, phoneNumber);
            orderPage.secondPageForm(date, rentalPeriod);
            assertTrue("Ошибка оформления заказа", orderPage.checkModal());
        }

        @Test
        public void sendOrderChromeDownbutton() throws InterruptedException { //TODO Найден баг при отправке формы в Хроме по середине
            OrderPageScooter orderPage = new OrderPageScooter(driver);
            orderPage.clickOrderButtonDown();
            orderPage.firstPageForm(name, surname, address, subway, phoneNumber);
            orderPage.secondPageForm(date, rentalPeriod);
            assertTrue("Ошибка оформления заказа", orderPage.checkModal());
        }

    }
