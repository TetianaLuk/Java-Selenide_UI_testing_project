package pageobjects;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selenide.$;
import static utils.ScreenshotMethods.attachScreenshotToAllureReport;

import io.qameta.allure.Step;
import utils.WebElementUtils;

public class VolunteerRegistrationPage {

    SelenideElement firstNameInput = $(by("name","firstName"));
    SelenideElement lastNameInput = $(by("name","lastName"));
    SelenideElement emailInput = $("#email");
    SelenideElement phoneNumberInput = $("#phoneNumber");
    SelenideElement passwordInput = $("#password");
    SelenideElement confPasswordInput = $("#confirmPassword");
    SelenideElement aboutInput = $("#about");
    SelenideElement selectCategoryField = $(by("name","categoryIds"));
    SelenideElement submitButton = $(by("name","submit"));
    SelenideElement successMessage = $(".display-3.text-center");
    SelenideElement errorMessageUnderFirstNameField = $("div[name='first-name'] small[class='text-danger']");
    SelenideElement errorMessageUnderLastNameField = $("div[name='last-name'] small[class='text-danger']");
    SelenideElement errorMessageUnderEmailField = $("div[name='email'] small[class='text-danger']");
    SelenideElement errorMessageUnderPasswordField = $("div[name='password'] small[class='text-danger']");
    SelenideElement errorMessageUnderConfirmPasswordField = $("div[name='confirm-password'] small[class='text-danger']");
    SelenideElement errorMessageUnderPhoneNumberField = $("div[name='phone'] small[class='text-danger']");

    public VolunteerRegistrationPage enterFirstName(String firstName) {
        WebElementUtils.enterText(firstNameInput, firstName);
        return this;
    }

    public VolunteerRegistrationPage enterLastName(String lastName) {
        WebElementUtils.enterText(lastNameInput, lastName);
        return this;
    }

    public VolunteerRegistrationPage enterEmail(String email) {
        WebElementUtils.enterText(emailInput, email);
        return this;
    }

    public VolunteerRegistrationPage enterPhoneNumber(String phoneNumber) {
        WebElementUtils.enterText(phoneNumberInput, phoneNumber);
        return this;
    }

    public VolunteerRegistrationPage enterPassword(String password) {
        WebElementUtils.enterText(passwordInput, password);
        return this;
    }

    public VolunteerRegistrationPage enterConfirmPassword(String confirmPassword) {
        WebElementUtils.enterText(confPasswordInput, confirmPassword);
        return this;
    }

    public VolunteerRegistrationPage enterAbout(String about) {
        WebElementUtils.enterText(aboutInput, about);
        return this;
    }

    public VolunteerRegistrationPage selectCategory(String category) {
        WebElementUtils.selectCategory(selectCategoryField, category);
        return this;
    }

    @Step("Enter volunteer data and click on 'Sign up' button")
    public void clickOnSubmitButton() {
        WebElementUtils.clickOnButton(submitButton);
    }

    @Step("Verify successful registration message")
    public void verifySuccessfulRegistrationMassage(){
        successMessage.shouldBe(visible);
        attachScreenshotToAllureReport("Successful registration message");
    }

    @Step("Verify error message when first name field left blank")
    public VolunteerRegistrationPage verifyErrorMessageWhenFirstNameFieldLeftBlank(){
        errorMessageUnderFirstNameField.shouldHave(exactText("Field can`t be empty"));
        return this;
    }

    @Step("Verify error message when last name field left blank")
    public VolunteerRegistrationPage verifyErrorMessageWhenLastNameFieldLeftBlank(){
        errorMessageUnderLastNameField.shouldHave(exactText("Field can`t be empty"));
        return this;
    }

    @Step("Verify error message when email field left blank")
    public VolunteerRegistrationPage verifyErrorMessageWhenEmailFieldLeftBlank(){
        errorMessageUnderEmailField.shouldHave(exactText("Field can`t be empty"));
        return this;
    }

    @Step("Verify error message when password field left blank")
    public VolunteerRegistrationPage verifyErrorMessageWhenPasswordFieldLeftBlank(){
        errorMessageUnderPasswordField.shouldHave(exactText("Field can`t be empty"));
        return this;
    }

    @Step("Verify error message when confirm password field left blank")
    public void verifyErrorMessageWhenConfirmPasswordFieldLeftBlank(){
        errorMessageUnderConfirmPasswordField.shouldHave(exactText("Field can`t be empty"));
    }

    @Step("Verify error message when first name field with invalid input")
    public VolunteerRegistrationPage verifyErrorMessageWhenFirstNameFieldWithInvalidInput(){
        errorMessageUnderFirstNameField.shouldHave(exactText("Імя не може містити символи та розділові знаки"));
        return this;
    }

    @Step("Verify error message when last name field with invalid input")
    public void verifyErrorMessageWhenLastNameFieldWithInvalidInput(){
        errorMessageUnderLastNameField.shouldHave(exactText("Призвище не може містити символи та розділові знаки"));
    }

    @Step("Verify error message when email field with invalid input")
    public void verifyErrorMessageWhenEmailFieldWithInvalidInput(){
        errorMessageUnderEmailField.shouldHave(exactText("Email введено не коректно"));
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

    public SelenideElement getAboutInput() {
        return aboutInput;
    }

    public SelenideElement getSelectCategoryField() {
        return selectCategoryField;
    }

    public SelenideElement getSubmitButton() {
        return submitButton;
    }
}
