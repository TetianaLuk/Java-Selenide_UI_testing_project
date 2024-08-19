package pageobjects;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import utils.WebElementUtils;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class SkarbHomePage {

    SelenideElement selectWebSiteLanguageButton = $(byXpath("//i[@class='fa fa-language fa-lg']"));
    SelenideElement selectUkrLanguageButton = $("a[data-lang='uk']");
    SelenideElement createNewUserButton = $(".fa.fa-user-plus.fa-3x.text-dark-red");
    SelenideElement signInButtonOnHomePage = $(".fa.fa-sign-in.fa-3x.text-dark-red");
    SelenideElement navigateToHomePageButton = $("h4[class='text-dark-red']");
    SelenideElement tasksDropdown = $("h5[id='tasksDropdown'] span");
    SelenideElement createTaskForVolunteerButton = $(byXpath("//a[contains(text(),'Створити завдання для волонтера')]"));
    SelenideElement navigateToUserProfilePageButton = $(".fa.fa-user-circle-o.fa-3x.text-dark-red");

    public SkarbHomePage clickOnSelectWebSiteLanguage() {
        WebElementUtils.clickOnButton(selectWebSiteLanguageButton);
        return this;
    }

    public SkarbHomePage clickOnSelectUkrLanguage() {
        selectUkrLanguageButton.click();
        return this;
    }

    @Step("Click on 'Create a new user' button")
    public SkarbRegistrationPage clickOnCreateNewUser() {
        WebElementUtils.clickOnButton(createNewUserButton);
        return page(SkarbRegistrationPage.class);
    }

    @Step("Click on 'Sign in' button on the Skarb home page")
    public LoginPage clickOnSignInButtonOnHomePage() {
        WebElementUtils.clickOnButton(signInButtonOnHomePage);
        return page(LoginPage.class);
    }

    public void clickOnNavigateToHomePageButton() {
        WebElementUtils.clickOnButton(navigateToHomePageButton);
    }

    public void clickOnTasksDropdown() {
        WebElementUtils.clickOnButton(tasksDropdown);
    }

    @Step("After user is logged in, click on task dropdown and then click on 'Create task for volunteer' button")
    public void clickOnCreateTaskForVolunteerButton() {
        WebElementUtils.clickOnButton(createTaskForVolunteerButton);
    }

    public void clickOnNavigateToUserProfilePageButton() {
        WebElementUtils.clickOnButton(navigateToUserProfilePageButton);
    }

    public SelenideElement getNavigateToHomePageButton() {
        return navigateToHomePageButton;
    }

    public SelenideElement getTasksDropdown() {
        return tasksDropdown;
    }

    public SelenideElement getCreateTaskForVolunteerButton() {
        return createTaskForVolunteerButton;
    }

    public SelenideElement getNavigateToUserProfilePageButton() {
        return navigateToUserProfilePageButton;
    }

    public SelenideElement getSelectWebSiteLanguageButton() {
        return selectWebSiteLanguageButton;
    }

    public SelenideElement getSelectUkrLanguageButton() {
        return selectUkrLanguageButton;
    }

    public SelenideElement getCreateNewUserButton() {
        return createNewUserButton;
    }
}
