package pageobjects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import dev.failsafe.internal.util.Assert;
import io.qameta.allure.Step;
import utils.WebElementUtils;

import java.io.IOException;
import java.util.Properties;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byPartialLinkText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.Condition.*;

public class MailHogPage {

    ElementsCollection listOfEmailsPresentOnPage = $$(".ng-binding.ng-scope");
    SelenideElement linkForEmailConfirmation = $(byPartialLinkText("https://skarb.foxmin"));
    SelenideElement finalSuccessMessage = $(".display-3.text-center");

    @Step("Click on link for user email confirmation")
    public void clickOnLinkForEmailConfirmation() {
        WebElementUtils.clickOnButton(linkForEmailConfirmation);
    }

    public String getMailHogURL () throws IOException {
        utils.ReadPropertiesFileMethod.readProperties("src/test/resources/testdata/config.properties");
        Properties properties = utils.ReadPropertiesFileMethod.getProperties();
        return properties.getProperty("URL_MailHog");
    }

    @Step("Check if successfully navigated to MailHog service webpage")
    public void checkIfSuccessfullyNavigatedToMailHogWebpage() {
        Assert.isTrue(getWebDriver().getCurrentUrl().contains("skarbmail.foxminded"), "URL should contain text 'skarbmail.foxminded'");
    }

    @Step("Verify that letter about successful registration of user is present in the MailHog service")
    public void verifyLetterAboutSuccessfulRegistrationIsPresentOnWebPage(String email) {
        ElementsCollection listOfElements = listOfEmailsPresentOnPage;
        int listOfElementsSize = listOfElements.size();
        for (int i=0; i<listOfElementsSize; i++) {
            if (listOfEmailsPresentOnPage.get(i).getText().equals(email)) {
                System.out.println("There is a letter about successful registration in the MailHog ");
                }
        }
        SelenideElement emailElement = $(byXpath("//div[contains(text(),'" + email + "')]"));
        emailElement.shouldBe(visible);
    }

    @Step("Verify message about successful confirmation of the email")
    public void verifySuccessfulConfirmationOfEmail(){
        finalSuccessMessage.shouldHave(exactText("Ваш email підтверджено!"));
        System.out.println("Message about successful confirmation of the email is visible");
    }

    public ElementsCollection getListOfEmailsPresentOnPage() {
        return listOfEmailsPresentOnPage;
    }

    public SelenideElement getLinkForEmailConfirmation() {
        return linkForEmailConfirmation;
    }

    public SelenideElement getFinalSuccessMessage() {
        return finalSuccessMessage;
    }
}
