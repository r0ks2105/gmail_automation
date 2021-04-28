import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class TestCases {
    static WebDriverInit webDriverInit = new WebDriverInit();
    public boolean action;
    public static String loginTitle = "r0stselenium01@gmail.com - Gmail";

    @BeforeSuite
    public static void webDriverStart(){
        webDriverInit.webDriverSetup();
    }

    @BeforeClass public static void gmailLogin() throws Exception {
        //webDriverInit.positiveLogin();
        GoogleMainPage gmailLogin = new GoogleMainPage(webDriverInit.driverThreadSafe.get());
        gmailLogin.loginPage();
        SignInPage signIn = new SignInPage(webDriverInit.driverThreadSafe.get());
        signIn.enterEmail();
        WelcomePage welcomePage = new WelcomePage(webDriverInit.driverThreadSafe.get());
        welcomePage.enterPassword();
        WebDriverWait wait = new WebDriverWait(webDriverInit.driverThreadSafe.get(), 5);
        wait.until(ExpectedConditions.titleContains(loginTitle));
        Assert.assertTrue(webDriverInit.driverThreadSafe.get().getTitle().contains("r0stselenium01@gmail.com"));
        System.out.println(webDriverInit.driverThreadSafe.get());
    }

    @Test(dataProvider = "testData", dataProviderClass = TestData.class)
    @TmsLink("threats")
    @Issue("repository")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Sending messages")
    @Description("Test for positive scenario of sending messages")
    public void sendMessage(String recipient, String subject, String text, String confirmTitle) throws Exception {
        GmailMainPage gmailMainPage = new GmailMainPage(webDriverInit.driverThreadSafe.get());
        SendMessage sendMessage = gmailMainPage.newMessage();
        sendMessage.send(recipient, subject, text);
        Assert.assertEquals(gmailMainPage.getLastMessage(), confirmTitle);
        takeScreenshot();
    }

    @Test
    @TmsLink("threats")
    @Issue("files")
    @Severity(SeverityLevel.MINOR)
    @Description("Test for the case when the letter is sent without a Subject (Positive)")
    public void sendMessageWithoutSubjectPositive() throws InterruptedException {
        action=true;
        String confirmTitle = "Message sent.";
        String recipient = "mytests4selenium@yopmail.com";
        GmailMainPage gmailMainPage = new GmailMainPage(webDriverInit.driverThreadSafe.get());
        SendMessage sendMessage = new SendMessage(webDriverInit.driverThreadSafe.get());
        gmailMainPage.newMessage();
        sendMessage.noSubject(action, recipient);
        Assert.assertEquals(gmailMainPage.getLastMessage(), confirmTitle);
        takeScreenshot();
    }
    @Test
    @TmsLink("threats")
    @Issue("repository")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test for the case when the letter is sent without a Subject (Negative)")
    public void sendMessageWithoutSubjectNegative() throws InterruptedException {
        action=false;
        String recipient = "mytests4selenium@yopmail.com";
        GmailMainPage gmailMainPage = new GmailMainPage(webDriverInit.driverThreadSafe.get());
        SendMessage sendMessage = new SendMessage(webDriverInit.driverThreadSafe.get());
        gmailMainPage.newMessage();
        sendMessage.noSubject(action, recipient);
        Assert.assertTrue(webDriverInit.driverThreadSafe.get().findElement(By.cssSelector("div[class='nH Hd']")).isDisplayed());
        takeScreenshot();
    }

    @Test(dataProvider = "testData", dataProviderClass = TestData.class)
    @TmsLink("threats")
    @Issue("repository")
    @Severity(SeverityLevel.MINOR)
    @Description("Test for deleting all messages via drag and drop")
    public void deleteAllSentEmails(String recipient, String subject, String text, String confirmTitle) throws Exception {
        DeleteSentEmails deleteSentEmails = PageFactory.initElements(webDriverInit.driverThreadSafe.get(), DeleteSentEmails.class);
        GmailMainPage gmailMainPage = new GmailMainPage(webDriverInit.driverThreadSafe.get());
        gmailMainPage.openSent();
        gmailMainPage.openMore();
        deleteSentEmails.deleteAll();
        Thread.sleep(1000);
        try {
            int count = deleteSentEmails.countOfSentEmails();
            for (int i = 0; i < count; i++) {
                deleteSentEmails.deleteMessages();
                Assert.assertEquals(deleteSentEmails.confirmationToolTip.getText(), deleteSentEmails.deleteToolTip);
                takeScreenshot();
            }
        } catch (NoSuchElementException elementException) {
            gmailMainPage.newMessage();
            SendMessage sendMessage = PageFactory.initElements(webDriverInit.driverThreadSafe.get(), SendMessage.class);
            sendMessage.send(recipient, subject, text);
        }
        Thread.sleep(3000);
        int count = deleteSentEmails.countOfSentEmails();
        for (int i = 0; i < count; i++) {
            deleteSentEmails.deleteMessages();
            Assert.assertEquals(deleteSentEmails.confirmationToolTip.getText(), deleteSentEmails.deleteToolTip);
            takeScreenshot();
        }

    }

    @Test
    @TmsLink("threats")
    @Issue("P_EEI-9587")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test for deleting one message via drag and drop")
    public void deleteOneSentEmail() throws InterruptedException {
        DeleteSentEmails deleteSentEmails = PageFactory.initElements(webDriverInit.driverThreadSafe.get(), DeleteSentEmails.class);
        GmailMainPage gmailMainPage = new GmailMainPage(webDriverInit.driverThreadSafe.get());
        gmailMainPage.openSent();
        gmailMainPage.openMore();
        deleteSentEmails.deleteAll();
        Assert.assertEquals(deleteSentEmails.confirmationToolTip.getText(), deleteSentEmails.deleteToolTip);
        takeScreenshot();
    }
    @AfterClass
    public void closeBrowser() throws InterruptedException {
      webDriverInit.driverThreadSafe.get().quit();
    }


    @Attachment
    private byte[] takeScreenshot(){
        return ((TakesScreenshot)webDriverInit.driverThreadSafe.get()).getScreenshotAs(OutputType.BYTES);
    }
}

