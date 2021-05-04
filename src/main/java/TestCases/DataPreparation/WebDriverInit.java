package TestCases.DataPreparation;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;
@Epic("Selenium, TestNG, Allure examples")
@Feature("Allure support")
public class WebDriverInit {
    public ThreadLocal<WebDriver> driverThreadSafe = new ThreadLocal<>();
    public static String url = "https://google.com";

    public void webDriverSetup() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driverThreadSafe.set(driver);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(url);

    }
}
