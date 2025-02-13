package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Метод для нажатия на первую кнопку
    public void btnLogo() {
        driver.findElement(By.className("navbar-brand")).click();
    }

    // Метод для нажатия на вторую кнопку
    public void btnXsdSchema() {
        driver.findElement(By.xpath("//a[@class='navbar-link' and text()='XSD-схемы']")).click();
    }

    // Метод для нажатия на третью кнопку
    public void btnApi() {
        driver.findElement(By.xpath("//a[@class='navbar-link' and text()='API']")).click();
    }

    // Метод для взаимодействия с выпадающим списком
    public AddItemPage selectDropdownOption(String optionText) {
        driver.findElement(By.id("navbarDropdown")).click();
        driver.findElement(By.xpath("//a[text()='" + optionText + "']")).click();

        // Возвращаем объект новой страницы
        return new AddItemPage(driver);
    }
}


