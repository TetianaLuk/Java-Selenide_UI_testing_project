package tests;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.Step;
import models.Partner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pageobjects.PartnerRegistrationPage;
import pageobjects.SkarbHomePage;
import utils.WebElementUtils;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import static io.qameta.allure.SeverityLevel.CRITICAL;
import static utils.DriverSetUp.chromeSetUp;

public class PartnerParameterizedTest {

    PartnerRegistrationPage objPartnerRegistrationPage;

    private static final Logger logger = LogManager.getLogger(PartnerParameterizedTest.class);

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

    @ParameterizedTest
    @MethodSource("testdata.PartnerParameterizedTestData#getPartnerParameterizedTestData")
    @DisplayName("Create new partner with parameterized test and @MethodSource")
    @Description("This test attempts to register new partner user using valid data from @MethodSource.")
    @Severity(CRITICAL)
    public void createPartnerTestUser(Partner user) {
        logger.info("createPartnerTestUser test started. Entering user data.");

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

    }
}
