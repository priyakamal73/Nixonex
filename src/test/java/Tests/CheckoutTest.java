package Tests;

import Base.BaseClass;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import Pages.CheckoutPage;
import java.util.Properties;

public class CheckoutTest extends BaseClass {
    Properties prop = BaseClass.getProp();
    CheckoutPage checkoutPage = new CheckoutPage(BaseClass.getDriver());

    @BeforeMethod
    public void startTest() {
        test = extent.createTest("Checkout Feature");
    }

    @Test
    public void checkoutPage() {
        checkoutPage.clickCart();
        checkoutPage.clickPlaceOrderButton();

        checkoutPage.enterName(prop.getProperty("name"));
        checkoutPage.enterCountry(prop.getProperty("country"));
        checkoutPage.enterCity(prop.getProperty("city"));
        checkoutPage.enterCreditCard(prop.getProperty("creditCard"));
        checkoutPage.enterMonth(prop.getProperty("month"));
        checkoutPage.enterYear(prop.getProperty("year"));
        checkoutPage.clickPurchaseButton();

        String name = prop.getProperty("name");
        String creditCard = prop.getProperty("creditCard");

        if (name.isEmpty() || creditCard.isEmpty()) {
            String screenshotPath = checkoutPage.takeScreenshot();
            test.fail("Checkout failed: Name or Credit Card is missing", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            Assert.fail("Checkout failed: Name or Credit Card is missing");
        }

        if (checkoutPage.isSuccessMessageDisplayed()) {
            String screenshotPath = checkoutPage.takeScreenshot();
            checkoutPage.acceptAlert();
            test.pass("Checkout process successful", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        } else {
            String screenshotPath = checkoutPage.takeScreenshot();
            test.fail("Checkout failed: Success message not displayed", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            Assert.fail("Checkout failed: Success message not displayed.");
        }
    }
}