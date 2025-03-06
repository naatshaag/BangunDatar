package bangun_datar;
import java.sql.*;

class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/bangun_datar";
    private static final String USER = "root"; // Ganti sesuai dengan username MySQL Anda
    private static final String PASSWORD = ""; // Jika ada password, isi di sini

    public static void initDB() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS bangun_datar ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "nama VARCHAR(255),"
                    + "dimensi1 DOUBLE,"
                    + "dimensi2 DOUBLE,"
                    + "luas DOUBLE,"
                    + "keliling DOUBLE)";
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public static void simpanKeDB(String nama, double dimensi1, double dimensi2, double luas, double keliling) {
        String sql = "INSERT INTO bangun_datar (nama, dimensi1, dimensi2, luas, keliling) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nama);
            pstmt.setDouble(2, dimensi1);
            pstmt.setDouble(3, dimensi2);
            pstmt.setDouble(4, luas);
            pstmt.setDouble(5, keliling);
            pstmt.executeUpdate();
            System.out.println("Data berhasil disimpan!");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void tampilkanData() {
        String sql = "SELECT * FROM bangun_datar";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("ID | Nama | Dimensi1 | Dimensi2 | Luas | Keliling");
            while (rs.next()) {
                System.out.printf("%d | %s | %.2f | %.2f | %.2f | %.2f\n",
                        rs.getInt("id"), rs.getString("nama"),
                        rs.getDouble("dimensi1"), rs.getDouble("dimensi2"),
                        rs.getDouble("luas"), rs.getDouble("keliling"));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void hapusData(int id) {
        String sql = "DELETE FROM bangun_datar WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Data dengan ID " + id + " berhasil dihapus.");
            } else {
                System.out.println("Data tidak ditemukan.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void updateData(int id, String nama, double dimensi1, double dimensi2, double luas, double keliling) {
        String sql = "UPDATE bangun_datar SET nama = ?, dimensi1 = ?, dimensi2 = ?, luas = ?, keliling = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nama);
            pstmt.setDouble(2, dimensi1);
            pstmt.setDouble(3, dimensi2);
            pstmt.setDouble(4, luas);
            pstmt.setDouble(5, keliling);
            pstmt.setInt(6, id);
            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Data berhasil diperbarui.");
            } else {
                System.out.println("Data tidak ditemukan.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
