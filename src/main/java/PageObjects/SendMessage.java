package PageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SendMessage {
    private WebDriver driver;
    @FindBy(css = "textarea[name='to']")
    WebElement sendTo;
    @FindBy(css = "input[name='subjectbox']")
    WebElement subjectBox;
    @FindBy(css = "div[class='Am Al editable LW-avf tS-tW']")
    WebElement textBox;
    @FindBy(css = "div[class='T-I J-J5-Ji aoO v7 T-I-atl L3']")
    WebElement sendButton;
    @FindBy(css = "div[class='Kj-JD-Jl']>button")
    WebElement noRecipientOk;
    @FindBy(css = "div[class='aoD hl']")
    WebElement recipients;
    @FindBy(css = "div[class='nH Hd']")
    WebElement sendButton2;
    @FindBy(css = "div[class='Kj-JD']")
    WebElement errorPopUp;

    public SendMessage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public GmailMainPage send(String recipient, String subject, String text) throws Exception {
        return typeRecipientEmail(recipient).typeSubject(subject).typeMessageText(text).sendMessage();
    }
@Step
    protected SendMessage typeRecipientEmail(String recipient) {
        sendTo.sendKeys(recipient);
        return this;
    }
@Step
    protected SendMessage typeSubject(String subject) {
        subjectBox.sendKeys(subject);
        return this;
    }
@Step
    protected SendMessage typeMessageText(String text) {
        textBox.sendKeys(text);
        return this;
    }
@Step
    protected GmailMainPage sendMessage() throws Exception {
        try {

            sendButton.click();
            waitForSending();
        } catch (TimeoutException elementException) {
            SignInPage signInPage = new SignInPage(this.driver);
            noRecipientOk.click();
            recipients.click();
            typeRecipientEmail(signInPage.getMAIL());
            sendButton.click();
        }
        waitForSending();
        return new GmailMainPage(this.driver);
    }
@Step
    public GmailMainPage noSubject(boolean alertAction, String recipient) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(this.driver, 3);
        typeRecipientEmail(recipient);
        sendButton.click();
        Alert alert = this.driver.switchTo().alert();
        if (alertAction == true) {
            alert.accept();
            waitForSending();
        } else {
            alert.dismiss();
            wait.until(ExpectedConditions.visibilityOf(sendButton2));
        }

        return new GmailMainPage(this.driver);
    }

    public void waitForSending() {
        GmailMainPage gmailMainPage = new GmailMainPage(this.driver);
        WebDriverWait wait = new WebDriverWait(this.driver, 3);
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(gmailMainPage.confirmationToolTip, "Sending...")));
    }
}
