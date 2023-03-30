package core;

import config.WebAppDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class WebApi {
    WebElement element;
    List<WebElement> elements;
    JavascriptExecutor javascriptExecutor;
    WebDriverWait waitExplicit;
    Actions action;
    By byLocator;
    public WebDriver driver = WebAppDriverManager.getDriver();
    boolean status;

    public WebApi() {
        PageFactory.initElements(this.driver, this);
    }

    public void openAnyUrl(String url) {
        this.driver.get(url);
    }
    public void highLightElement(WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
    }

    public String getCurrentPageUrl() {
        return this.driver.getCurrentUrl();
    }

    public String getPageTitle() {
        return this.driver.getTitle();
    }

    public String getPageSourceCode() {
        return this.driver.getPageSource();
    }

    public void backToPreviousPage() {
        this.driver.navigate().back();
    }

    public void refreshCurrentPage() {
        this.driver.navigate().refresh();
    }

    public void forwardToNextPage() {
        this.driver.navigate().forward();
    }

    public void acceptAlert() {
        this.driver.switchTo().alert().accept();
    }

    public void cancelAlert() {
        this.driver.switchTo().alert().dismiss();
    }

    public String getTextAlert() {
        return this.driver.switchTo().alert().getText();
    }
    public String getParentWindowID() {
        return this.driver.getWindowHandle();
    }

    public void sendKeyToAlert(String value) {
        this.driver.switchTo().alert().sendKeys(value);
    }

    public void clearTextElement(String locator, String... values) {
        locator = String.format(locator, (Object[])values);
        this.element = this.waitForElementVisible(locator);
        this.element.clear();
    }

    public void clearTextElement(WebElement element) {
        element = this.waitForElementVisible(element);
        element.clear();
    }

    public void selectAllWithKeys(WebElement element) {
        this.clickToElement(element);
        String os = System.getProperty("os.name");
        this.action = new Actions(this.driver);
        if (os.contains("Mac")) {
            this.action.keyDown(Keys.COMMAND).sendKeys(new CharSequence[]{"a"}).keyUp(Keys.COMMAND).build().perform();
        } else {
            this.action.keyDown(Keys.CONTROL).sendKeys(new CharSequence[]{"a"}).keyUp(Keys.CONTROL).build().perform();
        }

    }
    public WebElement waitForElementVisible(String locator, String... values) {
        this.waitExplicit = new WebDriverWait(this.driver, (long)20);
        locator = String.format(locator, (Object[])values);
        this.byLocator = By.xpath(locator);

        try {
            return (WebElement)this.waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(this.byLocator));
        } catch (Exception var4) {
            return null;
        }
    }
    public WebElement waitForElementVisible(WebElement element) {
        this.waitExplicit = new WebDriverWait(this.driver, (long)180);

        try {
            element = (WebElement)this.waitExplicit.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception var3) {
        }

        return element;
    }
    public WebElement waitForElementVisible1(WebElement element) {
        this.waitExplicit = new WebDriverWait(this.driver, (long)2);

        try {
            this.waitExplicit.until(ExpectedConditions.stalenessOf(element));
            element = (WebElement)this.waitExplicit.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception var3) {
        }

        return element;
    }

    public void clickToElement(WebElement element) {
        element = this.waitForElementVisible(element);
        element.click();
    }
    public void clickToElement(String locator, String... values) {
        locator = String.format(locator, (Object[])values);
        this.element = this.waitForElementVisible(locator);
        this.element.click();
    }
    public boolean isControlDisplayed(WebElement element) {
        element = this.waitForElementVisible(element);
        return element.isDisplayed();
    }
    public boolean isControlDisplayed(String locator, String... values) {
        locator = String.format(locator, (Object[])values);
        element = this.waitForElementVisible(locator);
        return element.isDisplayed();
    }
    public void sendKeyToElement(WebElement element, String value) {
        element = this.waitForElementVisible(element);
        element.sendKeys(value);
    }
    public void clearTextElementWithKeys(WebElement element) {
        element = this.waitForElementVisible(element);
        this.selectAllWithKeys(element);
        this.action = new Actions(this.driver);
        this.action.sendKeys(new CharSequence[]{Keys.DELETE}).build().perform();
    }

    public void clearTextElementWithKeys(String locator, String... values) {
        locator = String.format(locator, (Object[]) values);
        this.element = this.waitForElementVisible(locator);
        this.selectAllWithKeys(this.element);
        this.action = new Actions(this.driver);
        this.action.sendKeys(new CharSequence[]{Keys.DELETE}).build().perform();
    }
    public void selectItemInDropdown(WebElement element, String expectedValueInDropdown) {
        element = this.waitForElementVisible(element);
        Select select = new Select(element);
        select.selectByVisibleText(expectedValueInDropdown);
    }
    public void switchToChildWindowByID(String parentID) {
        Set<String> allWindows = this.driver.getWindowHandles();
        Iterator var3 = allWindows.iterator();

        while(var3.hasNext()) {
            String runWindow = (String)var3.next();
            if (!runWindow.equals(parentID)) {
                this.driver.switchTo().window(runWindow);
                break;
            }
        }
    }
    public String getAttributeValue(WebElement element, String attributeName) {
        element = this.waitForElementVisible(element);
        return element.getAttribute(attributeName);
    }
    public String getAttributeValue(String locator, String attributeName, String... values) {
        locator = String.format(locator, (Object[])values);
        this.element = this.driver.findElement(By.xpath(locator));
        return this.element.getAttribute(attributeName);
    }
    public void sendKeyToElement(String locator, String sendKeyValue, String... values) {
        locator = String.format(locator, (Object[])values);
        this.element = this.waitForElementVisible(locator);
        this.element.sendKeys(new CharSequence[]{sendKeyValue});
    }
    public boolean isControlEnabled(WebElement element) {
        element = this.waitForElementVisible1(element);
        return element.isEnabled();
    }
    public boolean isControlEnabled(String locator, String... value) {
        locator = String.format(locator, (Object[])value);
        this.element = this.driver.findElement(By.xpath(locator));
        return element.isEnabled();
    }
    public void overrideGlobalTimeout(int timeOut) {
        this.driver.manage().timeouts().implicitlyWait((long)timeOut, TimeUnit.SECONDS);
    }
    public boolean isControlUnDisplayed(String locator) {
        this.overrideGlobalTimeout(WebAppDriverManager.getSHORT_TIMEOUT());
        List<WebElement> elements = this.driver.findElements(By.xpath(locator));
        if (elements.isEmpty()) {
            this.overrideGlobalTimeout(WebAppDriverManager.getLONG_TIMEOUT());
            return true;
        } else if (!((WebElement)elements.get(0)).isDisplayed()) {
            this.overrideGlobalTimeout(WebAppDriverManager.getLONG_TIMEOUT());
            return true;
        } else {
            this.overrideGlobalTimeout(WebAppDriverManager.getLONG_TIMEOUT());
            return false;
        }
    }

    public boolean isControlUnDisplayed(String locator, String... values) {
        this.overrideGlobalTimeout(WebAppDriverManager.getSHORT_TIMEOUT());
        locator = String.format(locator, (Object[])values);
        List<WebElement> elements = this.driver.findElements(By.xpath(locator));
        if (elements.isEmpty()) {
            this.overrideGlobalTimeout(WebAppDriverManager.getLONG_TIMEOUT());
            return true;
        } else if (!((WebElement)elements.get(0)).isDisplayed()) {
            this.overrideGlobalTimeout(WebAppDriverManager.getLONG_TIMEOUT());
            return true;
        } else {
            this.overrideGlobalTimeout(WebAppDriverManager.getLONG_TIMEOUT());
            return false;
        }
    }
    public void hoverMouseToElement(String locator) {
        this.element = this.driver.findElement(By.xpath(locator));
        this.action = new Actions(this.driver);
        this.action.moveToElement(this.element).perform();
    }
    public String getTextElement(String locator, String... values) {
        locator = String.format(locator, (Object[])values);
        this.element = this.waitForElementVisible(locator);
        return this.element.getText();
    }

    public String getTextElement(WebElement element) {
        element = this.waitForElementVisible(element);
        return element.getText();
    }
}
