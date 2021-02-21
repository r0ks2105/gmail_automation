import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class WebDriverInit {
    public ThreadLocal<WebDriver> driverThreadSafe = new ThreadLocal<>();
    public static String url = "https://google.com";
    public String loginTitle = "r0stselenium01@gmail.com - Gmail";
    public boolean action;

    @BeforeSuite
    public void webDriverSetup() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driverThreadSafe.set(driver);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(url);

    }

    @BeforeClass
    public void positiveLogin() throws Exception {
        GoogleMainPage gmailLogin = new GoogleMainPage(driverThreadSafe.get());
        gmailLogin.loginPage();
        SignInPage signIn = new SignInPage(driverThreadSafe.get());
        signIn.enterEmail();
        WelcomePage welcomePage = new WelcomePage(driverThreadSafe.get());
        welcomePage.enterPassword();
        WebDriverWait wait = new WebDriverWait(driverThreadSafe.get(), 5);
        wait.until(ExpectedConditions.titleContains(loginTitle));
        Assert.assertTrue(driverThreadSafe.get().getTitle().contains("r0stselenium01@gmail.com"));
        System.out.println(driverThreadSafe.get().getTitle());
    }

    @Test
    public void allPages() throws InterruptedException {
        GmailMainPage gmailMainPage = new GmailMainPage(driverThreadSafe.get());
        InBoxBody inBoxBody = new InBoxBody(driverThreadSafe.get());
        gmailMainPage.newMessage();
        Thread.sleep(2000);
        gmailMainPage.openDraft();
        Thread.sleep(2000);
        gmailMainPage.openInBox();
        inBoxBody.openPromotionsTab();
        inBoxBody.openSocialTab();
        inBoxBody.openPrimaryTab();
        inBoxBody.selectAllItems();
        Thread.sleep(2000);
        gmailMainPage.openStarred();
        Thread.sleep(2000);
        gmailMainPage.openSent();
        Thread.sleep(2000);
        gmailMainPage.openSnoozed();
        Thread.sleep(2000);
        gmailMainPage.openMore();
        Thread.sleep(2000);
        gmailMainPage.openImportant();
        Thread.sleep(2000);
        gmailMainPage.openChats();
        Thread.sleep(2000);
        gmailMainPage.openScheduled();
        Thread.sleep(2000);
        gmailMainPage.openAllEmails();
        Thread.sleep(2000);
        gmailMainPage.openSpam();
        Thread.sleep(2000);
        gmailMainPage.openTrash();
        Thread.sleep(2000);
        gmailMainPage.openLess();
    }

    @Test(dataProvider = "testData", dataProviderClass = TestData.class)
    public void sendMessage(String recipient, String subject, String text, String confirmTitle) throws InterruptedException {
        GmailMainPage gmailMainPage = new GmailMainPage(driverThreadSafe.get());
        SendMessage sendMessage = gmailMainPage.newMessage();
        sendMessage.send(recipient, subject, text);
        Assert.assertEquals(gmailMainPage.getLastMessage(), confirmTitle);
    }

    @Test
    public void sendMessageWithoutSubjectPositive() throws InterruptedException {
        action=true;
        String confirmTitle = "Message sent.";
        String recipient = "mytests4selenium@yopmail.com";
        GmailMainPage gmailMainPage = new GmailMainPage(driverThreadSafe.get());
        SendMessage sendMessage = new SendMessage(driverThreadSafe.get());
        gmailMainPage.newMessage();
        sendMessage.noSubject(action, recipient);
        Assert.assertEquals(gmailMainPage.getLastMessage(), confirmTitle);
    }
    @Test
    public void sendMessageWithoutSubjectNegative() throws InterruptedException {
        action=false;
        String confirmTitle = "Message sent.";
        String recipient = "mytests4selenium@yopmail.com";
        GmailMainPage gmailMainPage = new GmailMainPage(driverThreadSafe.get());
        SendMessage sendMessage = new SendMessage(driverThreadSafe.get());
        gmailMainPage.newMessage();
        sendMessage.noSubject(action, recipient);
        Assert.assertTrue(driverThreadSafe.get().findElement(By.cssSelector("div[class='nH Hd']")).isDisplayed());
    }

    @Test(dataProvider = "testData", dataProviderClass = TestData.class)
    public void deleteAllSentEmails(String recipient, String subject, String text, String confirmTitle) throws InterruptedException {
        DeleteSentEmails deleteSentEmails = PageFactory.initElements(driverThreadSafe.get(), DeleteSentEmails.class);
        GmailMainPage gmailMainPage = new GmailMainPage(driverThreadSafe.get());
        gmailMainPage.openSent();
        gmailMainPage.openMore();
        deleteSentEmails.deleteAll();
        Thread.sleep(1000);
        try {
            int count = deleteSentEmails.countOfSentEmails();
            for (int i = 0; i < count; i++) {
                deleteSentEmails.deleteMessages();
                Assert.assertEquals(deleteSentEmails.confirmationToolTip.getText(), deleteSentEmails.deleteToolTip);
            }
        } catch (NoSuchElementException elementException) {
            gmailMainPage.newMessage();
            SendMessage sendMessage = PageFactory.initElements(driverThreadSafe.get(), SendMessage.class);
            sendMessage.send(recipient, subject, text);
        }
        Thread.sleep(3000);
        int count = deleteSentEmails.countOfSentEmails();
        for (int i = 0; i < count; i++) {
            deleteSentEmails.deleteMessages();
            Assert.assertEquals(deleteSentEmails.confirmationToolTip.getText(), deleteSentEmails.deleteToolTip);
        }

    }

    @Test
    public void deleteOneSentEmail() throws InterruptedException {
        DeleteSentEmails deleteSentEmails = PageFactory.initElements(driverThreadSafe.get(), DeleteSentEmails.class);
        GmailMainPage gmailMainPage = new GmailMainPage(driverThreadSafe.get());
        gmailMainPage.openSent();
        gmailMainPage.openMore();
        deleteSentEmails.deleteAll();
        Assert.assertEquals(deleteSentEmails.confirmationToolTip.getText(), deleteSentEmails.deleteToolTip);
    }
}

   /* @AfterClass
    public void closeBrowser() throws InterruptedException {
       driverThreadSafe.get().quit();
    }
*/
