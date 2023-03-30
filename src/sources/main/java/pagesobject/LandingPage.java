package pagesobject;

import core.WebApi;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class LandingPage extends WebApi {
    @FindBy(xpath = "//div[contains(text(),'Home')]")
    private WebElement txtHome;
    @FindBy(xpath = "//a[contains(text(),'do not edit')]")
    private WebElement txtSpaceName;

    @Step("verify login successfully")
    public LandingPage verifyLoginSuccessfully(){
        Assert.assertTrue(isControlDisplayed(txtHome));
        return this;
    }
    @Step("verify that space created successfully")
    public LandingPage verifySpaceCreatedSuccessfully(){
        Assert.assertTrue(isControlDisplayed(txtSpaceName));
        return this;
    }
}
