import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.*;

public class AddItemTest extends BaseTest {
    DataBaseConfig dataBaseConfig = new DataBaseConfig();


    @DisplayName("Добавление нового товара в таблицу (экзотического фрукта)")
    @Test
    void addFruitExotic() throws SQLException {
        // Добавляем новый товар "Манго" через интерфейс
        HomePage homePage = new HomePage(driver);
        AddItemPage page = homePage.selectDropdownOption("Товары");
        page.addItem("Манго", "FRUIT", true);
        Assertions.assertTrue(page.isItemDisplayed("Манго"), "Товар 'Манго' не отображается на странице");

        // Проверяем, что товар появился в базе данных
        boolean itemAddInDb = dataBaseConfig.isItemInDatabase("Манго");
        Assertions.assertTrue(itemAddInDb, "Товар 'Манго' не был добавлен в базу данных");

        // Удаляем товар из базы данных
        dataBaseConfig.deleteItemFromDatabase("Манго");

        // Проверяем, что товар был удален из базы данных
        boolean itemDelFromDb = dataBaseConfig.isItemInDatabase("Манго");
        Assertions.assertFalse(itemDelFromDb, "Товар 'Манго' не был удален из базы данных");

    }

    @DisplayName("Добавление нового товара в таблицу (не экзотического фрукта)")
    @Test
    void addFruitNotExotic() throws SQLException {
        // Добавляем новый товар "Груша" через интерфейс
        HomePage homePage = new HomePage(driver);
        AddItemPage page = homePage.selectDropdownOption("Товары");
        page.addItem("Груша", "FRUIT", false);
        Assertions.assertTrue(page.isItemDisplayed("Груша"), "Товар 'Груша' не отображается на странице");

        // Проверяем, что товар появился в базе данных
        boolean itemAddInDb = dataBaseConfig.isItemInDatabase("Груша");
        Assertions.assertTrue(itemAddInDb, "Товар 'Груша' не был добавлен в базу данных");

        // Удаляем товар из базы данных
        dataBaseConfig.deleteItemFromDatabase("Груша");

        // Проверяем, что товар был удален из базы данных
        boolean itemDelFromDb = dataBaseConfig.isItemInDatabase("Груша");
        Assertions.assertFalse(itemDelFromDb, "Товар 'Груша' не был удален из базы данных");
    }

    @DisplayName("Добавление существующего товара в таблицу (экзотического овоща)")
    @Test
    void addVegetableExotic() throws SQLException {
        // Добавляем товар "Бамия" в базу данных напрямую
        dataBaseConfig.addItemToDatabase("Бамия", "VEGETABLE", true);

        // Проверяем, что товар "Бамия" создан в одном уникальном экземпляре
        int itemCount = dataBaseConfig.countItemsInDatabase("Бамия");
        Assertions.assertEquals(1, itemCount, "Товар 'Бамия' не должен повторяться в базе данных");

        // Добавляем товар "Бамия" через интерфейс
        HomePage homePage = new HomePage(driver);
        AddItemPage page = homePage.selectDropdownOption("Товары");
        page.addItem("Бамия", "VEGETABLE", true);
        Assertions.assertTrue(page.isItemDisplayed("Бамия"), "Товар 'Бамия' не отображается на странице");

        // Проверяем, что товар "Бамия" не добавился второй раз в базу данных
        int itemCountAfterAdd = dataBaseConfig.countItemsInDatabase("Бамия");
        Assertions.assertEquals(1, itemCountAfterAdd, "Товар 'Бамия' не должен повторяться в базе данных");
    }

    @DisplayName("Добавление существующего товара в таблицу (не экзотического овоща)")
    @Test
    void addVegetableNotExotic() throws SQLException {
        // Добавляем товар "Огурец" в базу данных напрямую
        dataBaseConfig.addItemToDatabase("Огурец", "VEGETABLE",false);

        // Проверяем, что товар "Огурец" создан в одном уникальном экземпляре
        int itemCount = dataBaseConfig.countItemsInDatabase("Огурец");
        Assertions.assertEquals(1, itemCount, "Товар 'Огурец' не должен повторяться в базе данных");

        // Добавляем товар "Огурец" через интерфейс
        HomePage homePage = new HomePage(driver);
        AddItemPage page = homePage.selectDropdownOption("Товары");
        page.addItem("Огурец", "VEGETABLE", false);
        Assertions.assertTrue(page.isItemDisplayed("Огурец"), "Товар 'Огурец' не отображается на странице");

        // Проверяем, что товар "Огурец" не добавился второй раз в базу данных
        int itemCountAfterAdd = dataBaseConfig.countItemsInDatabase("Огурец");
        Assertions.assertEquals(1,itemCountAfterAdd, "Товар 'Огурец' не должен повторяться в базе данных");
    }

}
