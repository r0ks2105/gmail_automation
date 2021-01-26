import com.beust.jcommander.Parameter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class SendMessage {
    private WebDriver driver;
    public SendMessage(WebDriver driver){
        this.driver = driver;
    }
    public void sendTestEmail(String recipient, String subject, String text, String confirmTitle) throws InterruptedException {
        WebElement newMail = this.driver.findElement(By.cssSelector(".z0 div[role='button']"));
        newMail.click();
        WebElement sendTo = this.driver.findElement(By.cssSelector("textarea[name='to']"));
        sendTo.sendKeys(recipient);
        WebElement subjectBox = this.driver.findElement(By.cssSelector("input[name='subjectbox']"));
        subjectBox.sendKeys(subject);
        WebElement textBox = this.driver.findElement(By.cssSelector("div[class='Am Al editable LW-avf tS-tW']"));
        textBox.sendKeys(text);
        WebElement sendButton = this.driver.findElement(By.cssSelector("div[class='T-I J-J5-Ji aoO v7 T-I-atl L3']"));
        sendButton.click();
        Thread.sleep(2000);
        WebElement confirmationToolTip = this.driver.findElement(By.cssSelector("span .bAq"));
        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(confirmationToolTip, "Отправка…")));
        WebElement confirmationToolTip2 = this.driver.findElement(By.cssSelector("span .bAq"));
        Assert.assertEquals(confirmationToolTip2.getText(), confirmTitle);
    }
}
