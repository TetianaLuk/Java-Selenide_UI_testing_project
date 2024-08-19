package pageobjects;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import utils.WebElementUtils;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    SelenideElement loginInput = $("#login");
    SelenideElement passwordInput = $("#password");
    SelenideElement signInButtonOnLoginPage = $("button[name='login-button']");

    public LoginPage enterLogin(String login) {
        loginInput.clear();
        WebElementUtils.enterText(loginInput, login);
        return this;
    }

    public LoginPage enterPassword(String password) {
        passwordInput.clear();
        WebElementUtils.enterText(passwordInput, password);
        return this;
    }

    public LoginPage checkLoadingOfLoginPage(){
        loginInput.shouldBe(visible);
        passwordInput.shouldBe(visible);
        signInButtonOnLoginPage.shouldBe(enabled);
        return this;
    }

    @Step("Login with user's email and password")
    public void clickOnSignInButton() {
        WebElementUtils.clickOnButton(signInButtonOnLoginPage);
    }

    public SelenideElement getLoginInput() {
        return loginInput;
    }

    public SelenideElement getPasswordInput() {
        return passwordInput;
    }

    public SelenideElement getSignInButtonOnLoginPage() {
        return signInButtonOnLoginPage;
    }
}
