package pageobjects;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import utils.WebElementUtils;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class SkarbRegistrationPage {

    SelenideElement createVolunteerButton = $("button[class='btn btn-primary btn-lg btn-block']");
    SelenideElement createPartnerButton = $("button[class='btn btn-success btn-lg btn-block']");
    SelenideElement createNGO_Button = $("button[class='btn btn-warning btn-lg btn-block']");

    @Step("Click on 'Volunteer' button")
    public VolunteerRegistrationPage clickOnCreateNewVolunteer() {
        WebElementUtils.clickOnButton(createVolunteerButton);
        return page(VolunteerRegistrationPage.class);
    }

    @Step("Click on 'Partner' button")
    public PartnerRegistrationPage clickOnCreateNewPartner() {
        WebElementUtils.clickOnButton(createPartnerButton);
        return page(PartnerRegistrationPage.class);
    }

    @Step("Click on 'Nongovernmental organization' button")
    public NGO_RegistrationPage clickOnCreateNewNGO() {
        WebElementUtils.clickOnButton(createNGO_Button);
        return page(NGO_RegistrationPage.class);
    }

    public SelenideElement getCreateVolunteerButton() {
        return createVolunteerButton;
    }
}
