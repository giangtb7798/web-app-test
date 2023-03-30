package pagesobject;

import core.WebApi;
import io.qameta.allure.Step;

import models.SpaceModel;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static constants.ClickUpMessage.*;
import static java.lang.Thread.sleep;

public class NavigationPage extends WebApi {
    @FindBy(xpath = "//div[contains(text(),'New Space')]")
    private WebElement newSpaceBtn;
    @FindBy(xpath = "//div[@role=\"dialog\"]//h2[contains(text(),'Create new Space')]")
    private WebElement createNewSpaceDialog;
    @FindBy(xpath = "//div[contains(text(),'Space name')]//following-sibling::div/input")
    private WebElement fieldSpaceName;
    @FindBy(xpath = "//button[contains(text(),'Next')]")
    private WebElement nextBtn;
    @FindBy(xpath = "//button[contains(text(),'Create Space')]")
    private WebElement createSpaceBtn;
    @FindBy(xpath = "//div[contains(text(),'Folder name is required!')]")
    private WebElement warningFolderNameRequired;
    @FindBy(xpath = "//div[@class='cu-create-project-modal__pagination-item'][last()]")
    private WebElement lastPaginationBtn;
    @FindBy(xpath = "//p[contains(text(),'Space color')]")
    private WebElement spaceColorPicker;

    @FindBy(xpath = "//div[contains(text(),'Edit Space name')]")
    private WebElement editSpaceNameDialog;

    @FindBy(xpath = "//button[contains(text(),'Save')]")
    private WebElement saveBtn;

    @FindBy(xpath = "//div[@role=\"dialog\"]//h2[contains(text(),'Create Folder')]")
    private WebElement createFolderDialog;
    @FindBy(xpath = "//input[@placeholder=\"Enter Folder name\"]")
    private WebElement folderNameField;
    @FindBy(xpath = "//div[contains(text(),'Create Folder')]")
    private WebElement createFolderBtn;

    @FindBy(xpath = "//div[contains(text(),'HIDDEN')]")
    private WebElement hiddenSpace;
    @FindBy(xpath = "//h2[contains(text(),'Create List')]")
    private WebElement createListDialog;
    @FindBy(xpath = "//div[contains(text(),'List name')]//following-sibling::input")
    private WebElement fieldList;
    @FindBy(xpath = "//button[contains(text(),'Create List')]")
    private WebElement btnCreateList;
    @FindBy(xpath = "//div[@data-test=\"search__inner\"]")
    private WebElement fieldSearch;
    @FindBy(xpath = "//cu2-project-list-bar-item//a[@tooltipmodifier=\"tooltip_right\"]")
    private List<WebElement> spaceList;
    private String spaceSettingBtn = "//div[@aria-label=\"%s Space settings\"]";
    private String folderSettingBtn = "//div[@aria-label=\"%s Folder settings\"]";
    private String txtOptionInSpaceSetting = "(//div[contains(text(),'%s')])[last()]";
    private String txtOptionInFolderSetting = "(//div[contains(text(),'%s')])[last()]";
    private String spaceName = "//a[contains(text(),'%s')]";
    private String warningMessage = "//div[contains(text(),'%s')]";
    private String txtFolderName = "//div[contains(text(),'%s')]";
    private String sideBar = "//span[contains(text(),'%s')]";
    private String spaceNameInSideBar = "(//div[contains(text(),'%s')])[last()]";
    private String folder = "//a[contains(text(),'%s')]";
    private String txtList = "//button//cu-editable[contains(text(),'%s')]";

    @Step("Click to new space button")
    public NavigationPage clickToNewSpaceBtn(){
        clickToElement(newSpaceBtn);
        return this;
    }
    @Step("enter space name")
    public NavigationPage enterSpaceName(String spaceName){
        clearTextElementWithKeys(fieldSpaceName);
        sendKeyToElement(fieldSpaceName, spaceName);
        return this;
    }
    @Step("enter folder name")
    public NavigationPage enterFolderName(String folderName){
        clearTextElementWithKeys(folderNameField);
        sendKeyToElement(folderNameField, folderName);
        return this;
    }
    @Step("CLick to last button")
    public NavigationPage clickToLastPaginationBtn(){
        clickToElement(lastPaginationBtn);
        return this;
    }
    @Step("CLick to create space button")
    public NavigationPage clickToCreateSpaceBtn(){
        clickToElement(createSpaceBtn);
        return this;
    }

