package utils;

import com.codeborne.selenide.SelenideElement;

public class WebElementUtils {

    public static void clickOnButton(SelenideElement element) {
        element.click();
    }

    public static void enterText(SelenideElement element, String text) {
        element.setValue(text);
    }

    public static void selectCategory(SelenideElement element, String text) {
        element.selectOption(text);
    }
}
