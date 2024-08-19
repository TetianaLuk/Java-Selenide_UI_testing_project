package pageobjects;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import dev.failsafe.internal.util.Assert;
import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import utils.WebElementUtils;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static utils.ScreenshotMethods.attachScreenshotToAllureReport;

public class PartnerRegistrationPage {

    SelenideElement firstNameInput = $("#firstName");
    SelenideElement lastNameInput = $("#lastName");
    SelenideElement genderField = $("div[name='sex']");
    SelenideElement emailInput = $("#email");
    SelenideElement phoneNumberInput = $("#phoneNumber");
    SelenideElement passwordInput = $("#password");
    SelenideElement confPasswordInput = $("#confirmPassword");
    SelenideElement organizationNameInput = $("#organizationName");
    SelenideElement selectCategoryField = $("#categoryIds");
    SelenideElement positionInput = $(byXpath("//input[@id='positionInOrganization']"));
    SelenideElement organizationLinkInput = $("#organizationSiteUrl");
    SelenideElement aboutInput = $("#aboutOrganization");
    SelenideElement submitButton = $("button[name='submit']");
    SelenideElement successMessage = $(".display-3.text-center");
    SelenideElement errorMessageUnderFirstNameField = $("div[name='first-name'] small[class='text-danger']");
    SelenideElement errorMessageUnderLastNameField = $("div[name='last-name'] small[class='text-danger']");
    SelenideElement errorMessageUnderSexField = $("div[name='sex'] small[class='text-danger']");
    SelenideElement errorMessageUnderEmailField = $("div[name='email'] small[class='text-danger']");
    SelenideElement errorMessageUnderPasswordField = $("div[name='password'] small[class='text-danger']");
    SelenideElement errorMessageUnderConfirmPasswordField = $("div[name='confirm-password'] small[class='text-danger']");
    SelenideElement errorMessageUnderOrganizationNameField = $("div[name='organization-name'] small[class='text-danger']");
    SelenideElement errorMessageUnderPositionField = $("div[name='position-in-organization'] small[class='text-danger']");
    SelenideElement errorMessageUnderPhoneNumberField = $("div[name='phone'] small[class='text-danger']");

    public PartnerRegistrationPage enterFirstName(String firstName) {
        WebElementUtils.enterText(firstNameInput, firstName);
        return this;
    }

    public PartnerRegistrationPage enterLastName(String lastName) {
        WebElementUtils.enterText(lastNameInput, lastName);
        return this;
    }

    public PartnerRegistrationPage enterEmail(String email) {
        WebElementUtils.enterText(emailInput, email);
        return this;
    }

    public PartnerRegistrationPage enterPhoneNumber(String phoneNumber) {
        WebElementUtils.enterText(phoneNumberInput, phoneNumber);
        return this;
    }

    public PartnerRegistrationPage enterPassword(String password) {
        WebElementUtils.enterText(passwordInput, password);
        return this;
    }

    public PartnerRegistrationPage enterConfirmPassword(String confirmPassword) {
        WebElementUtils.enterText(confPasswordInput, confirmPassword);
        return this;
    }

    public PartnerRegistrationPage enterOrganizationName(String organizationName) {
        WebElementUtils.enterText(organizationNameInput, organizationName);
        return this;
    }

    public PartnerRegistrationPage selectCategory(String category) {
        WebElementUtils.selectCategory(selectCategoryField, category);
        return this;
    }

    public PartnerRegistrationPage enterPosition(String position) {
        WebElementUtils.enterText(positionInput, position);
        return this;
    }

    public PartnerRegistrationPage enterOrganizationLink(String organizationLink) {
        WebElementUtils.enterText(organizationLinkInput, organizationLink);
        return this;
    }

    public PartnerRegistrationPage enterAbout(String about) {
        WebElementUtils.enterText(aboutInput, about);
        return this;
    }

    @Step("Enter partner data and click on 'Sign up' button")
    public void clickOnSubmitButton() {
        WebElementUtils.clickOnButton(submitButton);
    }

    @Step("Verify successful registration message")
    public void verifySuccessfulRegistrationMassage(){
        successMessage.shouldBe(visible);
        attachScreenshotToAllureReport("Successful registration message");
    }

    @Step("Verify error message when first name field left blank or with invalid input")
    public PartnerRegistrationPage verifyErrorMessageUnderFirstNameField(){
        errorMessageUnderFirstNameField.shouldHave(exactText("Імя не може містити символи та розділові знаки"));
        return this;
    }

    @Step("Verify error message when last name field left blank or with invalid input")
    public PartnerRegistrationPage verifyErrorMessageUnderLastNameField(){
        errorMessageUnderLastNameField.shouldHave(exactText("Призвище не може містити символи та розділові знаки"));
        return this;
    }

    @Step("Verify error message when sex field left blank")
    public PartnerRegistrationPage verifyErrorMessageWhenSexFieldLeftBlank(){
        errorMessageUnderSexField.shouldHave(exactText("Поле не може бути порожнім"));
        return this;
    }

