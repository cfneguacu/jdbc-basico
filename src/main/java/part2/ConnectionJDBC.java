package part2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionJDBC {
    public static void main(String[] args) {

        String driver = "mysql";
        String dataBaseAddress = "localhost";
        String dataBaseName = "digital_innovation_one?useTimezone=true&serverTimezone=UTC";
        String user = "root";
        String password = "@Neves123";

        StringBuilder sb = new StringBuilder("jdbc:")
                .append(driver).append("://")
                .append(dataBaseAddress).append("/")
                .append(dataBaseName);
        String connectionURL = sb.toString();
        try (Connection conn = DriverManager.getConnection(connectionURL, user,password)){
                System.out.println("SUCESSO ao conectar ao Banco MySQL");
        }catch(SQLException e){
            System.out.println("FALHA ao se conectar ao Banco MySQL!");
            e.printStackTrace();
        }
    }
}
