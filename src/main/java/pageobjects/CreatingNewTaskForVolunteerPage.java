package pageobjects;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import utils.WebElementUtils;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static utils.ScreenshotMethods.attachScreenshotToAllureReport;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CreatingNewTaskForVolunteerPage {

    SelenideElement taskNameInput = $(by("name","name"));
    SelenideElement selectCategoryField = $("#categoryIds");
    SelenideElement deadlineInput = $(by("name","deadline"));
    SelenideElement taskDescriptionInput = $(by("name","description"));
    SelenideElement expectedOutcomeInput = $(by("name","expectedOutcome"));
    SelenideElement volunteerBenefitInput = $(by("name","benefit"));
    SelenideElement requirementsToVolunteerInput = $("input[id='requirements[0]']");
    SelenideElement interviewRequiredCheckBox = $(by("name","interviewRequired"));
    SelenideElement savedMoneyInput = $(by("name","savedMoney"));
    SelenideElement firstWorkStageNameInput = $(byXpath("(//input[@name='stages[0].name'])[1]"));
    SelenideElement firstWorkStageDurationInput = $(byXpath("(//input[@name='stages[0].duration'])[1]"));
    SelenideElement firstWorkStageDescriptionInput = $("textarea[name='stages[0].description']");
    SelenideElement secondWorkStageNameInput = $(byXpath("(//input[@name='stages[1].name'])[1]"));
    SelenideElement secondWorkStageDurationInput = $(byXpath("(//input[@name='stages[1].duration'])[1]"));
    SelenideElement secondWorkStageDescriptionInput = $("textarea[name='stages[1].description']");
    SelenideElement createAndPublishTaskButton = $("button[value='PUBLISHED']");
    SelenideElement successMessage = $(byXpath("//h4[contains(text(),'Нове завдання було успішно зареєстровано та опублі')]"));
    SelenideElement taskDescriptionInPublishedTask = $(byXpath("//div[@name='task-description']"));
    SelenideElement volunteerBenefitInPublishedTask = $(byXpath("//div[@name='volunteer-benefit']"));

    public CreatingNewTaskForVolunteerPage enterTaskName (String taskName) {
        WebElementUtils.enterText(taskNameInput, taskName);
        return this;
    }

    public CreatingNewTaskForVolunteerPage selectCategory (String category) {
        WebElementUtils.selectCategory(selectCategoryField, category);
        return this;
    }

    public CreatingNewTaskForVolunteerPage setTaskDeadline(int taskDuration){
        LocalDate deadLine = LocalDate.now().plusDays(taskDuration);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String text = deadLine.format(formatter);
        WebElementUtils.enterText(deadlineInput, text);
        return this;
    }

    public CreatingNewTaskForVolunteerPage enterTaskDescription (String taskDescription) {
        WebElementUtils.enterText(taskDescriptionInput, taskDescription);
        return this;
    }

    public CreatingNewTaskForVolunteerPage enterExpectedOutcome (String expectedOutcome) {
        WebElementUtils.enterText(expectedOutcomeInput, expectedOutcome);
        return this;
    }

    public CreatingNewTaskForVolunteerPage enterVolunteerBenefit (String volunteerBenefit) {
        WebElementUtils.enterText(volunteerBenefitInput, volunteerBenefit);
        return this;
    }

    public CreatingNewTaskForVolunteerPage enterRequirementsToVolunteer (String requirementsToVolunteer) {
        WebElementUtils.enterText(requirementsToVolunteerInput, requirementsToVolunteer);
        return this;
    }

    public CreatingNewTaskForVolunteerPage clickOnInterviewRequiredCheckBox () {
        WebElementUtils.clickOnButton(interviewRequiredCheckBox);
        return this;
    }

    public CreatingNewTaskForVolunteerPage enterSavedMoney (String savedMoney) {
        WebElementUtils.enterText(savedMoneyInput, savedMoney);
        return this;
    }

    public CreatingNewTaskForVolunteerPage enterFirstWorkStageName (String firstWorkStageName) {
        firstWorkStageNameInput.clear();
        WebElementUtils.enterText(firstWorkStageNameInput, firstWorkStageName);
        return this;
    }

    public CreatingNewTaskForVolunteerPage enterFirstWorkStageDuration (String firstWorkStageDuration) {
        firstWorkStageDurationInput.clear();
        WebElementUtils.enterText(firstWorkStageDurationInput, firstWorkStageDuration);
        return this;
    }

    public CreatingNewTaskForVolunteerPage enterFirstWorkStageDescription (String firstWorkStageDescription) {
        firstWorkStageDescriptionInput.clear();
        WebElementUtils.enterText(firstWorkStageDescriptionInput, firstWorkStageDescription);
        return this;
    }

    public CreatingNewTaskForVolunteerPage enterSecondWorkStageName (String secondWorkStageName) {
        secondWorkStageNameInput.clear();
        WebElementUtils.enterText(secondWorkStageNameInput, secondWorkStageName);
        return this;
    }

    public CreatingNewTaskForVolunteerPage enterSecondWorkStageDuration (String secondWorkStageDuration) {
        secondWorkStageDurationInput.clear();
        WebElementUtils.enterText(secondWorkStageDurationInput, secondWorkStageDuration);
        return this;
    }

    public CreatingNewTaskForVolunteerPage enterSecondWorkStageDescription (String secondWorkStageDescription) {
        secondWorkStageDescriptionInput.clear();
        WebElementUtils.enterText(secondWorkStageDescriptionInput, secondWorkStageDescription);
        return this;
    }

    @Step("Enter task data and click on 'Create and publish task' button")
    public void clickOnCreateAndPublishTaskButton() {
        WebElementUtils.clickOnButton(createAndPublishTaskButton);
    }

    @Step("Verify message about successful creating and publishing of the task")
    public void verifySuccessfulCreatingAndPublishingOfTaskMassage(){
        successMessage.shouldHave(exactText("Нове завдання було успішно зареєстровано та опубліковано."));
        attachScreenshotToAllureReport("Message about successful creating and publishing of the task");
    }

    public void checkContentOfPublishedTask(String taskName, String taskDescription, String volunteerBenefit){
        $(byXpath("//span[normalize-space()='" + taskName + "']")).shouldBe(visible);
        taskDescriptionInPublishedTask.shouldHave(exactText(taskDescription));
        volunteerBenefitInPublishedTask.shouldHave(exactText(volunteerBenefit));
    }

    public SelenideElement getSelectCategoryField() {
        return selectCategoryField;
    }

    public SelenideElement getSuccessMessage() {
        return successMessage;
    }
}
