package pageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class FAQ {
    private WebDriver driver;
    public FAQ(WebDriver driver){
        this.driver = driver;
    }



    public void scrollToElement(By by) throws InterruptedException {
        WebElement element = driver.findElement(by);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        new WebDriverWait(driver, Duration.ofSeconds(7)).until(ExpectedConditions.visibilityOf(element));
    }
    public void click(By element) {
        driver.findElement(element).click();
    }

    public String getText(By element) {
        return driver.findElement(element).getText();
    }

    public boolean getAnswerOnQuestion(String questionText , String answerText) throws InterruptedException {
        String xPathQuestion = ".//div[text()='"+ questionText +"']";
        scrollToElement(By.xpath(xPathQuestion));
        click(By.xpath(xPathQuestion));
        String xPathAnswer = xPathQuestion + "/../../div[@class='accordion__panel']/p";
        scrollToElement(By.xpath(xPathAnswer));
        return getText(By.xpath(xPathAnswer)).equals(answerText);
    }

}
