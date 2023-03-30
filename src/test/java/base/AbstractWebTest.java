package base;

import config.WebAppDriverManager;
import httprequest.HttpRequests;
import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import static config.WebAppDriverManager.closeBrowser;
import static config.WebAppDriverManager.openMultiBrowser;

public class AbstractWebTest {
    @BeforeMethod
    public void beforeMethod() throws IOException {
        HttpRequests.initReq();
        HttpRequests.setAuthorization("pk_55578828_L0EICZVTIO92ZOPYY9MA7UBI6WZ20JU7");
        HttpRequests.setAllUrl("https://api.clickup.com/api/v2");
        openMultiBrowser(System.getProperty("webBrowser.browser"));
    }
    @AfterMethod
    public void afterMethod() throws IOException {
        closeBrowser(WebAppDriverManager.getDriver());
    }

    @BeforeSuite
    @Parameters("browser")
    public void beforeSuite(@Optional("chrome") String browser) {
        System.setProperty("webBrowser.browser", browser);
    }

}