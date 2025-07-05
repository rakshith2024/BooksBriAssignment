package com.bribooks.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BookCreationPage {

    WebDriver driver;

    public BookCreationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Elements (Update based on actual site structure if needed)
    @FindBy(xpath = "//button[contains(text(),'Create New Book')]")
    private WebElement createNewBookButton;

    @FindBy(xpath = "//div[contains(@class,'theme-card')]")
    private WebElement selectThemeCard;

    @FindBy(id = "book-title")
    private WebElement bookTitleInput;

    @FindBy(id = "book-author")
    private WebElement bookAuthorInput;

    @FindBy(xpath = "//button[text()='Publish']")
    private WebElement publishButton;

    @FindBy(xpath = "//div[contains(text(),'successfully published')]")
    private WebElement successMessage;

    // Actions
    public void clickCreateNewBook() {
        createNewBookButton.click();
    }

    public void selectTheme() {
        selectThemeCard.click();
    }

    public void enterBookTitle(String title) {
        bookTitleInput.clear();
        bookTitleInput.sendKeys(title);
    }

    public void enterAuthorName(String author) {
        bookAuthorInput.clear();
        bookAuthorInput.sendKeys(author);
    }

    public void clickPublish() {
        publishButton.click();
    }

    public boolean isPublishSuccessMessageDisplayed() {
        return successMessage.isDisplayed();
    }
}