    @Step("Verify error message when email field left blank")
    public PartnerRegistrationPage verifyErrorMessageWhenEmailFieldLeftBlank(){
        errorMessageUnderEmailField.shouldHave(exactText("Адреса повинна бути корпоративною"));
        return this;
    }

    @Step("Verify error message when password field left blank")
    public PartnerRegistrationPage verifyErrorMessageWhenPasswordFieldLeftBlank(){
        errorMessageUnderPasswordField.shouldHave(exactText("Поле не може бути порожнім"));
        return this;
    }

    @Step("Verify error message when confirm password field left blank")
    public PartnerRegistrationPage verifyErrorMessageWhenConfirmPasswordFieldLeftBlank(){
        errorMessageUnderConfirmPasswordField.shouldHave(exactText("Поле не може бути порожнім"));
        return this;
    }

    @Step("Verify error message when organization name field left blank or with invalid input")
    public PartnerRegistrationPage verifyErrorMessageUnderOrganizationNameField(){
        errorMessageUnderOrganizationNameField.shouldHave(exactText("Не должно содержать символов"));
        return this;
    }

    @Step("Verify error message when position field left blank or with invalid input")
    public void verifyErrorMessageUnderPositionField(){
        errorMessageUnderPositionField.shouldHave(exactText("Не може містити символи"));
    }

    @Step("Verify error message when email field with invalid input")
    public void verifyErrorMessageWhenEmailFieldWithInvalidInput(){
        String expectedMessage = "Електронна адреса має містити знак \"@\". В електронній адресі \"ashley.anderson.skarb.ngo\" знака \"@\" немає.";
        String validationMessage = (String) Selenide.executeJavaScript("var emailField = document.querySelector('#email'); return emailField.validationMessage;");
        Assert.isTrue(expectedMessage.equals(validationMessage),"There is no alert message 'Електронна адреса має містити знак \"@\". В електронній адресі \"ashley.anderson.skarb.ngo\" знака \"@\" немає' under email field");

        /* If doesn't see such message, check the text of validation message:
        *
        * String result = (String) Selenide.executeJavaScript("return document.querySelector('#email').validationMessage;");
        * System.out.println("Validation Message: " + result);
        *
        * Running via Docker I`ve got following result:
        * String expectedMessage = "Please include an '@' in the email address. 'ashley.anderson.skarb.ngo' is missing an '@'.";
        * String validationMessage = (String) Selenide.executeJavaScript("var emailField = document.querySelector('#email'); return emailField.validationMessage;");
        * Assert.isTrue(expectedMessage.equals(validationMessage),"There is no alert message 'Please include an '@' in the email address. 'ashley.anderson.skarb.ngo' is missing an '@'.' under email field");
        */
    }

    @Step("Verify error message when phone number field with invalid input")
    public void verifyErrorMessageWhenPhoneNumberFieldWithInvalidInput(){
        errorMessageUnderPhoneNumberField.shouldHave(exactText("Номер телефону введено не коректно"));
    }

    @Step("Verify error message when password field with invalid input")
    public void verifyErrorMessageWhenPasswordFieldWithInvalidInput(){
        errorMessageUnderPasswordField.shouldHave(exactText("Пароль введено не коректно. Максимальна довжина паролю 100, мінімальна довжина паролю 8, має містити: великих літер - щонайменше 2, малих літер - щонайменше 2, спеціальних символів - 1"));
    }

    @Step("Verify error message when confirm password field with invalid input")
    public void verifyErrorMessageWhenConfirmPasswordFieldWithInvalidInput(){
        errorMessageUnderConfirmPasswordField.shouldHave(exactText("Password and confirm password isn`t equal"));
    }

    public SelenideElement getFirstNameInput() {
        return firstNameInput;
    }

    public SelenideElement getLastNameInput() {
        return lastNameInput;
    }

    public SelenideElement getGenderField() {
        return genderField;
    }

    public SelenideElement getEmailInput() {
        return emailInput;
    }

    public SelenideElement getPhoneNumberInput() {
        return phoneNumberInput;
    }

    public SelenideElement getPasswordInput() {
        return passwordInput;
    }

    public SelenideElement getConfPasswordInput() {
        return confPasswordInput;
    }

    public SelenideElement getOrganizationNameInput() {
        return organizationNameInput;
    }

    public SelenideElement getSelectCategoryField() {
        return selectCategoryField;
    }

    public SelenideElement getPositionInput() {
        return positionInput;
    }

    public SelenideElement getOrganizationLinkInput() {
        return organizationLinkInput;
    }

    public SelenideElement getAboutInput() {
        return aboutInput;
    }

    public SelenideElement getSubmitButton() {
        return submitButton;
    }

    public SelenideElement getSuccessMessage() {
        return successMessage;
    }

}
