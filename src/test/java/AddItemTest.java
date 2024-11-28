import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AddItemTest extends BaseTest {

    @DisplayName("Добавление экзотического фрукта")
    @Test
    void addFruitExotic() {
        AddItemPage page = new AddItemPage(driver);
        page.addItem("Манго", "FRUIT", true);
        Assertions.assertTrue(page.isItemDisplayed("Манго"), "Товар 'Манго' не отображается на странице");
    }

    @DisplayName("Добавление не экзотического фрукта")
    @Test
    void addFruitNotExotic() {
        AddItemPage page = new AddItemPage(driver);
        page.addItem("Груша", "FRUIT", false);
        Assertions.assertTrue(page.isItemDisplayed("Груша"), "Товар 'Груша' не отображается на странице");
    }

    @DisplayName("Добавление экзотического овоща")
    @Test
    void addVegetableExotic() {
        AddItemPage page = new AddItemPage(driver);
        page.addItem("Бамия", "VEGETABLE", true);
        Assertions.assertTrue(page.isItemDisplayed("Бамия"), "Товар 'Бамия' не отображается на странице");
    }

    @DisplayName("Добавление не экзотического овоща")
    @Test
    void addVegetableNotExotic() {
        AddItemPage page = new AddItemPage(driver);
        page.addItem("Огурец", "VEGETABLE", false);
        Assertions.assertTrue(page.isItemDisplayed("Огурец"), "Товар 'Огурец' не отображается на странице");
    }
}
