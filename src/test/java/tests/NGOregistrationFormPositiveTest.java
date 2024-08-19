package tests;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.Step;
import models.NGO;
import models.NGOforBuilderPattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.instancio.Instancio;
import org.instancio.junit.InstancioExtension;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import pageobjects.NGO_RegistrationPage;
import pageobjects.SkarbHomePage;
import utils.WebElementUtils;
import java.io.IOException;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.SeverityLevel.CRITICAL;
import static org.junit.jupiter.api.Assertions.*;
import static utils.DriverSetUp.chromeSetUp;

@ExtendWith(InstancioExtension.class)

public class NGOregistrationFormPositiveTest {

    private static final Logger logger = LogManager.getLogger(NGOregistrationFormPositiveTest.class);

    NGO_RegistrationPage obgNGO_RegistrationPage;

    @BeforeAll
    public static void setUp() throws IOException {
        Selenide.open(utils.ReadPropertiesFileMethod.readHomePageUrlInConfigFile());
        chromeSetUp();
    }

    @BeforeEach
    @Step("Navigating to initial webpage")
    public void navigateToInitialWebPage() throws IOException {
        Selenide.open(utils.ReadPropertiesFileMethod.readHomePageUrlInConfigFile());
        obgNGO_RegistrationPage = page(NGO_RegistrationPage.class);
        SkarbHomePage objSkarbHomePage = page(SkarbHomePage.class);
        objSkarbHomePage.clickOnSelectWebSiteLanguage()
                        .clickOnSelectUkrLanguage()
                        .clickOnCreateNewUser()
                        .clickOnCreateNewNGO();
        logger.info("The driver is set up. Navigated to initial webpage.");
    }

    @Test
    @DisplayName("Checking the initial loading of the web page")
    @Description("This test verifying the initial webpage is loaded successfully. Fails if at least one web element is invisible or disable.")
    @Severity(CRITICAL)
    public void verifyInitialWebPageLoad() {
        assertAll("Checking the initial loading of the web page",
                () -> assertTrue(getWebDriver().getCurrentUrl().contains("registration/organizations"),"URL should contain text registration/volunteers"),
                () -> obgNGO_RegistrationPage.getFirstNameInput().shouldBe(visible),
                () -> obgNGO_RegistrationPage.getLastNameInput().shouldBe(visible),
                () -> obgNGO_RegistrationPage.getGenderField().shouldBe(enabled),
                () -> obgNGO_RegistrationPage.getEmailInput().shouldBe(visible),
                () -> obgNGO_RegistrationPage.getPhoneNumberInput().shouldBe(visible),
                () -> obgNGO_RegistrationPage.getPasswordInput().shouldBe(visible),
                () -> obgNGO_RegistrationPage.getConfPasswordInput().shouldBe(visible),
                () -> obgNGO_RegistrationPage.getOrganizationNameInput().shouldBe(visible),
                () -> obgNGO_RegistrationPage.getSelectCategoryField().shouldBe(enabled),
                () -> obgNGO_RegistrationPage.getPositionInput().shouldBe(visible),
                () -> obgNGO_RegistrationPage.getOrganizationLinkInput().shouldBe(visible),
                () -> obgNGO_RegistrationPage.getAboutInput().shouldBe(visible),
                () -> obgNGO_RegistrationPage.getOrganizationRegistrationUrlInput().shouldBe(visible),
                () -> obgNGO_RegistrationPage.getSubmitButton().shouldBe(visible)
        );
        logger.info("Initial WebPage is loaded successfully.");
    }

    @Test
    @DisplayName("Test with valid data input")
    @Description("This test attempts to register new NGO user using valid data.")
    @Severity(CRITICAL)
    public void validDataInput() {
        NGO user = Instancio.create(NGO.getNGO_Model());

        NGOforBuilderPattern NGOforBuilderPattern = new NGOforBuilderPattern.Builder()
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setGender(user.getGender())
                .setEmail(user.getEmail())
                .setPhoneNumber(user.getPhoneNumber())
                .setPassword(user.getPassword())
                .setOrganizationName(user.getOrganizationName())
                .setSelectCategory(user.getSelectCategory())
                .setPosition(user.getPosition())
                .setOrganizationLink(user.getOrganizationLink())
                .setAbout(user.getAbout())
                .setRegisterLink(user.getRegisterLink()).build();

        logger.info("validDataInput test started. Entering user data.");

        WebElementUtils.clickOnButton($(NGOforBuilderPattern.getGender()));
        obgNGO_RegistrationPage.populateNGOregistrationForm(NGOforBuilderPattern.getFirstName(), NGOforBuilderPattern.getLastName(), NGOforBuilderPattern.getEmail(),NGOforBuilderPattern.getPhoneNumber(),NGOforBuilderPattern.getPassword(), NGOforBuilderPattern.getPassword(), NGOforBuilderPattern.getOrganizationName(), NGOforBuilderPattern.getSelectCategory(), NGOforBuilderPattern.getPosition(), NGOforBuilderPattern.getOrganizationLink(), NGOforBuilderPattern.getAbout(), NGOforBuilderPattern.getRegisterLink())
                                .verifyNGOregistrationFormPopulatedCorrectly(NGOforBuilderPattern.getFirstName(), NGOforBuilderPattern.getLastName(), NGOforBuilderPattern.getEmail(), NGOforBuilderPattern.getPhoneNumber(), NGOforBuilderPattern.getPassword(), NGOforBuilderPattern.getPassword(), NGOforBuilderPattern.getOrganizationName(), NGOforBuilderPattern.getSelectCategory(), NGOforBuilderPattern.getPosition(), NGOforBuilderPattern.getOrganizationLink(), NGOforBuilderPattern.getAbout(), NGOforBuilderPattern.getRegisterLink())
                                .clickOnSubmitButton()
                                .verifySuccessfulRegistrationMassage();
        logger.info("Registration of user is successful. Message about successful registration is visible.");
    }
}
