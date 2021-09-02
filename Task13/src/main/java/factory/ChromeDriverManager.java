package factory;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

import java.io.IOException;

public class ChromeDriverManager extends DriverManager {
    private ChromeDriverService chromeService;

    @Override
    protected void startService() {
        if (chromeService == null) {
            System.setProperty("webdriver.chrome.driver", "browserDrivers/chromedriver.exe");
            chromeService = new ChromeDriverService.Builder().usingAnyFreePort().build();
            try {
                chromeService.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void stopService() {
        if(chromeService.isRunning() && chromeService != null) {
            chromeService.stop();
        }
    }

    @Override
    protected void createDriver() {
        driver = new ChromeDriver(chromeService);
        driver.manage().window().maximize();
    }
}
