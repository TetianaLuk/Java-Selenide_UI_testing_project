package utils;

import com.codeborne.selenide.Configuration;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class DriverSetUp {

    public static void chromeSetUp() {
        Configuration.browser = "chrome";
        getWebDriver().manage().window().maximize();
        Configuration.timeout = 10000;
    }

    public static void firefoxSetUp() {
        Configuration.browser = "firefox";
        getWebDriver().manage().window().maximize();
        Configuration.timeout = 10000;
    }

}
