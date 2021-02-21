import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInPage {
    private final static String MAIL = "r0stselenium01@gmail.com";
    private WebDriver driver;

    @FindBy(css = "#identifierId")
    WebElement loginField;
    @FindBy(css = "#identifierNext > div > button > div.VfPpkd-RLmnJb")
    WebElement nextButton;
    public SignInPage (WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver=driver;
    }
    public WelcomePage enterEmail(){
        loginField.sendKeys(MAIL);
        nextButton.click();
        return new WelcomePage(this.driver);
    }
    public static String getMAIL() {
        return MAIL;
    }
}
