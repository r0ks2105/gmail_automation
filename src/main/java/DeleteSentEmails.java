import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DeleteSentEmails {
    private WebDriver driver;
    @FindBy(xpath = "// div[2]/div[1]/div[2]/div[1]/span/div[1]/span/span[2]")
    WebElement countOfEmails;
    @FindBy(css = "div[class='ae4 UI'] tbody > :first-child")
    WebElement firstMessage;
    @FindBy(css = "span .bAq")
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

    protected GmailMainPage deleteAll() throws InterruptedException {
        new Actions(driver).dragAndDrop(firstMessage, deletedEmails).release().perform();
        Thread.sleep(1000);
        return new GmailMainPage(this.driver);
    }

    public int countOfSentEmails() {
        int emailsCount = Integer.parseInt(countOfEmails.getText());
        return emailsCount;
    }
}