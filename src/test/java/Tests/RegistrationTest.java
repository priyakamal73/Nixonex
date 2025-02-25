package Tests;

import Base.BaseClass;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import Pages.RegistrationPage;
import java.util.Properties;

public class RegistrationTest extends BaseClass {

    private final WebDriver driver;
    Properties prop = BaseClass.getProp();
    RegistrationPage registrationPage = new RegistrationPage(BaseClass.getDriver());

    @BeforeMethod
    public void startTest() {
        test = extent.createTest("Registration Feature");
    }

    public RegistrationTest() {
        this.driver = BaseClass.getDriver();
    }

    @Test
    public void registration() {
        registrationPage.clickSignupLink();
        registrationPage.enterUsername(prop.getProperty("username"));
        registrationPage.enterPassword(prop.getProperty("password"));
        registrationPage.clickSignupButton();

        Alert alt = driver.switchTo().alert();
        String message = alt.getText();
        String screenshotPath = registrationPage.takeScreenshot();
        alt.accept();

        try {
            Assert.assertEquals(message, "Sign up successful.", "The registration failed");
            test.pass("User registered successfully", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        } catch (AssertionError e) {
            test.fail("Registration failed: " + message, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            throw e;
        }
    }
}