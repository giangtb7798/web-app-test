package tests;

import base.AbstractWebTest;
import pagesobject.LandingPage;
import pagesobject.LoginPage;
import org.testng.annotations.Test;

import static constants.ClickUpMessage.*;

public class LoginTest extends AbstractWebTest {

    @Test(description = "verify login successfully")
    public void login_to_click_up_successfully(){
        String mail;
        String password;
        LoginPage loginPage = new LoginPage();
        LandingPage landingPage = new LandingPage();

        // Bỏ trống trường email và password sau đó nhấn submit
        loginPage
                .goToLoginPage()
                .clickToSubmitBtn()
                .verifyWarningEmailDisplayed(EMAIL_REQUIRED)
                .verifyWarningPasswordDisplayed(PASSWORD_REQUIRED);

        // mật khẩu nhỏ hơn 8 kí tự
        password ="123";
        loginPage
                .goToLoginPage()
                .enterPassword(password)
                .clickToSubmitBtn()
                .verifyWarningPasswordDisplayed(INVALID_PASSWORD);

        // Nhập sai định dạng email
        mail = "ntruonggiangtb98";
        loginPage
                .goToLoginPage()
                .enterEmailAddress(mail)
                .clickToSubmitBtn()
                .verifyWarningEmailDisplayed(INVALID_EMAIL)
                ;

        //Nhập đúng email và password
        mail = "ntruonggiangtb98@gmail.com";
        password = "07071998Gg";
        loginPage
                .goToLoginPage()
                .enterEmailAddress(mail)
                .enterPassword(password)
                .clickToSubmitBtn();

        landingPage
                .verifyLoginSuccessfully();
    }
}
