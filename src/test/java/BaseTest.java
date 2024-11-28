import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public  class BaseTest {
    public static WebDriver driver;

    @BeforeAll
    static void setup() {
        System.setProperty("webdriver.chromedriver/driver", "\\src\\test\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://qualit.appline.ru/food");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    protected WebElement findElementByXpath(String xpath) {
        return driver.findElement(By.xpath(xpath));
    }

    protected WebElement findElementById(String id) {
        return driver.findElement(By.id(id));
    }

    @AfterAll
    static void tearDown() {
        WebElement navbarDropdown = driver.findElement(By.id("navbarDropdown"));
        navbarDropdown.click();
        WebElement btnReset = driver.findElement(By.id("reset"));
        btnReset.click();
        driver.quit();
    }
}
