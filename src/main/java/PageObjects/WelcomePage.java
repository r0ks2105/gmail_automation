package PageObjects;

import RestAssured.ReadProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WelcomePage {

    String property = "password";
    private final String PASSWORD = ReadProperties.getInstance().getPropertyValue(property);
    private WebDriver driver;

    @FindBy(name = "password")
    WebElement inputPassword;
    @FindBy(css = "#passwordNext > div > button > div.VfPpkd-RLmnJb")
    WebElement nextButton;

    public WelcomePage (WebDriver driver) throws Exception {
        PageFactory.initElements(driver, this);
        this.driver=driver;

    }
    public void enterPassword(){
        inputPassword.sendKeys(PASSWORD);
        nextButton.click();
    }
}
