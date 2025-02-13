package tests;

import database.DataBaseConfig;
import database.H2ConnectionProvider;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.AddItemPage;
import pages.HomePage;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public  class BaseTest {
    public static WebDriver driver;
    private static Process process;
    protected static H2ConnectionProvider provider;
    protected static DataBaseConfig dataBaseConfig;
    protected HomePage homePage;
    protected AddItemPage addItemPage;

    @BeforeAll
    static void setup() {

        // Запуск стенда
        ProcessBuilder processBuilder = new ProcessBuilder("java", "-jar", "C:/Working Project/qualit-sandbox.jar");
        processBuilder.directory(new File("C:/Working Project"));

        try {
            process = processBuilder.start();
            Thread.sleep(10000);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Ошибка запуска стенда", e);
        }

        // Настройка WebDriver
        System.setProperty("webdriver.chrome.driver", "C:/Users/Wh0uts/IdeaProjects/Selenium.Avgustinovich.v2/src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:8080");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));

        // Инициализация БД
        provider = new H2ConnectionProvider();
        dataBaseConfig = new DataBaseConfig(provider);
    }


    @BeforeEach
    void initHomePageAndAddItemPage() {
        homePage = new HomePage(driver);// Теперь в каждом тесте `homePage` готов к использованию
        addItemPage = new AddItemPage(driver);// Теперь в каждом тесте `AddItemPage` готов к использованию
    }



    @AfterAll
    static void tearDown() {
        WebElement navbarDropdown = driver.findElement(By.id("navbarDropdown"));
        navbarDropdown.click();
        WebElement btnReset = driver.findElement(By.id("reset"));
        btnReset.click();
        driver.quit();
        process.destroyForcibly();
        }
}

