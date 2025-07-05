package com.bribooks.tests;

import com.bribooks.pages.BookCreationPage;
import com.bribooks.pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class LoginAndPublishTest {

    WebDriver driver;
    LoginPage loginPage;
    BookCreationPage bookCreationPage;
    Properties prop = new Properties();

    @BeforeClass
    public void setup() throws IOException {
        // Load test data
        FileInputStream fis = new FileInputStream("src/main/resources/testdata.properties");
        prop.load(fis);

        // Set up ChromeDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        driver.get("https://www.bribooks.com");

        // Init page objects
        loginPage = new LoginPage(driver);
        bookCreationPage = new BookCreationPage(driver);
        loginPage.acceptCookiesIfVisible();
    }

    @Test(priority = 1)
    public void validLoginTest() {
        loginPage.enterEmail(prop.getProperty("valid.email"));
        loginPage.enterPassword(prop.getProperty("valid.password"));
        loginPage.clickLogin();
    }

    @Test(priority = 2)
    public void invalidLoginTest() {
        driver.navigate().refresh();
        loginPage.enterEmail(prop.getProperty("invalid.email"));
        loginPage.enterPassword(prop.getProperty("invalid.password"));
        loginPage.clickLogin();

        assert loginPage.isInvalidLoginMessageDisplayed();
    }

    @Test(priority = 3)
    public void emptyLoginTest() {
        driver.navigate().refresh();
        loginPage.enterEmail("");
        loginPage.enterPassword("");
        loginPage.clickLogin();
    }

    @Test(priority = 4)
    public void bookCreationTest() {
        bookCreationPage.clickCreateNewBook();
        bookCreationPage.selectTheme();
        bookCreationPage.enterBookTitle(prop.getProperty("book.title"));
        bookCreationPage.enterAuthorName(prop.getProperty("book.author"));
        bookCreationPage.clickPublish();

        assert bookCreationPage.isPublishSuccessMessageDisplayed();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
