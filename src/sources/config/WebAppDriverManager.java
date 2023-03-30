package config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebAppDriverManager {
    private static int SHORT_TIMEOUT = 5;
    private static int LONG_TIMEOUT = 30;
    private static ThreadLocal<WebDriver> driver = new ThreadLocal();

    public static void setDriver(WebDriver webDriver) {
        driver.set(webDriver);
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void openMultiBrowser(String browser){
        WebDriver tmpDriver = null;
        ChromeOptions chromeOptions;
        System.out.println(browser.getClass());
        if(browser.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            chromeOptions = new ChromeOptions();
            tmpDriver = new ChromeDriver(chromeOptions);
        }else if(browser.equalsIgnoreCase("hchrome")){
            WebDriverManager.chromedriver().setup();
            chromeOptions = new ChromeOptions();
            chromeOptions.addArguments(new String[]{"headless"});
            tmpDriver = new ChromeDriver(chromeOptions);
        }
        tmpDriver.manage().window().maximize();
        setDriver(tmpDriver);
    }
    public static int getLONG_TIMEOUT() {
        return LONG_TIMEOUT;
    }

    public static void setLONG_TIMEOUT(int LONG_TIMEOUT) {
        WebAppDriverManager.LONG_TIMEOUT = LONG_TIMEOUT;
    }
    public static int getSHORT_TIMEOUT() {
        return SHORT_TIMEOUT;
    }
    public static void setSHORT_TIMEOUT(int SHORT_TIMEOUT) {
        WebAppDriverManager.SHORT_TIMEOUT = SHORT_TIMEOUT;
    }
    public static void closeBrowser(WebDriver driver) {
        driver.close();
    }
}
