package repository;

import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private static final String SELECT_ALL_PRODUCTS = "SELECT * FROM Product";
    private static final String INSERT_PRODUCT = "INSERT INTO Product (ProductName, ProductType, Price, StockQuantity, ImageURL, Description) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_PRODUCT = "UPDATE Product SET ProductName = ?, ProductType = ?, Price = ?, StockQuantity = ?, ImageURL = ?, Description = ? WHERE ProductID = ?";
    private static final String DELETE_PRODUCT = "DELETE FROM Product WHERE ProductID = ?";

    public static List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        try (Connection connection = BaseRepository.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTS)) {

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ProductID");
                String name = rs.getString("ProductName");
                String type = rs.getString("ProductType");
                double price = rs.getDouble("Price");
                int stockQuantity = rs.getInt("StockQuantity");
                String imageUrl = rs.getString("ImageURL");
                String description = rs.getString("Description");

                products.add(new Product(id, name, type, price, stockQuantity, imageUrl, description));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }



    public static void main(String[] args) {
        ProductRepository repository = new ProductRepository();
        System.out.println(repository.getAllProducts().size());
    }
    public static void addProduct(Product product) {
        try (Connection connection = BaseRepository.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT)) {

            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getType());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setInt(4, product.getStockQuantity());
            preparedStatement.setString(5, product.getImageUrl());
            preparedStatement.setString(6, product.getDescription());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateProduct(Product product) {
        try (Connection connection = BaseRepository.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getType());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setInt(4, product.getStockQuantity());
            preparedStatement.setString(5, product.getImageUrl());
            preparedStatement.setString(6, product.getDescription());
            preparedStatement.setInt(7, product.getId());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteProduct(int id) {
        try (Connection connection = BaseRepository.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
