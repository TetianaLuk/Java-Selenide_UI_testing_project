package pageobjects;

import com.codeborne.selenide.SelenideElement;
import utils.WebElementUtils;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class NGO_ProfilePage {

    SelenideElement myTasksDropdown = $(byXpath("//span[contains(text(),'Мої задачі')]"));
    SelenideElement publishedTasksButton = $(byXpath("//span[contains(text(),'Опубліковані')]"));
    SelenideElement signOutButton = $(".fa.fa-sign-out.fa-3x.text-dark-red");
    SelenideElement messageAboutSuccessfulLogout = $(byXpath("//p[@class='text-dark']"));

    public void clickOnMyTasksDropdown() {
        WebElementUtils.clickOnButton(myTasksDropdown);
    }

    public void clickOnPublishedTasksButton() {
        WebElementUtils.clickOnButton(publishedTasksButton);
    }

    public NGO_ProfilePage clickOnSignOutButton() {
        WebElementUtils.clickOnButton(signOutButton);
        return this;
    }

    public void checkMessageAboutSuccessfulLogout(){
        messageAboutSuccessfulLogout.shouldHave(exactText("Ви успішно вийшли з аккаунту."));
    }

    public SelenideElement getMyTasksDropdown() {
        return myTasksDropdown;
    }

    public SelenideElement getPublishedTasksButton() {
        return publishedTasksButton;
    }

}
