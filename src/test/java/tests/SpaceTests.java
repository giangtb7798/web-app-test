package tests;

import base.AbstractWebTest;
import models.SpaceModel;
import pagesobject.LandingPage;
import pagesobject.LoginPage;
import pagesobject.NavigationPage;
import microservices.models.spacemodels.SpaceInput;
import microservices.models.spacemodels.SpaceResponse;
import microservices.models.spacemodels.SpacesResponseList;
import microservices.steps.SpaceSteps;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;

import static constants.ClickUpMessage.*;

public class SpaceTests extends AbstractWebTest {
    SpaceResponse spaceResponse;
    SpaceSteps spaceSteps = new SpaceSteps();
    String name = "company";

    @BeforeMethod
    public void login() throws IOException {
        String email = "ntruonggiangtb98@gmail.com";
        String password = "07071998Gg";
        SpaceInput spaceInput = new SpaceInput(name);
        NavigationPage navigationPage = new NavigationPage();

        spaceResponse = (SpaceResponse) spaceSteps.when_createSpace(spaceInput)
                .validateResponse(HttpURLConnection.HTTP_OK)
                .saveResponseObject(SpaceResponse.class);
        navigationPage.refreshCurrentPage();

        new LoginPage()
                .goToLoginPage()
                .enterEmailAddress(email)
                .enterPassword(password)
                .clickToSubmitBtn();
    }
    @Test(description = "verify dialog when click to new space")
    public void verify_dialog_when_click_to_new_space(){
        String spaceName = "!@#";
        NavigationPage navigationPage = new NavigationPage();
        navigationPage
                .clickToNewSpaceBtn()
                .verifyCreateNewSpaceDialogDisplayed()
                .verifyNextButtonIsDisable()
                .enterSpaceName(spaceName)
                .verifyNextButtonIsEnabled();
    }
    @Test(description = "verify that create new space successfully")
    public void verify_create_new_space_successfully() throws IOException {
        String spaceName = "company";
        NavigationPage navigationPage = new NavigationPage();
        LandingPage landingPage = new LandingPage();

        navigationPage
                .clickToNewSpaceBtn()
                .enterSpaceName(spaceName)
                .clickToLastPaginationBtn()
                .clickToCreateSpaceBtn();

        landingPage
                .verifySpaceCreatedSuccessfully();

        //clean data
        SpaceSteps spaceSteps = new SpaceSteps();
        SpacesResponseList spacesResponseLists = (SpacesResponseList) spaceSteps
                .when_getAllSpace()
                .validateResponse(HttpURLConnection.HTTP_OK)
                .saveResponseObject(SpacesResponseList.class);
        List<SpaceResponse> spaceResponses = spacesResponseLists.getSpaces();
        spaceSteps
                .when_deleteSpace(spaceResponses.get(spaceResponses.size() - 1).getId())
                .validateResponse(HttpURLConnection.HTTP_OK);
    }

    @Test(description = "verify content in space setting correctly")
    public void verify_content_in_space_setting_correctly() throws IOException {
        SpaceSteps spaceSteps = new SpaceSteps();
        String name = "do not edit";
        SpaceInput spaceInput = new SpaceInput(name);
        NavigationPage navigationPage = new NavigationPage();

        navigationPage
                .clickToSpaceSettingBtn(name)
                .verifyContentInMenuSpaceSettingIsCorrect()
                .clickToOptionInSpaceSetting(MENU_SPACE_SETTING[0])
                .verifyContentInMenuCreateNewIsCorrect()
                .clickToOptionInSpaceSetting(MENU_CREATE_NEW[5])
                .verifyContentInMenuImportIsCorrect()
                .refreshCurrentPage();

        navigationPage
                .clickToSpaceSettingBtn(name)
                .clickToOptionInSpaceSetting(MENU_SPACE_SETTING[2])
                .verifySpaceColorPickerDisplayed()
                .refreshCurrentPage();

        navigationPage
                .clickToSpaceSettingBtn(name)
                .clickToOptionInSpaceSetting(MENU_SPACE_SETTING[7])
                .verifyContentInMenuTemplateIsCorrect()
                .refreshCurrentPage();

        navigationPage
                .clickToSpaceSettingBtn(name)
                .clickToOptionInSpaceSetting(MENU_SPACE_SETTING[8])
                .verifyContentMenuMoreSettingIsCorrect();
    }
    @Test(description = "verify rename space function")
    public void verify_rename_space_function() throws IOException {
        NavigationPage navigationPage = new NavigationPage();

        navigationPage
                .clickToSpaceSettingBtn(name)
                .clickToOptionInSpaceSetting(MENU_SPACE_SETTING[1])
                .verifyEditSpaceNameDialogDisplayed()
                .clickToSaveBtn()
                .verifySpaceNameDisplayed(name);

        String sameName = "do not edit";
        navigationPage
                .clickToSpaceSettingBtn(name)
                .clickToOptionInSpaceSetting(MENU_SPACE_SETTING[1])
                .enterSpaceName(sameName)
                .clickToSaveBtn()
                .verifyWarningMessageShowUpWhenEnteringSameName();

        String nameEdited = "companyEdited";
        navigationPage
                .enterSpaceName(nameEdited)
                .clickToSaveBtn()
                .verifySpaceNameDisplayed(nameEdited);
    }
    @Test(description = "add to favorite list")
    public void verify_add_to_favorite_list_successfully() throws IOException, InterruptedException {
        NavigationPage navigationPage = new NavigationPage();

        navigationPage
                .clickToSpaceSettingBtn(name)
                .clickToOptionInSpaceSetting(MENU_SPACE_SETTING[5])
                .refreshCurrentPage();

        navigationPage
                .clickToSidebar(SIDE_BAR[0])
                .verifySpaceAddedToFavoriteSideBar(name);
    }
    @Test(description = "hide in my side bar")
    public void verify_hide_in_my_side_bar_successfully() throws IOException, InterruptedException {
        NavigationPage navigationPage = new NavigationPage();

        navigationPage
                .clickToSpaceSettingBtn(name)
                .clickToOptionInSpaceSetting(MENU_SPACE_SETTING[6])
                .verifySpaceNameUndisplayed(name)
                .clickToHiddenSpace()
                .verifySpaceNameDisplayedInHiddenSpace(name);
    }
    @Test(description = "verify all space displayed correctly")
    public void verify_all_space_displayed_correctly() throws IOException, InterruptedException {
        NavigationPage navigationPage = new NavigationPage();
        SpaceSteps spaceSteps = new SpaceSteps();

        SpacesResponseList spacesResponseLists = (SpacesResponseList) spaceSteps
                .when_getAllSpace()
                .validateResponse(HttpURLConnection.HTTP_OK)
                .saveResponseObject(SpacesResponseList.class);

        List<SpaceModel> spaceModels = navigationPage
                .getAllSpaceListFromUI();
        navigationPage
                .verifySpaceListDisplayedCorrectly(spacesResponseLists.getSpaces(), spaceModels);
    }
    @AfterMethod
    public void cleanData() throws IOException {

        //clean data
        spaceSteps
                .when_deleteSpace(spaceResponse.getId())
                .validateResponse(HttpURLConnection.HTTP_OK);
    }
}
