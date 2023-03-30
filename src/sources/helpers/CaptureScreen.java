package helpers;

import config.WebAppDriverManager;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import static utils.DateTimeUtils.getTimeNow;

public class CaptureScreen {
    public static void takeScreenShot(String imageName) throws IOException {
        File srcFile = ((TakesScreenshot) WebAppDriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
        File desFile = new File("./screenshot/" + imageName + ".png");

        FileUtils.copyFile(srcFile, desFile);
    }
    public static void attachScreenshot() throws IOException {
        String imageName = getTimeNow();
        takeScreenShot(imageName);
        InputStream inputStream = Files.newInputStream(Paths.get("./screenshot/" + imageName + ".png"));
        Allure.addAttachment("Failure Screenshot", inputStream);
    }
}
