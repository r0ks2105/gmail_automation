package PageObjects;

import PageObjects.GmailMainPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DeleteSentEmails {
    private WebDriver driver;
    @FindBy(xpath = "// div[2]/div[1]/div[2]/div[1]/span/div[1]/span/span[2]")
    WebElement countOfEmails;
    @FindBy(xpath = "//div[2]/div/table/tbody/tr[1]")
    WebElement firstMessage;
    @FindBy(css = "span .bAq")
    public
    WebElement confirmationToolTip;
    @FindBy(css = "a[href='https://mail.google.com/mail/u/0/#trash']")
    WebElement deletedEmails;
    public String deleteToolTip = "Conversation moved to Trash.";

    public DeleteSentEmails(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public GmailMainPage deleteMessages() throws InterruptedException {
        return deleteAll();
    }
@Step
public GmailMainPage deleteAll() throws InterruptedException {
        new Actions(driver).dragAndDrop(firstMessage, deletedEmails).release().perform();
        Thread.sleep(1000);
        return new GmailMainPage(this.driver);
    }
@Step
    public int countOfSentEmails() {
        int emailsCount = Integer.parseInt(countOfEmails.getText());
        return emailsCount;
    }
}