    @Step("CLick to space setting button")
    public NavigationPage clickToSpaceSettingBtn(String nameSpace){
        String locator = String.format(spaceSettingBtn, nameSpace);
        waitForElementVisible(spaceName, nameSpace);
        hoverMouseToElement(locator);
        clickToElement(locator);
        return this;
    }
    @Step("Click to option in space setting")
    public NavigationPage clickToOptionInSpaceSetting(String optionName){
        String locator = String.format(txtOptionInSpaceSetting, optionName);
        waitForElementVisible(locator);
        hoverMouseToElement(locator);
        clickToElement(locator);
        return this;
    }

    @Step("Click to save button")
    public NavigationPage clickToSaveBtn(){
        clickToElement(saveBtn);
        return this;
    }
    @Step("Click to create folder button")
    public NavigationPage clickToCreateFolderBtn(){
        clickToElement(createFolderBtn);
        return this;
    }
    @Step("Click to space")
    public NavigationPage clickToSpace(String nameSpace){
        waitForElementVisible(spaceName, nameSpace);
        if(getAttributeValue(spaceName, "href", nameSpace) != null){
            clickToElement(spaceName, nameSpace);
        }
        return this;
    }
    @Step("Click to folder setting")
    public NavigationPage clickToFolderSetting(String folderName){
        waitForElementVisible(folder, folderName);
        clickToElement(folderSettingBtn, folderName);
        return this;
    }

    @Step("Click to option folder setting")
    public NavigationPage clickToOptionFolderSetting(String option){
        clickToElement(txtOptionInFolderSetting, option);
        return this;
    }
    @Step("Click to folder")
    public NavigationPage clickToFolder(String folderName){
        waitForElementVisible(folder, folderName);
        clickToElement(folder, folderName);
        return this;
    }

    @Step("Click to sidebar")
    public NavigationPage clickToSidebar(String sideBarName){
        clickToElement(sideBar, sideBarName);
        return this;
    }

    @Step("Click to hidden space")
    public NavigationPage clickToHiddenSpace(){
        clickToElement(hiddenSpace);
        return this;
    }

    @Step("Click to create list submit")
    public NavigationPage clickToCreateListBtn(){
        clickToElement(btnCreateList);
        return this;
    }

    @Step("click to search field")
    public NavigationPage clickToSearchField(){
        clickToElement(fieldSearch);
        return this;
    }

    @Step("enter list name ")
    public NavigationPage enterListName(String listName){
        sendKeyToElement(fieldList, listName);
        return this;
    }

    @Step("get all space list from UI ")
    public List<SpaceModel> getAllSpaceListFromUI(){
        List<SpaceModel> spaceLists = new ArrayList<SpaceModel>();
        for(int i = 0; i < spaceList.size(); i++){
            SpaceModel spaceModel = new SpaceModel();
            spaceModel.setName(getTextElement(spaceList.get(i)));
            spaceLists.add(spaceModel);
        }
        return spaceLists;
    }
    @Step("verify create new space dialog displayed")
    public NavigationPage verifyCreateNewSpaceDialogDisplayed(){
        Assert.assertTrue(isControlDisplayed(createNewSpaceDialog));
        return this;
    }

    @Step("verify next button is disabled")
    public NavigationPage verifyNextButtonIsDisable(){
        Assert.assertFalse(isControlEnabled(nextBtn));
        return this;
    }
    @Step("verify next button is enabled")
    public NavigationPage verifyNextButtonIsEnabled(){
        Assert.assertTrue(isControlEnabled(nextBtn));
        return this;
    }
    @Step("verify content in menu space setting is correct")
    public NavigationPage verifyContentInMenuSpaceSettingIsCorrect(){
        for (String option: MENU_SPACE_SETTING) {
            Assert.assertTrue(isControlEnabled(txtOptionInSpaceSetting, option));
        }
        return this;
    }

