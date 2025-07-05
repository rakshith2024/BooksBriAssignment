package com.bribooks.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;


public class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Locators
    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(xpath = "//button[text()='Login']")
    private WebElement loginButton;

    @FindBy(xpath = "//div[contains(text(), 'invalid')]") // Adjust this based on actual error text
    private WebElement invalidError;

    @FindBy(xpath = "//button[contains(text(), 'Accept All Cookies')]")  // 🔁
    private WebElement acceptCookiesButton;



    // Actions
    public void enterEmail(String email) {
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void enterPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickLogin() {
        loginButton.click();
    }

    public boolean isInvalidLoginMessageDisplayed() {
        return invalidError.isDisplayed();
    }

    public void acceptCookiesIfVisible() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement acceptBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(),'Accept All Cookies')]")
            ));

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", acceptBtn);
        } catch (Exception ignored) {
            System.out.println("Cookie popup not found or already dismissed.");
        }
    }

}
