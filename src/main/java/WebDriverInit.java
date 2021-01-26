import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class WebDriverInit {
    public ThreadLocal<WebDriver> driverThreadSafe = new ThreadLocal<>();
    public static String url ="https://google.com";
    public String loginTitle = "Входящие - r0stselenium01@gmail.com - Gmail";
    @BeforeClass
    public void webDriverSetup(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driverThreadSafe.set(driver);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(url);

    }
    @Test
    public void positiveLogin() throws Exception {
        GoogleMainPage gmailLogin = new GoogleMainPage(driverThreadSafe.get());
        gmailLogin.loginPage();
        SignInPage signIn =new SignInPage(driverThreadSafe.get());
        signIn.enterEmail();
        WelcomePage welcomePage = new WelcomePage(driverThreadSafe.get());
        welcomePage.enterPassword();
        WebDriverWait wait = new WebDriverWait(driverThreadSafe.get(), 5);
        wait.until(ExpectedConditions.titleIs(loginTitle));
        Assert.assertTrue(driverThreadSafe.get().getTitle().contains("r0stselenium01@gmail.com"));
        System.out.println(driverThreadSafe.get().getTitle());
    }

    @Test(dataProvider = "testData", dataProviderClass = TestData.class)
    public void sendMessage( String recipient, String subject, String text, String confirmTitle) throws InterruptedException {
                SendMessage sendMessage = new SendMessage(driverThreadSafe.get());
        sendMessage.sendTestEmail(recipient, subject, text, confirmTitle);
    }
    @Test(dataProvider = "testData", dataProviderClass = TestData.class)
    public void deleteSentEmails(String recipient, String subject, String text, String confirmTitle) throws InterruptedException {
        DeleteSentEmails deleteSentEmails = new DeleteSentEmails(driverThreadSafe.get());
      try {
          deleteSentEmails.deleteMessages();
      }
      catch (NoSuchElementException elementException){
          sendMessage(recipient, subject, text, confirmTitle);
      }
        deleteSentEmails.deleteMessages();
    }
   /* @AfterMethod
    public void closeBrowser(){
       driverThreadSafe.get().quit();
    }*/
}
