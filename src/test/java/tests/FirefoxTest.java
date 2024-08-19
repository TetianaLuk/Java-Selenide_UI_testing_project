package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Properties;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.SeverityLevel.CRITICAL;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static utils.DriverSetUp.firefoxSetUp;
import static utils.ScreenshotMethods.attachScreenshotToAllureReport;

public class FirefoxTest {

    private static Properties properties;

    @BeforeAll
    public static void setUp() throws IOException {
        utils.ReadPropertiesFileMethod.readProperties("src/test/resources/testdata/config.properties");
        properties = utils.ReadPropertiesFileMethod.getProperties();
    }

    @Test
    @DisplayName("Open Firefox browser and go to specified url")
    @Description("This test attempts to open Firefox browser and go to specified url.")
    @Severity(CRITICAL)
    public void testFirefox() {
        String url = properties.getProperty("URL");
        open(url);
        firefoxSetUp();
        assertTrue(getWebDriver().getCurrentUrl().contains("login"));
        attachScreenshotToAllureReport("Screenshot");
    }
}
