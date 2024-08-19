package tests;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.provider.CsvFileSource;
import pageobjects.*;
import models.TaskForVolunteer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.Properties;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.SeverityLevel.CRITICAL;
import static org.junit.jupiter.api.Assertions.*;
import static utils.DriverSetUp.chromeSetUp;

public class NGOtasksForVolunteerParameterizedTest {

    static SkarbHomePage objSkarbHomePage = page(SkarbHomePage.class);
    CreatingNewTaskForVolunteerPage objCreatingNewTaskForVolunteerPage = page(CreatingNewTaskForVolunteerPage.class);

    private static final Logger logger = LogManager.getLogger(NGOtasksForVolunteerParameterizedTest.class);

    @BeforeAll
    public static void setUpAndLogIn() throws IOException {
        Selenide.open(utils.ReadPropertiesFileMethod.readHomePageUrlInConfigFile());
        chromeSetUp();
        objSkarbHomePage.clickOnSelectWebSiteLanguage()
                        .clickOnSelectUkrLanguage()
                        .clickOnSignInButtonOnHomePage();

        utils.ReadPropertiesFileMethod.readProperties("src/test/resources/testdata/config.properties");
        Properties properties = utils.ReadPropertiesFileMethod.getProperties();
        LoginPage objLoginPage = page(LoginPage.class);
        objLoginPage.checkLoadingOfLoginPage()
                    .enterLogin(properties.getProperty("login_NGOuser"))
                    .enterPassword(properties.getProperty("password_NGOuser"))
                    .clickOnSignInButton();

        logger.info("Set up the driver, navigated to initial webpage.User logged in.");
    }

    @ParameterizedTest
    @MethodSource("testdata.TaskForVolunteerParameterizedTestData#getTaskForVolunteerParameterizedTestData")
    @DisplayName("Create new task for Volunteer with parameterized test and @MethodSource")
    @Description("This test attempts to create new task for volunteer using valid data from @MethodSource.")
    @Severity(CRITICAL)
    public void createTaskForVolunteerWithMethodSource(TaskForVolunteer task) {

        objSkarbHomePage.getNavigateToHomePageButton().shouldBe(enabled);
        objSkarbHomePage.clickOnNavigateToHomePageButton();

        logger.info("createTaskForVolunteer test started.");

        objSkarbHomePage.getTasksDropdown().shouldBe(enabled);
        objSkarbHomePage.clickOnTasksDropdown();

        objSkarbHomePage.getCreateTaskForVolunteerButton().shouldBe(enabled);
        objSkarbHomePage.clickOnCreateTaskForVolunteerButton();

        assertTrue(getWebDriver().getCurrentUrl().contains("tasks/register/volunteer"), "URL should contain text 'tasks/register/volunteer'");

        logger.info("Entering task data.");

        objCreatingNewTaskForVolunteerPage.enterTaskName(task.getTaskName())
                                            .selectCategory(task.getSelectCategory())
                                            .setTaskDeadline(5)
                                            .enterTaskDescription(task.getTaskDescription())
                                            .enterExpectedOutcome(task.getExpectedOutcome())
                                            .enterVolunteerBenefit(task.getVolunteerBenefit())
                                            .enterRequirementsToVolunteer(task.getRequirementsToVolunteer())
                                            .clickOnInterviewRequiredCheckBox()
                                            .enterSavedMoney(task.getSavedMoney())
                                            .enterFirstWorkStageName(task.getFirstWorkStageName())
                                            .enterFirstWorkStageDuration(task.getFirstWorkStageDuration())
                                            .enterFirstWorkStageDescription(task.getFirstWorkStageDescription())
                                            .enterSecondWorkStageName(task.getSecondWorkStageName())
                                            .enterSecondWorkStageDuration(task.getSecondWorkStageDuration())
                                            .enterSecondWorkStageDescription(task.getSecondWorkStageDescription())
                                            .clickOnCreateAndPublishTaskButton();


        objCreatingNewTaskForVolunteerPage.verifySuccessfulCreatingAndPublishingOfTaskMassage();
        logger.info("Task is created and published. Message about successful creating and publishing of the task is visible.");

        objCreatingNewTaskForVolunteerPage.checkContentOfPublishedTask(task.getTaskName(), task.getTaskDescription(), task.getVolunteerBenefit());
        logger.info("Created task is present in the list of published tasks and contains given data.");
    }

    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/testdata/dataForTasksForVolunteer.csv", numLinesToSkip = 1)
    @DisplayName("Create new task for Volunteer with parameterized test and @CsvFileSource")
    @Description("This test attempts to create new task for volunteer using valid data from @CsvFileSource.")
    @Severity(CRITICAL)
    public void createTaskForVolunteerWithCsvFileSource(String taskName, String selectCategory, int numberOfDaysToTaskDeadline, String taskDescription, String expectedOutcome, String volunteerBenefit, String requirementsToVolunteer, String savedMoney, String firstWorkStageName, String firstWorkStageDuration, String firstWorkStageDescription, String secondWorkStageName, String secondWorkStageDuration, String secondWorkStageDescription) {

        objSkarbHomePage.getNavigateToHomePageButton().shouldBe(enabled);
        objSkarbHomePage.clickOnNavigateToHomePageButton();

        logger.info("createTaskForVolunteerWithCsvFileSource test started.");

        objSkarbHomePage.getTasksDropdown().shouldBe(enabled);
        objSkarbHomePage.clickOnTasksDropdown();

        objSkarbHomePage.getCreateTaskForVolunteerButton().shouldBe(enabled);
        objSkarbHomePage.clickOnCreateTaskForVolunteerButton();

        assertTrue(getWebDriver().getCurrentUrl().contains("tasks/register/volunteer"), "URL should contain text 'tasks/register/volunteer'");

        logger.info("Entering task data.");

        objCreatingNewTaskForVolunteerPage.enterTaskName(taskName)
                .selectCategory(selectCategory)
                .setTaskDeadline(numberOfDaysToTaskDeadline)
                .enterTaskDescription(taskDescription)
                .enterExpectedOutcome(expectedOutcome)
                .enterVolunteerBenefit(volunteerBenefit)
                .enterRequirementsToVolunteer(requirementsToVolunteer)
                .clickOnInterviewRequiredCheckBox()
                .enterSavedMoney(savedMoney)
                .enterFirstWorkStageName(firstWorkStageName)
                .enterFirstWorkStageDuration(firstWorkStageDuration)
                .enterFirstWorkStageDescription(firstWorkStageDescription)
                .enterSecondWorkStageName(secondWorkStageName)
                .enterSecondWorkStageDuration(secondWorkStageDuration)
                .enterSecondWorkStageDescription(secondWorkStageDescription)
                .clickOnCreateAndPublishTaskButton();

        objCreatingNewTaskForVolunteerPage.verifySuccessfulCreatingAndPublishingOfTaskMassage();
        logger.info("Task is created and published. Message about successful creating and publishing of the task is visible.");

        objCreatingNewTaskForVolunteerPage.checkContentOfPublishedTask(taskName, taskDescription, volunteerBenefit);
        logger.info("Created task is present in the list of published tasks and contains given data.");
    }

    @AfterAll
    public static void signOutAndTearDown() {
        NGO_ProfilePage objNGO_ProfilePage = page(NGO_ProfilePage.class);
        objNGO_ProfilePage.clickOnSignOutButton()
                          .checkMessageAboutSuccessfulLogout();
        logger.info("The user is logged out.");
    }
}
