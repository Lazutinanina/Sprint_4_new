package pageObject;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


@RunWith(Parameterized.class)
public class OrderTest extends SettingForTests{
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
    public void sendOrderChromeUpbutton() throws InterruptedException {  //TODO Найден баг при отправке формы в Хроме
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
    public void sendOrderChromeDownbutton() throws InterruptedException { //TODO Найден баг при отправке формы в Хроме
        // Создать объект класса с домашней страницей
        OrderPageScooter objHomePage = new OrderPageScooter(driver);
        // Нажать на кнопку Заказать в шапке
        objHomePage.clickOrderButtonDown();
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
    }
