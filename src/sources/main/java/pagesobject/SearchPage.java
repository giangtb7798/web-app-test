package pagesobject;

import core.WebApi;
import io.qameta.allure.Step;
import models.SearchResults;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Thread.sleep;

public class SearchPage extends WebApi {
    @FindBy(xpath = "//input[@placeholder=\"Search or run a command…\"]")
    private WebElement fieldSearch;

    @FindBy(xpath = "//div[contains(@class,\"Option-contents-u3POB\")]/div[contains(@class,'OptionTitle')]/span/span[last()]")
    private List<WebElement> txtTitle;
    @FindBy(xpath = "//div[contains(@class,\"Option-contents-u3POB\")]/div[contains(@class,'OptionSubtitle')]/span[1]")
    private List<WebElement> txtSubtitle;
    @FindBy(xpath = "//h2[contains(text(),'No results found')]")
    private WebElement notFoundMessage;
    @Step("enter search value")
    public SearchPage enterSearchValue(String value){
        clearTextElementWithKeys(fieldSearch);
        sendKeyToElement(fieldSearch, value);
        return this;
    }

    @Step("get all results")
    public List<SearchResults> getAllResults() throws InterruptedException {
        List<SearchResults> results = new ArrayList<SearchResults>();
        sleep(5000);
        for (int i = 0; i < txtTitle.size(); i++) {
            SearchResults searchResults = new SearchResults();
            searchResults.setTitle(getTextElement(txtTitle.get(i)));
            searchResults.setSubTitle(getTextElement(txtSubtitle.get(i)));
            results.add(searchResults);
        }
        return results;
    }

    @Step("verify that results include keyword")
    public SearchPage verifyThatResultsIncludeKeyword(List<SearchResults> searchResults, String keyWord, String subTitle){
        boolean keyWordExits = searchResults
                .stream()
                .anyMatch(s -> s.getTitle().contains(keyWord) && s.getSubTitle().matches(subTitle));
        Assert.assertTrue(keyWordExits);
        return this;
    }

    @Step("verify message displayed if don't find any matches")
    public SearchPage verifyMessageNotFoundDisplayed(){
        Assert.assertTrue(isControlDisplayed(notFoundMessage));
        return this;
    }
}
