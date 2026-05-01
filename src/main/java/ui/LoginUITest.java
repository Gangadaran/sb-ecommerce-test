package ui;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginUITest {

    @Test
    void loginTest() {

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        WebDriver driver = new ChromeDriver(options);

        // ✅ Fix 1: Default value (prevents null crash)
        String baseUrl = System.getProperty("baseUrl", "http://3.7.60.195:3000");

        driver.get(baseUrl);

        driver.findElement(By.xpath("//span[text()='Login']")).click();
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("adminPass");
        driver.findElement(By.xpath("//button[text()='Login']")).click();

        driver.quit();
    }
}