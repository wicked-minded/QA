import factory.DriverManager;
import factory.DriverFactoryManager;
import factory.DriverType;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObject.*;

import java.util.concurrent.TimeUnit;

public class MusicLibraryTest {
    WebDriver driver;
    DriverManager driverManager;

    @BeforeTest
    public void setUp() {
        driverManager = DriverFactoryManager.getDriver(DriverType.CHROME);
    }

    @BeforeMethod
    public void beforeMethod() {
        driver = driverManager.getDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void musicLibraryTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openPage()
                .musicLibraryPage();
    }

    @AfterTest(alwaysRun = true)
    public void shutDown() {
        driverManager.quitDriver();
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("All tests passed");
    }
}
