package Base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Properties;

public class BaseClass {

    @Getter
    public static WebDriver driver = new ChromeDriver();

    @Getter
    static Properties prop;
    static String filePath = System.getProperty("user.dir") + "\\src\\main\\java\\config\\config.properties";
    public static ExtentReports extent;
    public static ExtentTest test;
    ExtentSparkReporter sparkReporter;


    static {
        prop = new Properties();
        try (InputStream input = Files.newInputStream(Paths.get(filePath))) {
            prop.load(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected static WebDriver getDriver() {
        return driver;
    }

    protected static Properties getProp() {
        return prop;
    }

    @BeforeSuite
    public void setup() {
        sparkReporter = new ExtentSparkReporter(prop.getProperty("report.file", "ExtentReport.html"));
        sparkReporter.config().setDocumentTitle(prop.getProperty("report.title", "Nixonex E-commerce Automation Report"));
        sparkReporter.config().setReportName(prop.getProperty("report.name", "Nixonex E-commerce Automation Report"));
        sparkReporter.config().setTheme(Theme.valueOf(prop.getProperty("report.theme", "DARK").toUpperCase()));

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        driver.get("https://www.demoblaze.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }


    @AfterSuite
    public void tearDown() {
        extent.flush();
        driver.quit();
    }
}