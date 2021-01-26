import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class DeleteSentEmails {
    private WebDriver driver;
    private String deleteToolTip = "Цепочка помещена в корзину.";

    public DeleteSentEmails(WebDriver driver) {
        this.driver = driver;
    }

    public void deleteMessages() throws InterruptedException {
        WebElement sentMessages = this.driver.findElement(By.cssSelector("a[href='https://mail.google.com/mail/u/0/#sent']"));
        sentMessages.click();
        WebElement countOfEmails = this.driver.findElement(By.cssSelector("div[class='J-J5-Ji amH J-JN-I'] > span > span.ts"));
        int emailsCount = Integer.parseInt(countOfEmails.getText());
        System.out.println(emailsCount);
        WebElement moreButton = this.driver.findElement(By.cssSelector("span[class='CJ']"));
        moreButton.click();

        for (int i = 0; i < emailsCount; i++) {
            WebElement firstMessage = this.driver.findElement(By.cssSelector("table[role='grid'] tbody > :first-child"));
            WebElement deletedEmails = this.driver.findElement(By.cssSelector("a[href='https://mail.google.com/mail/u/0/#trash']"));
            new Actions(driver).dragAndDrop(firstMessage, deletedEmails).release().perform();
            WebElement confirmationToolTip = this.driver.findElement(By.cssSelector("span .bAq"));
            Assert.assertEquals(confirmationToolTip.getText(), deleteToolTip);
            Thread.sleep(1000);
        }
    }
}