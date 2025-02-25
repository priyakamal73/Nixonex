package Tests;

import Base.BaseClass;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import Pages.LoginPage;
import java.util.Properties;

public class LoginTest extends BaseClass {
    Properties prop = BaseClass.getProp();
    LoginPage loginPage = new LoginPage(BaseClass.getDriver());

    @BeforeMethod
    public void startTest() {
        test = extent.createTest("Login Feature");
    }

    @Test
    public void login() {
        loginPage.clickLoginLink();
        loginPage.enterUsername(prop.getProperty("username"));
        loginPage.enterPassword(prop.getProperty("password"));
        loginPage.clickLoginButton();
        String screenshotPath = loginPage.takeScreenshot();

        try {
            Alert alt = BaseClass.getDriver().switchTo().alert();
            String alertMessage = alt.getText();
            alt.accept();
            test.fail("Login failed: " + alertMessage, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            Assert.fail("Login failed: " + alertMessage);
        } catch (NoAlertPresentException e) {
            if (loginPage.isLoginSuccessful()) {
                test.pass("User logged in successfully", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            } else {
                test.fail("Login failed: User not redirected properly", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
                Assert.fail("Login failed: User not redirected properly");
            }
        }
    }
}