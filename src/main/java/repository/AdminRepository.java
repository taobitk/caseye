package repository;

import model.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AdminRepository {
    private static final String SELECT_ALL_USENAME = "SELECT* FROM Admin;";
    private static final String VALIDATE_ADMIN = "SELECT * FROM Admin WHERE Username = ? AND Password = ?";

    public static List<Admin> getUsenamePassword() {
        List<Admin> admins = new ArrayList<>();
        try {
            Connection connection = BaseRepository.getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_USENAME);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int adminID = rs.getInt("adminID");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                admins.add(new Admin(adminID,username,password,email));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return admins;
    }
    public static boolean validateAdmin(String username, String password) {
        try {
            Connection connection = BaseRepository.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(VALIDATE_ADMIN);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return true;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
