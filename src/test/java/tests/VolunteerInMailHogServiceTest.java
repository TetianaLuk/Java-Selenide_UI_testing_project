package tests;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import models.Volunteer;
import org.instancio.Instancio;
import org.instancio.junit.InstancioExtension;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import pageobjects.MailHogPage;
import pageobjects.SkarbHomePage;
import pageobjects.VolunteerRegistrationPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.SeverityLevel.CRITICAL;
import static org.junit.jupiter.api.Assertions.*;
import static utils.DriverSetUp.chromeSetUp;
import static utils.ScreenshotMethods.attachScreenshotToAllureReport;

@ExtendWith(InstancioExtension.class)

public class VolunteerInMailHogServiceTest {

    private static final Logger logger = LogManager.getLogger(VolunteerInMailHogServiceTest.class);

    VolunteerRegistrationPage objVolunteerRegistrationPage = page(VolunteerRegistrationPage.class);
    MailHogPage objMailHogPage = page(MailHogPage.class);

    @BeforeAll
    public static void setUpAndNavigateToInitialWebPage() throws IOException {
        Selenide.open(utils.ReadPropertiesFileMethod.readHomePageUrlInConfigFile());
        chromeSetUp();
        SkarbHomePage objSkarbHomePage = page(SkarbHomePage.class);
        objSkarbHomePage.clickOnSelectWebSiteLanguage()
                        .clickOnSelectUkrLanguage()
                        .clickOnCreateNewUser()
                        .clickOnCreateNewVolunteer();
    }

    @Test
    @DisplayName("Confirm volunteer registration in MailHog service")
    @Description("This test attempts to register new volunteer and then confirm his email in the MailHog service.")
    @Severity(CRITICAL)
    public void confirmVolunteerRegistrationInMailHogService() throws IOException {
        Volunteer user = Instancio.create(Volunteer.getUserModel());

        logger.info("confirmVolunteerRegistrationInMailHogService test started. Entering user data.");

        objVolunteerRegistrationPage.enterFirstName(user.getFirstName())
                                .enterLastName(user.getLastName())
                                .enterEmail(user.getEmail())
                                .enterPhoneNumber(user.getPhoneNumber())
                                .enterPassword(user.getPassword())
                                .enterConfirmPassword(user.getPassword())
                                .enterAbout(user.getAbout())
                                .selectCategory(user.getSelectCategory())
                                .clickOnSubmitButton();

        objVolunteerRegistrationPage.verifySuccessfulRegistrationMassage();
        logger.info("Registration of user is successful. Message about successful registration is visible.");
        logger.debug("confirmVolunteerRegistrationInMailHogService test started. User data entered. Registration of test user is successful. User email: {}, user password: {}.", user.getEmail(), user.getPassword());

        Selenide.open(objMailHogPage.getMailHogURL());
        objMailHogPage.checkIfSuccessfullyNavigatedToMailHogWebpage();
        logger.info("A new tab created. Navigated to the MailHog website.");
        logger.debug("A new tab created. Navigated to the MailHog website.");

        SelenideElement emailElement = $(byXpath("//div[contains(text(),'" + user.getEmail() + "')]"));
        emailElement.shouldBe(visible,Duration.ofSeconds(60));

        objMailHogPage.verifyLetterAboutSuccessfulRegistrationIsPresentOnWebPage(user.getEmail());
        logger.info("Letter about successful registration is present in the MailHog.");
        logger.debug("Letter about successful registration of email {} is present in the MailHog.", user.getEmail());

        utils.WebElementUtils.clickOnButton(emailElement);

        objMailHogPage.getLinkForEmailConfirmation().shouldBe(visible);
        logger.info("Link for confirmation of email is visible in the letter.");
        logger.debug("Link for confirmation of email {} is visible in the letter.", user.getEmail());
        objMailHogPage.clickOnLinkForEmailConfirmation();

        List<String> tabList = new ArrayList<>(getWebDriver().getWindowHandles());
        int tabListSize = tabList.size();
        switchTo().window(tabList.get(tabListSize-1));
        assertTrue(getWebDriver().getCurrentUrl().contains("registration/confirm"), "URL should contain text 'registration/confirm'");

        objMailHogPage.verifySuccessfulConfirmationOfEmail();
        attachScreenshotToAllureReport("Screenshot");
        logger.info("Message about successful confirming of the email is visible. Test completed successfully");
        logger.debug("Message about successful confirming of the email is visible. confirmVolunteerRegistrationInMailHogService test completed successfully");

    }
}

