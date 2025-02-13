package tests;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.sql.*;

@DisplayName("Проверка функциональности добавления товаров")
public class AddItemTest extends BaseTest {



    //Ниже сами тесты

    @DisplayName("Добавление нового товара в таблицу")
    @ParameterizedTest
    @CsvSource({
            "Манго, FRUIT, true",
            "Груша, FRUIT, false"
    })
    void addNewItemTest(String name, String type, boolean isExotic) throws SQLException {
        addItemToUI(name, type, isExotic);
        checkItemInUI(name);
        checkItemInDatabase(name);
        deleteItemFromDatabase(name);
        checkItemDeletedFromDatabase(name);
    }

    @DisplayName("Попытка добавить уже существующий товар")
    @ParameterizedTest
    @CsvSource({
            "Бамия, VEGETABLE, true",
            "Огурец, VEGETABLE, false"
    })
    void addExistingItemTest(String name, String type, boolean isExotic) throws SQLException {
        addItemToDatabase(name, type, isExotic);
        checkUniqueItemInDatabase(name);
        addItemToUI(name, type, isExotic);
        checkUniqueItemInDatabase(name);
    }

    /*
    Ниже степы каждого действия
    */
    @Step("Добавляем товар '{name}' типа '{type}' (экзотика: {isExotic}) через UI")
    void addItemToUI(String name, String type, boolean isExotic) {
        homePage.selectDropdownOption("Товары");
        addItemPage.addItem(name, type, isExotic);
    }

    @Step("Добавляем товар '{name}' в БД")
    void addItemToDatabase(String name, String type, boolean isExotic) throws SQLException {
        dataBaseConfig.addItemToDatabase(name, type, isExotic);
    }

    @Step("Проверяем, что товар '{name}' отображается в UI")
    void checkItemInUI(String name) {
        Assertions.assertTrue(addItemPage.isItemDisplayed(name), "Товар " + name + " не отображается в UI");
    }

    @Step("Проверяем, что товар '{name}' присутствует в БД")
    void checkItemInDatabase(String name) throws SQLException {
        Assertions.assertTrue(dataBaseConfig.isItemInDatabase(name), "Товар " + name + " не найден в БД");
    }

    @Step("Проверяем, что товар '{name}' существует в БД в единственном экземпляре")
    void checkUniqueItemInDatabase(String name) throws SQLException {
        Assertions.assertEquals(1, dataBaseConfig.countItemsInDatabase(name), "Товар " + name + " повторяется в БД");
    }

    @Step("Удаляем товар '{name}' из БД")
    void deleteItemFromDatabase(String name) throws SQLException {
        dataBaseConfig.deleteItemFromDatabase(name);
    }

    @Step("Проверяем, что товар '{name}' был удален из БД")
    void checkItemDeletedFromDatabase(String name) throws SQLException {
        Assertions.assertFalse(dataBaseConfig.isItemInDatabase(name), "Товар " + name + " не удален из БД");
    }
}



