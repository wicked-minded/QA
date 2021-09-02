import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.*;
import java.time.LocalDate;

public class WaiterTest extends BasePage {

    @BeforeSuite
    public void setProps() {
        System.setProperty("webdriver.chrome.driver", "browserDrivers/chromedriver.exe");
    }

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://rozetka.com.ua/");
        driver.manage().window().maximize();
    }

    @Test
    public void main() {
        String testSearchText = "монитор";

        RozetkaHomePage rozetkaHomePage = new RozetkaHomePage(driver);
        rozetkaHomePage.searchProduct(testSearchText);

        RozetkaSearchResults rozetkaSearchResults = new RozetkaSearchResults(driver);
        rozetkaSearchResults.ClickFirstProduct();

        RozetkaProductPage rozetkaProductPage = new RozetkaProductPage(driver);
        rozetkaProductPage.ClickToCheckout().checkoutPage();
    }

    @AfterMethod(alwaysRun = true)
    public void takeScreenshot(ITestResult result) {
        if (!result.isSuccess())
            try {
                byte[] scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                FileOutputStream fw = new FileOutputStream(result.getName() +"["+ LocalDate.now() + "][" + System.currentTimeMillis() + "].png");
                fw.write(scrFile);

            } catch (
                    IOException e) {
                e.printStackTrace();
            }
        driver.quit();
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("All tests passed");
    }
}

