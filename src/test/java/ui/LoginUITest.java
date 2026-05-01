package ui;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import io.github.bonigarcia.wdm.WebDriverManager;

import static org.junit.Assert.assertTrue;

public class LoginUITest {

    @Test
    void loginTest() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless");

        WebDriver driver = new ChromeDriver(options);

        String baseUrl = System.getProperty("http://3.7.60.195:3000/");

        driver.get(baseUrl);

        driver.findElement(By.name("userName")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("adminPass");
        driver.findElement(By.tagName("button")).click();

        assertTrue(driver.getCurrentUrl().contains("dashboard"));

        driver.quit();
    }
}