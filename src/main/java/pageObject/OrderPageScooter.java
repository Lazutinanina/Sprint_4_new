package pageObject;


import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;


public class OrderPageScooter {
    private WebDriver driver;

    private By orderHeader = By.xpath(".//div[text()='Для кого самокат']");
    private By aboutOrderHeader = By.xpath(".//div[text()='Про аренду']");

    private By acceptCookieButton = By.xpath(".//button[text()='да все привыкли']");

    private By nameField = By.xpath(".//input[@placeholder='* Имя']");

    private By surnameField = By.xpath(".//input[@placeholder='* Фамилия']");

    private By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");

    private By subwayField = By.xpath(".//input[@placeholder='* Станция метро']");

    private By phoneNumberField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");

    private By orderNextButton = By.xpath(".//button[text()='Далее']");


    private By dateField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");

    private By rentalPeriodField = By.xpath(".//div[@class='Dropdown-placeholder']");

    private By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");

    private By orderCreateButton = By.xpath("//div[contains(@class,'Order_Buttons')]/button[text()='Заказать']");

    private By orderConfirmButton = By.xpath(".//button[text()='Да']");

    public By confirmHeader = By.xpath(".//button[text()='Посмотреть статус']");
    /**кнопка заказать в шапке*/
    private By headerOrderButton = By.className("Button_Button__ra12g");;
    /** кнопка Заказать по середине страницы*/
    private By pageOrderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    private By successfulModal = By.xpath(".//div[@class='Order_Modal__YZ-d3']/div[@class='Order_ModalHeader__3FDaJ' and text()='Заказ оформлен']");
    private By rentalPeriod = By.className("Dropdown-placeholder");
    private By rentalPeriodList = By.className("Dropdown-option");
    private By sendButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    public static final String order = "Для кого самокат";
    public static final String confirm = "Посмотреть статус";

    public OrderPageScooter(WebDriver driver){
        this.driver = driver;
    }

    // Геттер для получения текста заголовка страницы заказа
    public String getOrderHeader() {
        return driver.findElement(orderHeader).getText();
    }
    // Метод для принятия куки
    public void acceptCookieButtonClick() {
        driver.findElement(acceptCookieButton).click();
    }
    // Метод для заполнения поля * Имя
    public void setName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }
    // Метод для заполнения поля * Фамилия
    public void setSurname(String surname) {
        driver.findElement(surnameField).sendKeys(surname);
    }
    // Метод для заполнения поля * Адрес: куда привезти заказ
    public void setAddress(String address) {
        driver.findElement(addressField).sendKeys(address);
    }
    // Метод для заполнения поля * Станция метро
    public void setSubway(String subway) {
        driver.findElement(subwayField).click();
        driver.findElement(By.xpath(".//div[text()='" + subway + "']")).click(); // Изменено на String, так как subway это стринг
    }
    // Метод для заполнения поля * Телефон: на него позвонит курьер
    public void setPhoneNumber(String phoneNumber) {
        driver.findElement(phoneNumberField).sendKeys(phoneNumber);
    }
    // Метод для перехода ко второй странице создания заказа
    public void clickOrderNextButton() {
        driver.findElement(orderNextButton).click();
    }
    // Метод для заполнения поля * Когда привезти самокат
    public void setDate(String date) {
        driver.findElement(dateField).sendKeys(date);
    }
    // Метод для заполнения поля Срок аренды
    public void setRentalPeriod(String rentalPeriod) {
        driver.findElement(aboutOrderHeader).click();
        driver.findElement(rentalPeriodField).click();
        driver.findElement(By.xpath(".//div[text()='"+rentalPeriod+"']")).click();
    }
    // Метод для заполнения поля Цвет самоката
    public void setColor(String color) {
        driver.findElement(By.xpath(".//label[text()='"+color+"']")).click();
    }
    // Метод для заполнения поля Комментарий для курьера
    public void setComment(String comment) {
        driver.findElement(commentField).sendKeys(comment);
    }
    // Метод для перехода к подтверждению заказа
    public void clickOrderCreateButton() {
        driver.findElement(orderCreateButton).click();
    }
    // Метод для подтверждения заказа
    public void clickOrderConfirmButton() {
        driver.findElement(orderConfirmButton).click();
    }

    public void clickOrderButtonUp() {
        driver.findElement(headerOrderButton).click();
    }
    public void clickOrderButtonDown() throws InterruptedException {
        WebElement element = driver.findElement(pageOrderButton);
        scrollToElement(element);
        element.click();
    }
    public void isPageOpen(String headerText, String text) {
        MatcherAssert.assertThat(headerText, is(text));
    }

    public void clickPageOrderButton() {
        // Проскролить до появления кнопки
        WebElement bigButton = driver.findElement(pageOrderButton);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", bigButton);
        driver.findElement(pageOrderButton).click();
    }

    public void clickButton(By element) {
        driver.findElement(element).click();
    }

    public void sendKeys(By element, String key) {
        driver.findElement(element).sendKeys(key);
    }

    public List<WebElement> findAll(By element) {
        return driver.findElements(element);
    }
    public void scrollToElement(WebElement webElement) throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", webElement);
        new WebDriverWait(driver, Duration.ofSeconds(7)).until(ExpectedConditions.visibilityOf(webElement));
    }
    public boolean checkModal() {
        return !driver.findElements(successfulModal).isEmpty();
    }
    // Геттер для получения текста на кнопке для просмотра статуса заказа
    public String getConfirmHeader() {
        return driver.findElement(confirmHeader).getText();
    }
    public void clickHeaderOrderButton() {
        driver.findElement(headerOrderButton).click();
    }


    public void firstPageForm(String firstNameToForm, String lastNameToForm, String addressToForm, String subwayStation, String phoneToForm) throws InterruptedException {
        setName(firstNameToForm);
        setSurname(lastNameToForm);
        setAddress(addressToForm);
        setSubway(subwayStation);
        setPhoneNumber(phoneToForm);
        clickOrderNextButton();
    }


    public void secondPageForm(String dateToForm, String rentalPeriodToForm) throws InterruptedException {
        setDate(dateToForm);
        clickOrderCreateButton();
        setRentalPeriod(rentalPeriodToForm);
        clickOrderCreateButton();
    }
}
