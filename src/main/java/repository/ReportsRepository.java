package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportsRepository {

    private static final String QUERY_ORDERS_WITH_DETAILS =
                    "SELECT o.CustomerId, o.OrderDate, o.TotalAmount, o.OrderStatus, " +
                    "od.ProductId, od.Quantity, od.Price " +
                    "FROM Orders o " +
                    "LEFT JOIN OrderDetail od ON o.OrderId = od.OrderId";

    public static List<Map<String, Object>> getOrdersWithDetailsMerged() {
        List<Map<String, Object>> mergedData = new ArrayList<>();
        try (Connection connection = BaseRepository.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY_ORDERS_WITH_DETAILS)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("CustomerId", rs.getInt("CustomerId"));
                row.put("OrderDate", rs.getDate("OrderDate"));
                row.put("TotalAmount", rs.getDouble("TotalAmount"));
                row.put("OrderStatus", rs.getString("OrderStatus"));
                row.put("ProductId", rs.getInt("ProductId"));
                row.put("Quantity", rs.getInt("Quantity"));
                row.put("Price", rs.getDouble("Price"));

                mergedData.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mergedData;
    }

    public static void main(String[] args) {
        System.out.println(getOrdersWithDetailsMerged().size());
    }
}
