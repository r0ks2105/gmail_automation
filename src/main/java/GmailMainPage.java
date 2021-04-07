import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GmailMainPage {
    WebDriver driver;
    @FindBy(css = ".z0 div[role='button']")
    WebElement newMail;
    @FindBy(css = "[class='TN bzz aHS-bnt']")
    WebElement inBox;
    @FindBy(css = "div.aim>div[class='TO']>div[class='TN bzz aHS-bnw']")
    WebElement starred;
    @FindBy(css = "div.aim>div[class='TO']>div[class='TN bzz aHS-bu1']")
    WebElement snoozed;
    @FindBy(css = "div.aim>div[class='TO']>div[class='TN bzz aHS-bnu']")
    WebElement sent;
    @FindBy(css = "div.aim>div[class='TO']>div[class='TN bzz aHS-bnq']")
    WebElement draft;
    @FindBy(css = "span[class='CJ']")
    WebElement more;
    @FindBy(css = "span[class='J-Ke n4 ah9 aiu air']")
    WebElement less;
    @FindBy(css = "div.TO>div[class='TN bzz aHS-bns']")
    WebElement important;
    @FindBy(css = "div.TO>div[class='TN bzz aHS-aHP']")
    WebElement chats;
    @FindBy(css = "div.TO>div[class='TN bzz aHS-nd']")
    WebElement scheduled;
    @FindBy(css = "div.TO>div[class='TN bzz aHS-aHO']")
    WebElement allEmails;
    @FindBy(css = "div[data-tooltip='Spam']")
    WebElement spam;
    @FindBy(css = "div[data-tooltip='Trash']")
    WebElement trash;
    @FindBy(css = "span .bAq")
    WebElement confirmationToolTip;

    public GmailMainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public SendMessage newMessage() {
        newMail.click();
        return new SendMessage(this.driver);
    }

    public InBoxBody openInBox() {
        inBox.click();
        return new InBoxBody(this.driver);
    }

    public void openStarred() {
        starred.click();
    }

    public void openSnoozed() {
        snoozed.click();
    }

    public void openSent() {
        sent.click();
    }

    public void openDraft() {
        draft.click();
    }

    public void openMore() {
        more.click();
    }

    public void openLess() {
        less.click();
    }

    public void openImportant() {
        important.click();
    }

    public void openChats() {
        chats.click();
    }

    public void openScheduled() {
        scheduled.click();
    }

    public void openAllEmails() {
        allEmails.click();
    }

    public void openSpam() {
        spam.click();
    }

    public void openTrash() {
        trash.click();
    }

    public String getLastMessage() {
        WebDriverWait wait = new WebDriverWait(this.driver, 3);
        wait.until(ExpectedConditions.visibilityOf(confirmationToolTip));
        return confirmationToolTip.getText();
    }

}
