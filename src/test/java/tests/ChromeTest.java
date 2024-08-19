package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.Properties;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.SeverityLevel.CRITICAL;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static utils.DriverSetUp.chromeSetUp;
import static utils.ScreenshotMethods.attachScreenshotToAllureReport;

public class ChromeTest {

    private static Properties properties;

    @BeforeAll
    public static void setUp() throws IOException {
        utils.ReadPropertiesFileMethod.readProperties("src/test/resources/testdata/config.properties");
        properties = utils.ReadPropertiesFileMethod.getProperties();
    }

    @Test
    @DisplayName("Open Chrome browser and go to specified url")
    @Description("This test attempts to open Chrome browser and go to specified url.")
    @Severity(CRITICAL)
    public void testChrome() {
        String url = properties.getProperty("URL");
        open(url);
        chromeSetUp();
        assertTrue(getWebDriver().getCurrentUrl().contains("login"));
        attachScreenshotToAllureReport("Screenshot");
    }
}
