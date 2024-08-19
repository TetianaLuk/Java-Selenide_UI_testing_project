package pageobjects;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import utils.WebElementUtils;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class VolunteerProfilePage {

    SelenideElement userProfileButton = $(".fa.fa-user-circle-o.fa-3x.text-dark-red");
    SelenideElement signOutButton = $(".fa.fa-sign-out.fa-3x.text-dark-red");
    SelenideElement messageAboutSuccessfulLogout = $(byXpath("//p[@class='text-dark']"));

    @Step("Click on 'Sign out' button to log out from user profile.")
    public VolunteerProfilePage clickOnSignOutButton() {
        WebElementUtils.clickOnButton(signOutButton);
        return this;
    }

    public void checkMessageAboutSuccessfulLogout(){
        messageAboutSuccessfulLogout.shouldHave(exactText("Ви успішно вийшли з аккаунту."));
    }

    public SelenideElement getUserProfileButton() {
        return userProfileButton;
    }
}
