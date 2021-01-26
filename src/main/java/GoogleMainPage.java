import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleMainPage {
    private WebDriver driver;
    @FindBy(css = "a.gb_g")
    WebElement gmail;
    @FindBy(css = "div.h-c-header__bar > div.h-c-header__cta > ul.h-c-header__cta-list.header__nav--ltr > li:nth-child(2) > a")
    WebElement SigIn;
    public GoogleMainPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    public SignInPage loginPage(){
        gmail.click();
        SigIn.click();
        for (String childTab:driver.getWindowHandles()){
            driver.switchTo().window(childTab);
        }
        return new SignInPage(this.driver);
    }
}
