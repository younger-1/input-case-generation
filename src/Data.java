import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class Data {

    public static List<double[]> objectsData;
    public static List<double[]> satellitesData;

    public static void readFromDatabase(String[] args) throws SQLException {
        String JDBC_URL = "jdbc:mysql://localhost:3306/test?useSSL=false&characterEncoding=utf8";
        String JDBC_USER = "root";
        String JDBC_PASSWORD = "";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {

            // * 查询
            try (PreparedStatement ps = conn
                    .prepareStatement("SELECT id, grade, name, gender FROM students WHERE gender=? AND grade=?")) {
                ps.setObject(1, 1);
                ps.setObject(2, 3);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        long id = rs.getLong("id");
                        long grade = rs.getLong("grade");
                        String name = rs.getString("name");
                        int gender = rs.getInt("gender");
                        System.out.println(
                                String.format("id: %d, grade: %d, name: %s, gender: %d", id, grade, name, gender));
                    }
                }
            }

        }

    }
}
