package pageobjects;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import utils.WebElementUtils;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static utils.ScreenshotMethods.attachScreenshotToAllureReport;

public class NGO_RegistrationPage {

    SelenideElement firstNameInput = $("#firstName");
    SelenideElement lastNameInput = $("#lastName");
    SelenideElement genderField = $("div[name='sex']");
    SelenideElement emailInput = $("#email");
    SelenideElement phoneNumberInput = $("#phoneNumber");
    SelenideElement passwordInput = $("#password");
    SelenideElement confPasswordInput = $("#confirmPassword");
    SelenideElement organizationNameInput = $("#organizationName");
    SelenideElement selectCategoryField = $("#categoryIds");
    SelenideElement selectedCategory = $(".filter-option-inner-inner");
    SelenideElement positionInput = $(byXpath("//input[@id='positionInOrganization']"));
    SelenideElement organizationLinkInput = $("#organizationSiteUrl");
    SelenideElement aboutInput = $("#aboutOrganization");
    SelenideElement organizationRegistrationUrlInput = $("#organizationRegistrationUrl");
    SelenideElement submitButton = $("button[name='submit']");
    SelenideElement successMessage = $(".display-3.text-center");

    public NGO_RegistrationPage enterFirstName(String firstName) {
        WebElementUtils.enterText(firstNameInput, firstName);
        return this;
    }

    public NGO_RegistrationPage enterLastName(String lastName) {
        WebElementUtils.enterText(lastNameInput, lastName);
        return this;
    }

    public NGO_RegistrationPage enterEmail(String email) {
        WebElementUtils.enterText(emailInput, email);
        return this;
    }

    public NGO_RegistrationPage enterPhoneNumber(String phoneNumber) {
        WebElementUtils.enterText(phoneNumberInput, phoneNumber);
        return this;
    }

    public NGO_RegistrationPage enterPassword(String password) {
        WebElementUtils.enterText(passwordInput, password);
        return this;
    }

    public NGO_RegistrationPage enterConfirmPassword(String confirmPassword) {
        WebElementUtils.enterText(confPasswordInput, confirmPassword);
        return this;
    }

    public NGO_RegistrationPage enterOrganizationName(String organizationName) {
        WebElementUtils.enterText(organizationNameInput, organizationName);
        return this;
    }

    public NGO_RegistrationPage selectCategory(String category) {
        WebElementUtils.selectCategory(selectCategoryField, category);
        return this;
    }

    public NGO_RegistrationPage enterPosition(String position) {
        WebElementUtils.enterText(positionInput, position);
        return this;
    }

    public NGO_RegistrationPage enterOrganizationLink(String organizationLink) {
        WebElementUtils.enterText(organizationLinkInput, organizationLink);
        return this;
    }

    public NGO_RegistrationPage enterAbout(String about) {
        WebElementUtils.enterText(aboutInput, about);
        return this;
    }

    public NGO_RegistrationPage enterOrganizationRegistrationUrl(String organizationRegistrationUrl) {
        WebElementUtils.enterText(organizationRegistrationUrlInput, organizationRegistrationUrl);
        return this;
    }

    @Step("Enter NGO data and click on 'Sign up' button")
    public NGO_RegistrationPage clickOnSubmitButton() {
        WebElementUtils.clickOnButton(submitButton);
        return this;
    }

    @Step("Verify successful registration message")
    public void verifySuccessfulRegistrationMassage(){
        successMessage.shouldBe(visible);
        attachScreenshotToAllureReport("Successful registration message");
    }

    public NGO_RegistrationPage populateNGOregistrationForm(String firstName, String lastName, String email, String phoneNumber, String password, String confirmPassword, String organizationName, String category, String position, String organizationLink, String about, String organizationRegistrationUrl){
        WebElementUtils.enterText(firstNameInput, firstName);
        WebElementUtils.enterText(lastNameInput, lastName);
        WebElementUtils.enterText(emailInput, email);
        WebElementUtils.enterText(phoneNumberInput, phoneNumber);
        WebElementUtils.enterText(passwordInput, password);
        WebElementUtils.enterText(confPasswordInput, confirmPassword);
        WebElementUtils.enterText(organizationNameInput, organizationName);
        WebElementUtils.selectCategory(selectCategoryField, category);
        WebElementUtils.enterText(positionInput, position);
        WebElementUtils.enterText(organizationLinkInput, organizationLink);
        WebElementUtils.enterText(aboutInput, about);
        WebElementUtils.enterText(organizationRegistrationUrlInput, organizationRegistrationUrl);
        return this;
    }

    public NGO_RegistrationPage verifyNGOregistrationFormPopulatedCorrectly(String firstName, String lastName, String email, String phoneNumber, String password, String confirmPassword, String organizationName, String category, String position, String organizationLink, String about, String organizationRegistrationUrl){
        firstNameInput.shouldHave(value(firstName));
        lastNameInput.shouldHave(value(lastName));
        emailInput.shouldHave(value(email));
        phoneNumberInput.shouldHave(value(phoneNumber));
        passwordInput.shouldHave(value(password));
        confPasswordInput.shouldHave(value(confirmPassword));
        organizationNameInput.shouldHave(value(organizationName));
        selectedCategory.shouldHave(text(category));
        positionInput.shouldHave(value(position));
        organizationLinkInput.shouldHave(value(organizationLink));
        aboutInput.shouldHave(value(about));
        organizationRegistrationUrlInput.shouldHave(value(organizationRegistrationUrl));
        return this;
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

    public SelenideElement getGenderField() {
        return genderField;
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

    public SelenideElement getOrganizationRegistrationUrlInput() {
        return organizationRegistrationUrlInput;
    }

    public SelenideElement getSubmitButton() {
        return submitButton;
    }

    public SelenideElement getSuccessMessage() {
        return successMessage;
    }
}
