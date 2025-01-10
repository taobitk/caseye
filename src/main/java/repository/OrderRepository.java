package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderRepository {

    /**
     * Đảm bảo khách hàng đã có đơn hàng Pending.
     */
    public static void ensurePendingOrderExists(int customerId) {
        String CHECK_QUERY = "SELECT OrderID FROM Orders WHERE CustomerID = ? AND OrderStatus = 'Pending'";
        String INSERT_QUERY = "INSERT INTO Orders (CustomerID, TotalAmount, OrderStatus) VALUES (?, 0, 'Pending')";
        try (Connection connection = BaseRepository.getConnection();
             PreparedStatement checkStmt = connection.prepareStatement(CHECK_QUERY)) {
            checkStmt.setInt(1, customerId);
            ResultSet rs = checkStmt.executeQuery();
            if (!rs.next()) {
                try (PreparedStatement insertStmt = connection.prepareStatement(INSERT_QUERY)) {
                    insertStmt.setInt(1, customerId);
                    insertStmt.executeUpdate();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error ensuring pending order exists", e);
        }
    }

    /**
     * Thêm sản phẩm vào giỏ hàng.
     */
    public static void addToCart(int customerId, int productId) {
        String ADD_OR_UPDATE_ORDER_DETAIL =
                "INSERT INTO OrderDetail (OrderID, ProductID, Quantity, Price) " +
                        "SELECT o.OrderID, ?, 1, p.Price " +
                        "FROM Orders o, Product p " +
                        "WHERE o.CustomerID = ? AND o.OrderStatus = 'Pending' AND p.ProductID = ? " +
                        "ON DUPLICATE KEY UPDATE Quantity = Quantity + 1";

        String UPDATE_TOTAL_AMOUNT =
                "UPDATE Orders o " +
                        "SET TotalAmount = (SELECT SUM(od.Quantity * od.Price) " +
                        "                   FROM OrderDetail od " +
                        "                   WHERE od.OrderID = o.OrderID) " +
                        "WHERE o.CustomerID = ? AND o.OrderStatus = 'Pending'";

        try (Connection connection = BaseRepository.getConnection()) {
            connection.setAutoCommit(false);

            try (PreparedStatement detailStmt = connection.prepareStatement(ADD_OR_UPDATE_ORDER_DETAIL)) {
                detailStmt.setInt(1, productId);
                detailStmt.setInt(2, customerId);
                detailStmt.setInt(3, productId);
                detailStmt.executeUpdate();
            }

            try (PreparedStatement updateOrderStmt = connection.prepareStatement(UPDATE_TOTAL_AMOUNT)) {
                updateOrderStmt.setInt(1, customerId);
                updateOrderStmt.executeUpdate();
            }

            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error while adding to cart", e);
        }
    }

    /**
     * Lấy danh sách sản phẩm trong giỏ hàng.
     */
    public static List<Map<String, Object>> getCartItems(int customerId) {
        String QUERY =
                "SELECT p.ProductName, p.ImageURL, od.Quantity, od.Price " +
                        "FROM Orders o " +
                        "JOIN OrderDetail od ON o.OrderID = od.OrderID " +
                        "JOIN Product p ON od.ProductID = p.ProductID " +
                        "WHERE o.CustomerID = ? AND o.OrderStatus = 'Pending'";

        List<Map<String, Object>> cartItems = new ArrayList<>();
        try (Connection connection = BaseRepository.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {
            preparedStatement.setInt(1, customerId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Map<String, Object> item = new HashMap<>();
                item.put("ProductName", rs.getString("ProductName"));
                item.put("ImageURL", rs.getString("ImageURL"));
                item.put("Quantity", rs.getInt("Quantity"));
                item.put("Price", rs.getDouble("Price"));
                cartItems.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cartItems;
    }
}
