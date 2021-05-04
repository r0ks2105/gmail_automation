package PageObjects;

import PageObjects.WelcomePage;
import RestAssured.ReadProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInPage {
    String property = "login";
    private final String MAIL = ReadProperties.getInstance().getPropertyValue(property);
    private WebDriver driver;

    @FindBy(css = "#identifierId")
    WebElement loginField;
    @FindBy(css = "#identifierNext > div > button > div.VfPpkd-RLmnJb")
    WebElement nextButton;
    public SignInPage (WebDriver driver) throws Exception {
        PageFactory.initElements(driver, this);
        this.driver=driver;
    }
    public WelcomePage enterEmail() throws Exception {
        loginField.sendKeys(MAIL);
        nextButton.click();
        return new WelcomePage(this.driver);
    }
    public String getMAIL() {
        return MAIL;
    }
}
