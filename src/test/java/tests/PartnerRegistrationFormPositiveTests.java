package tests;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.Step;
import models.Partner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.instancio.Instancio;
import org.instancio.junit.InstancioExtension;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import pageobjects.PartnerRegistrationPage;
import pageobjects.SkarbHomePage;
import utils.WebElementUtils;
import java.io.IOException;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.SeverityLevel.CRITICAL;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static utils.DriverSetUp.chromeSetUp;

@ExtendWith(InstancioExtension.class)

public class PartnerRegistrationFormPositiveTests {

    PartnerRegistrationPage objPartnerRegistrationPage;

    private static final Logger logger = LogManager.getLogger(PartnerRegistrationFormPositiveTests.class);

    @BeforeAll
    public static void setUp() throws IOException {
        Selenide.open(utils.ReadPropertiesFileMethod.readHomePageUrlInConfigFile());
        chromeSetUp();
    }

    @BeforeEach
    @Step("Navigating to initial webpage")
    public void navigateToInitialWebPage() throws IOException {
        Selenide.open(utils.ReadPropertiesFileMethod.readHomePageUrlInConfigFile());
        objPartnerRegistrationPage = page(PartnerRegistrationPage.class);
        SkarbHomePage objSkarbHomePage = page(SkarbHomePage.class);
        objSkarbHomePage.clickOnSelectWebSiteLanguage()
                        .clickOnSelectUkrLanguage()
                        .clickOnCreateNewUser()
                        .clickOnCreateNewPartner();
    }

    @Test
    @DisplayName("Checking the initial loading of the web page")
    @Description("This test verifying the initial webpage is loaded successfully. Fails if at least one web element is invisible or disable.")
    @Severity(CRITICAL)
    public void verifyInitialWebPageLoad() {
        assertAll("Checking the initial loading of the web page",
                () -> assertTrue(getWebDriver().getCurrentUrl().contains("registration/partners"),"URL should contain text registration/volunteers"),
                () -> objPartnerRegistrationPage.getFirstNameInput().shouldBe(visible),
                () -> objPartnerRegistrationPage.getLastNameInput().shouldBe(visible),
                () -> objPartnerRegistrationPage.getGenderField().shouldBe(enabled),
                () -> objPartnerRegistrationPage.getEmailInput().shouldBe(visible),
                () -> objPartnerRegistrationPage.getPhoneNumberInput().shouldBe(visible),
                () -> objPartnerRegistrationPage.getPasswordInput().shouldBe(visible),
                () -> objPartnerRegistrationPage.getConfPasswordInput().shouldBe(visible),
                () -> objPartnerRegistrationPage.getOrganizationNameInput().shouldBe(visible),
                () -> objPartnerRegistrationPage.getSelectCategoryField().shouldBe(enabled),
                () -> objPartnerRegistrationPage.getPositionInput().shouldBe(visible),
                () -> objPartnerRegistrationPage.getOrganizationLinkInput().shouldBe(visible),
                () -> objPartnerRegistrationPage.getAboutInput().shouldBe(visible),
                () -> objPartnerRegistrationPage.getSubmitButton().shouldBe(visible)
        );
        logger.debug("verifyInitialWebPageLoad test. Initial WebPage is loaded successfully.");
        logger.info("verifyInitialWebPageLoad test. Initial WebPage is loaded successfully.");
    }

    @Test
    @DisplayName("Test with valid data input")
    @Description("This test attempts to register new partner user using valid data.")
    @Severity(CRITICAL)
    public void validDataInput() {
        Partner user = Instancio.create(Partner.getPartnerModel());

        logger.info("validDataInput test started. Entering user data.");

        WebElementUtils.clickOnButton($(user.getGender()));
        objPartnerRegistrationPage.enterFirstName(user.getFirstName())
                                .enterLastName(user.getLastName())
                                .enterEmail(user.getEmail())
                                .enterPhoneNumber(user.getPhoneNumber())
                                .enterPassword(user.getPassword())
                                .enterConfirmPassword(user.getPassword())
                                .enterOrganizationName(user.getOrganizationName())
                                .selectCategory(user.getSelectCategory())
                                .enterPosition(user.getPosition())
                                .enterOrganizationLink(user.getOrganizationLink())
                                .enterAbout(user.getAbout())
                                .clickOnSubmitButton();

        objPartnerRegistrationPage.verifySuccessfulRegistrationMassage();
        logger.info("validDataInput test execution completed. Registration of user is successful. Message about successful registration is visible.");
        logger.debug("validDataInput test. User data entered. Registration of test user is successful. User email: {}, user password: {}.", user.getEmail(), user.getPassword());

    }

