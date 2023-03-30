package pagesobject;

import core.WebApi;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static constants.Url.LOGIN_PAGE;

public class LoginPage extends WebApi {

    @FindBy(id = "login-email-input")
    private WebElement fieldEmail;

    @FindBy(id = "login-password-input")
    private WebElement fieldPassword;

    @FindBy(xpath = "//button[@data-test=\"login-submit\"]")
    private WebElement loginSubmitButton;

    private String txtWarningEmail = "//span[contains(text(),'%s')]";

    private String txtWarningPassword = "//span[contains(text(),'%s')]";


    @Step("go to login page")
    public LoginPage goToLoginPage(){
        openAnyUrl(LOGIN_PAGE);
        return this;
    }
    @Step("Enter your email address")
    public LoginPage enterEmailAddress(String emailAddress){
        sendKeyToElement(fieldEmail, emailAddress);
        return this;
    }
    @Step("Enter your password")
    public LoginPage enterPassword(String password){
        sendKeyToElement(fieldPassword, password);
        return this;
    }
    @Step("Click to submit button")
    public LoginPage clickToSubmitBtn(){
        clickToElement(loginSubmitButton);
        return this;
    }
    @Step("verify warning email displayed")
    public LoginPage verifyWarningEmailDisplayed(String message){
        isControlDisplayed(txtWarningEmail, message);
        return this;
    }
    @Step("verify warning password displayed")
    public LoginPage verifyWarningPasswordDisplayed(String message){
        isControlDisplayed(txtWarningPassword, message);
        return this;
    }
}
