package tests;

import base.AbstractWebTest;
import microservices.models.listmodels.FolderInput;
import microservices.models.listmodels.FolderResponse;
import microservices.steps.ListSteps;
import models.TaskModel;
import org.apache.commons.lang3.ArrayUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pagesobject.LoginPage;
import pagesobject.NavigationPage;
import pagesobject.TaskPage;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TaskTests extends AbstractWebTest {
    ListSteps listSteps = new ListSteps();
    @BeforeMethod
    public void logIn() throws Exception {
        String email = "ntruonggiangtb98@gmail.com";
        String password = "07071998Gg";
        new LoginPage()
                .goToLoginPage()
                .enterEmailAddress(email)
                .enterPassword(password)
                .clickToSubmitBtn();
    }

    @Test(description = "create task")
    public void create_task() throws IOException {
        String listName = "nameList";
        String taskName = "taskName";
        String spaceName = "do not edit";
        FolderInput folderInput = new FolderInput(listName);
        FolderResponse folderResponse = (FolderResponse) listSteps
                .when_createListInSpace(folderInput)
                .validateResponse(HttpURLConnection.HTTP_OK)
                .saveResponseObject(FolderResponse.class);

        NavigationPage navigationPage = new NavigationPage();
        TaskPage taskPage = new TaskPage();
        navigationPage.refreshCurrentPage();

        navigationPage
                .clickToSpace(spaceName);

        taskPage
                .clickToFieldTaskName()
                .enterTaskName(taskName)
                .clickToSaveTask()
                .verifyTaskNameCreatedSuccessfully(taskName);

        //delete list
        listSteps
                .when_deleteList(folderResponse.getId())
                .validateResponse(HttpURLConnection.HTTP_OK);
    }

    @Test(description = "Sort by task name")
    public void sort_by_task_name() throws IOException, InterruptedException {
        String listName = "nameList";
        String taskName = "taskName";
        String spaceName = "do not edit";

        NavigationPage navigationPage = new NavigationPage();
        TaskPage taskPage = new TaskPage();

        navigationPage
                .clickToSpace(spaceName);

        taskPage
                .clickToSortDropDown()
                .clickToSortDropDownByTaskName();

        List<TaskModel> taskList = taskPage.getAllTaskName();

        //verify that list is sorted in nature
        taskPage.verifyTaskNameIsSortedInNature(taskList);
    }
    @Test(description = "Verify search task works well")
    public void search_task_works_well() throws IOException, InterruptedException {
        String keyword = "da";
        String spaceName = "do not edit";

        NavigationPage navigationPage = new NavigationPage();
        TaskPage taskPage = new TaskPage();

        navigationPage
                .clickToSpace(spaceName);

        taskPage
                .clickToBoard()
                .enterKeywordToSearch(keyword);

        List<TaskModel> taskList = taskPage.getAllTaskName();

        taskPage.verifySearchResultIncludeKeyword(taskList, keyword);
    }
    @Test(description = "verify create subtask function")
    public void verifyCreateSubtaskFunction() throws IOException, InterruptedException {
        String listName = "nameList";
        String taskName = "taskName";
        String subtaskName = "subtaskName";
        String spaceName = "do not edit";
        FolderInput folderInput = new FolderInput(listName);
        FolderResponse folderResponse = (FolderResponse) listSteps
                .when_createListInSpace(folderInput)
                .validateResponse(HttpURLConnection.HTTP_OK)
                .saveResponseObject(FolderResponse.class);

        NavigationPage navigationPage = new NavigationPage();
        TaskPage taskPage = new TaskPage();
        navigationPage.refreshCurrentPage();

        navigationPage
                .clickToSpace(spaceName);

        taskPage
                .clickToFieldTaskName()
                .enterTaskName(taskName)
                .clickToSaveTask()
                .clickToCreateSubtask()
                .enterSubtaskName(subtaskName)
                .clickToSaveTask();

        //delete list
        listSteps
                .when_deleteList(folderResponse.getId())
                .validateResponse(HttpURLConnection.HTTP_OK);
    }
}