    @Step("verify content in menu create new is correct ")
    public NavigationPage verifyContentInMenuCreateNewIsCorrect(){
        for (String option: MENU_CREATE_NEW) {
            Assert.assertTrue(isControlEnabled(txtOptionInSpaceSetting, option));
        }
        return this;
    }
    @Step("verify content in menu import is correct ")
    public NavigationPage verifyContentInMenuImportIsCorrect(){
        for (String option: MENU_IMPORT) {
            Assert.assertTrue(isControlEnabled(txtOptionInSpaceSetting, option));
        }
        return this;
    }

    @Step("verify content in menu more settings is correct ")
    public NavigationPage verifyContentMenuMoreSettingIsCorrect(){
        for (String option: MENU_MORE_SETTINGS) {
            Assert.assertTrue(isControlEnabled(txtOptionInSpaceSetting, option));
        }
        return this;
    }

    @Step("verify content in menu template is correct ")
    public NavigationPage verifyContentInMenuTemplateIsCorrect(){
        for (String option: MENU_TEMPLATE) {
            Assert.assertTrue(isControlEnabled(txtOptionInSpaceSetting, option));
        }
        return this;
    }
    @Step("verify space colour picker displayed")
    public NavigationPage verifySpaceColorPickerDisplayed(){
        Assert.assertTrue(isControlEnabled(spaceColorPicker));
        return this;
    }

    @Step("verify edit space name dialog displayed")
    public NavigationPage verifyEditSpaceNameDialogDisplayed(){
        Assert.assertTrue(isControlEnabled(editSpaceNameDialog));
        return this;
    }

    @Step("verify space name displayed")
    public NavigationPage verifySpaceNameDisplayed(String nameSpace){
        Assert.assertTrue(isControlEnabled(spaceName, nameSpace));
        return this;
    }
    @Step("verify space name Undisplayed")
    public NavigationPage verifySpaceNameUndisplayed(String nameSpace){
        Assert.assertTrue(isControlUnDisplayed(spaceName, nameSpace));
        return this;
    }
    @Step("verify space name displayed in hidden space list")
    public NavigationPage verifySpaceNameDisplayedInHiddenSpace(String nameSpace){
        Assert.assertTrue(isControlEnabled(spaceName, nameSpace));
        return this;
    }

    @Step("verify warning message show up when entering same name")
    public NavigationPage verifyWarningMessageShowUpWhenEnteringSameName(){
        Assert.assertTrue(isControlEnabled(warningMessage, SAME_NAME));
        return this;
    }
    @Step("verify 'create folder' dialog displayed")
    public NavigationPage verifyCreateFolderDialogDisplayed(){
        Assert.assertTrue(isControlEnabled(createFolderDialog));
        return this;
    }
    @Step("verify warning message required folder name")
    public NavigationPage verifyWarningMessageRequiredFolderName() throws InterruptedException {
        Assert.assertTrue(isControlEnabled(warningMessage, FOLDER_NAME_REQUIRED));
        return this;
    }

    @Step("verify folder Name displayed and linked to Space")
    public NavigationPage verifyFolderNameDisplayedAndLinkedToSpace(String nameFolder) throws InterruptedException {
        waitForElementVisible(txtFolderName, nameFolder);
        Assert.assertTrue(isControlEnabled(txtFolderName, nameFolder));
        return this;
    }
    @Step("verify space name added to favorite sidebar")
    public NavigationPage verifySpaceAddedToFavoriteSideBar(String spaceName) throws InterruptedException {
        Assert.assertTrue(isControlEnabled(spaceNameInSideBar, spaceName));
        return this;
    }

    @Step("verify that create list dialog displayed")
    public NavigationPage verifyCreateListDialogDisplayed() throws InterruptedException {
        Assert.assertTrue(isControlEnabled(createListDialog));
        return this;
    }

    @Step("verify list displayed")
    public NavigationPage verifyListDisplayed(String listName) throws InterruptedException {
        waitForElementVisible(txtList, listName);
        Assert.assertTrue(isControlEnabled(txtList, listName));
        return this;
    }

}
