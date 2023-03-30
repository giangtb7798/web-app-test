package pagesobject;

import core.WebApi;
import io.qameta.allure.Step;
import models.TaskModel;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Thread.sleep;

public class TaskPage extends WebApi {
    @FindBy(xpath = "//div[contains(text(),'nameList')]//parent::a")
    private WebElement taskName;
    @FindBy(xpath = "//input[@data-test=\"task-row-new__input\"]")
    private WebElement fieldTaskName;
    @FindBy(xpath = "//div[contains(text(),'Save')]")
    private WebElement saveTask;
    @FindBy(xpath = "//span[contains(text(),'taskName')]//parent::div//div[@cutooltip=\"Create subtask\"]")
    private WebElement subTaskBtn;
    @FindBy(xpath = "//input[@placeholder=\"New subtask\"]")
    private WebElement fieldSubTaskName;
    @FindBy(xpath = "//cu-task-list-header-field[@sorttitle=\"Sort by Task Name\"]")
    private WebElement sortDropdown;

    @FindBy(xpath = "//cu-dropdown-list-item[@data-id=\"sortGroup\"]")
    private WebElement taskNameDropdown;

    @FindBy(xpath = "//cu-list-view-loader")
    private WebElement pageLoader;
    @FindBy(xpath = "//div[contains(text(),'Board')]")
    private WebElement board;
    @FindBy(xpath = "//input[@placeholder=\"Search tasks...\"]")
    private WebElement fieldSearch;

    @FindBy(xpath = "//cu-task-row//span[@class=\"cu-task-row-main__link-text-inner\"]")
    private List<WebElement> txtTaskNameList;
    private String txtTaskName = "//span[contains(text(),'%s')]";

    @Step("click to task")
    public TaskPage clickToTask(){
        clickToElement(taskName);
        return this;
    }
    @Step("click to board")
    public TaskPage clickToBoard(){
        clickToElement(board);
        return this;
    }
    @Step("enter keyword to search")
    public TaskPage enterKeywordToSearch(String keyword) {
        sendKeyToElement(fieldSearch, keyword);
        return this;
    }
    @Step("click to task")
    public TaskPage clickToFieldTaskName(){
        clickToElement(fieldTaskName);
        return this;
    }
    @Step("enter task name")
    public TaskPage enterTaskName(String taskName){
        clearTextElement(fieldTaskName);
        sendKeyToElement(fieldTaskName, taskName);
        return this;
    }
    @Step("click to save task")
    public TaskPage clickToSaveTask(){
        clickToElement(saveTask);
        return this;
    }
    @Step("click to create subtask")
    public TaskPage clickToCreateSubtask(){
        clickToElement(subTaskBtn);
        return this;
    }
    @Step("enter subtask name")
    public TaskPage enterSubtaskName(String subTask){
        sendKeyToElement(fieldSubTaskName, subTask);
        return this;
    }
    @Step("click to sort dropdown")
    public TaskPage clickToSortDropDown(){
        clickToElement(sortDropdown);
        return this;
    }
    @Step("click to sort dropdown by task name")
    public TaskPage clickToSortDropDownByTaskName(){
        clickToElement(taskNameDropdown);
        return this;
    }
    @Step("get all task name")
    public List<TaskModel> getAllTaskName() throws InterruptedException {
        waitForElementVisible(pageLoader);
        List<TaskModel> task = new ArrayList<>();
        for (int i = 0; i < txtTaskNameList.size(); i++) {
            TaskModel taskModel = new TaskModel();
            taskModel.setName(getTextElement(txtTaskNameList.get(i)));
            task.add(taskModel);
        }
        return task;
    }
    @Step("verify task name created successfully")
    public TaskPage verifyTaskNameCreatedSuccessfully(String taskName){
        Assert.assertTrue(isControlDisplayed(txtTaskName, taskName));
        return this;
    }
    @Step("verify task name is sorted in nature ")
    public TaskPage verifyTaskNameIsSortedInNature(List<TaskModel> taskModel){
        Assert.assertTrue(taskModel.stream().sorted(Comparator.comparing(TaskModel::getName)).collect(Collectors.toList()).equals(taskModel));
        return this;
    }
    @Step("verify search results include keyword ")
    public TaskPage verifySearchResultIncludeKeyword(List<TaskModel> taskModel, String keyword){
        taskModel.stream().anyMatch(s -> s.getName().contains(keyword));
        return this;
    }
}
