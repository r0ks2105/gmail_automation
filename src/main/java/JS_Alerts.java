import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class JS_Alerts {

@Test
    public void webDriver() throws IOException {
    WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("file:///F:/Java_projects/test_files/JS_alerts.html");
        Alert alert = driver.switchTo().alert();
        alert.accept();
        alert.sendKeys("Rostyslav");
        alert.accept();
        String helloMessage = alert.getText();
    Assert.assertEquals(helloMessage, "Hello, Rostyslav");
    System.out.println(helloMessage);
    alert.accept();
    }
}