    @Test
    @DisplayName("Test with only mandatory fields input")
    @Description("This test attempts to register new partner user filling up only mandatory fields of the form.")
    @Severity(CRITICAL)
    public void onlyMandatoryFieldsInput() {
        Partner user = Instancio.create(Partner.getPartnerModel());

        logger.info("onlyMandatoryFieldsInput test started. Entering user data.");

        WebElementUtils.clickOnButton($(user.getGender()));
        objPartnerRegistrationPage.enterFirstName(user.getFirstName())
                                .enterLastName(user.getLastName())
                                .enterEmail(user.getEmail())
                                .enterPassword(user.getPassword())
                                .enterConfirmPassword(user.getPassword())
                                .enterOrganizationName(user.getOrganizationName())
                                .enterPosition(user.getPosition())
                                .clickOnSubmitButton();

        objPartnerRegistrationPage.verifySuccessfulRegistrationMassage();
        logger.info("onlyMandatoryFieldsInput test execution completed. Registration of user is successful. Message about successful registration is visible.");
        logger.debug("onlyMandatoryFieldsInput test. User data entered. Registration of test user is successful. User email: {}, user password: {}.", user.getEmail(), user.getPassword());

    }

    @Test
    @DisplayName("Test with Cyrillic data input")
    @Description("This test attempts to register new partner user filling up text fields with Cyrillic data.")
    @Severity(CRITICAL)
    public void cyrillicDataInput() {
        Partner user = Instancio.create(Partner.getPartnerModel());

        logger.info("cyrillicDataInput test started. Entering user data.");

        WebElementUtils.clickOnButton($(user.getGender()));
        objPartnerRegistrationPage.enterFirstName("A"+utils.RandomDataGenerationMethods.generateRandomCyrillicString(5))
                                .enterLastName("A"+utils.RandomDataGenerationMethods.generateRandomCyrillicString(5))
                                .enterEmail(user.getEmail())
                                .enterPhoneNumber(user.getPhoneNumber())
                                .enterPassword(user.getPassword())
                                .enterConfirmPassword(user.getPassword())
                                .enterOrganizationName(utils.RandomDataGenerationMethods.generateRandomCyrillicString(5))
                                .selectCategory(user.getSelectCategory())
                                .enterPosition(utils.RandomDataGenerationMethods.generateRandomCyrillicString(7))
                                .enterOrganizationLink(user.getOrganizationLink())
                                .enterAbout(utils.RandomDataGenerationMethods.generateRandomCyrillicString(7))
                                .clickOnSubmitButton();

        objPartnerRegistrationPage.verifySuccessfulRegistrationMassage();
        logger.info("cyrillicDataInput test execution completed. Registration of user is successful. Message about successful registration is visible.");
        logger.debug("cyrillicDataInput test. User data entered. Registration of test user is successful. User email: {}, user password: {}.", user.getEmail(), user.getPassword());

    }


    @Test
    @DisplayName("Test with phone number without code 38")
    @Description("This test attempts to register new partner user with phone number without code 38.")
    @Severity(CRITICAL)
    public void phoneWithoutCode38Input() {
        Partner user = Instancio.create(Partner.getPartnerModel());

        logger.info("phoneWithoutCode38Input test started. Entering user data.");

        WebElementUtils.clickOnButton($(user.getGender()));
        objPartnerRegistrationPage.enterFirstName(user.getFirstName())
                                .enterLastName(user.getLastName())
                                .enterEmail(user.getEmail())
                                .enterPhoneNumber("0965676565")
                                .enterPassword(user.getPassword())
                                .enterConfirmPassword(user.getPassword())
                                .enterOrganizationName(user.getOrganizationName())
                                .selectCategory(user.getSelectCategory())
                                .enterPosition(user.getPosition())
                                .enterOrganizationLink(user.getOrganizationLink())
                                .enterAbout(user.getAbout())
                                .clickOnSubmitButton();

        objPartnerRegistrationPage.verifySuccessfulRegistrationMassage();
        logger.info("phoneWithoutCode38Input test execution completed. Registration of user is successful. Message about successful registration is visible.");
        logger.debug("phoneWithoutCode38Input test. User data entered. Registration of test user is successful. User email: {}, user password: {}.", user.getEmail(), user.getPassword());

    }

}
