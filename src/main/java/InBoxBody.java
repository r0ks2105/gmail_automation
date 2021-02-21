import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InBoxBody extends GmailMainPage {
    @FindBy(xpath = "//div[5]/table/tbody/tr[1]/td[1]/div")
    WebElement primaryTab;
    @FindBy(xpath = "//div[5]/table/tbody/tr[1]/td[2]/div")
    WebElement socialTab;
    @FindBy(xpath = "//div[5]/table/tbody/tr[1]/td[3]/div")
    WebElement promotionsTab;
    @FindBy(xpath = "//div[1]/div[3]/div/table/tbody/tr[1]")
    WebElement firstElementInPrimaryGrid;
    @FindBy(xpath = "//div[2]/div[1]/div/table/tbody/tr")
    WebElement firsElementInSocialGrid;
    @FindBy(xpath = "//div[3]/div[3]/div/table/tbody/tr[1]")
    WebElement getFirstElementInPromotionsGrid;
    @FindBy(xpath = "//div[2]/div[1]/div[1]/div/div/div[1]/div/div[1]/span")
    WebElement allItemsCheckbox;

    public InBoxBody(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void openPrimaryTab(){
        primaryTab.click();
    }
    public void openSocialTab(){
        socialTab.click();
    }
    public void openPromotionsTab(){
        promotionsTab.click();
    }
    public void selectAllItems(){
        allItemsCheckbox.click();
    }
}
