package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBaseConfig {
    private final ConnectionProvider provider;

    public DataBaseConfig(ConnectionProvider provider) {
        this.provider = provider;
    }

    // Метод проверки, что товар находится в БД
    public boolean isItemInDatabase(String itemName) throws SQLException {
        String query = "SELECT FOOD_NAME FROM FOOD WHERE FOOD_NAME = ?";
        try (Connection connection = provider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, itemName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next(); // Возвращаем true, если строка найдена
            }
        }
    }


    //Метод удаления товара из БД
    public void deleteItemFromDatabase(String itemName) throws SQLException {
        String query = "DELETE FROM FOOD WHERE FOOD_NAME = ?";
        try (Connection connection = provider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, itemName);
            preparedStatement.executeUpdate();
        }
    }

    //Метод добавления товара в БД
    public void addItemToDatabase(String itemName, String itemType, boolean isExotic) throws SQLException {
        String query = "INSERT INTO FOOD (FOOD_NAME, FOOD_TYPE, FOOD_EXOTIC) VALUES (?, ?, ?)";
        try (Connection connection = provider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, itemName);
            preparedStatement.setString(2, itemType);
            preparedStatement.setBoolean(3, isExotic);
            preparedStatement.executeUpdate();
        }
    }

    //Метод проверки уникальности записи в БД
    public int countItemsInDatabase(String itemName) throws SQLException {
        String query = "SELECT COUNT(*) FROM FOOD WHERE FOOD_NAME = ?";
        try (Connection connection = provider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, itemName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1); // Возвращаем количество найденных записей
                }
                return 0; // Если товар не найден, возвращаем 0
            }
        }
    }
}

