package repository;

import model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {
    private static final String SELECT_ALL_CUSTOMERS = "SELECT * FROM Customer";
    private static final String INSERT_CUSTOMER = "INSERT INTO Customer (Name, Phone, Email, Address, Gender, Password) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_CUSTOMER = "UPDATE Customer SET Name = ?, Phone = ?, Email = ?, Address = ?, Gender = ?, Password = ? WHERE CustomerID = ?";
    private static final String DELETE_CUSTOMER = "DELETE FROM Customer WHERE CustomerID = ?";
    private static final String CHECK_DUPLICATE_EMAIL = "SELECT COUNT(*) FROM Customer WHERE Email = ?";
    private static final String CHECK_DUPLICATE_PHONE = "SELECT COUNT(*) FROM Customer WHERE Phone = ?";
    private static final String CHECK_EMAIL_DUPLICATE = "SELECT COUNT(*) FROM Customer WHERE Email = ?";
    private static final String CHECK_PASSWORD_DUPLICATE = "SELECT COUNT(*) FROM Customer WHERE Password = ?";

    public static List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        try (Connection connection = BaseRepository.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CUSTOMERS)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("CustomerID");
                String name = rs.getString("Name");
                String phone = rs.getString("Phone");
                String email = rs.getString("Email");
                String address = rs.getString("Address");
                String gender = rs.getString("Gender");
                String password = rs.getString("Password");

                customers.add(new Customer(id, name, phone, email, address, gender, password));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customers;
    }
    public static boolean isEmailDuplicate(String email) {
        try (Connection connection = BaseRepository.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CHECK_EMAIL_DUPLICATE)) {

            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0; // Trả về true nếu email tồn tại
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isPasswordDuplicate(String password) {
        try (Connection connection = BaseRepository.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CHECK_PASSWORD_DUPLICATE)) {

            preparedStatement.setString(1, password);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0; // Trả về true nếu mật khẩu tồn tại
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public static void addCustomer(Customer customer) {
        try (Connection connection = BaseRepository.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CUSTOMER)) {

            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getPhone());
            preparedStatement.setString(3, customer.getEmail());
            preparedStatement.setString(4, customer.getAddress());
            preparedStatement.setString(5, customer.getGender());
            preparedStatement.setString(6, customer.getPassword());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateCustomer(Customer customer) {
        try (Connection connection = BaseRepository.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CUSTOMER)) {

            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getPhone());
            preparedStatement.setString(3, customer.getEmail());
            preparedStatement.setString(4, customer.getAddress());
            preparedStatement.setString(5, customer.getGender());
            preparedStatement.setString(6, customer.getPassword());
            preparedStatement.setInt(7, customer.getCustomerId());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteCustomer(int customerId) {
        try (Connection connection = BaseRepository.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CUSTOMER)) {
            preparedStatement.setInt(1, customerId);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static boolean isPhoneDuplicate(String phone) {
        try (Connection connection = BaseRepository.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CHECK_DUPLICATE_PHONE)) {
            preparedStatement.setString(1, phone);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Customer getCustomerByEmail(String email) {
        String query = "SELECT * FROM Customer WHERE Email = ?";
        try (Connection connection = BaseRepository.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                return new Customer(
                        rs.getInt("CustomerID"),
                        rs.getString("Name"),
                        rs.getString("Phone"),
                        rs.getString("Email"),
                        rs.getString("Address"),
                        rs.getString("Gender"),
                        rs.getString("Password")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // Trả về null nếu không tìm thấy khách hàng
    }

}