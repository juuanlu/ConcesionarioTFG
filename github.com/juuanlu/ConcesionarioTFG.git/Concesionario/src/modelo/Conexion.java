package modelo;
import java.sql.*;

public class Conexion {
	
    public static Connection Conectar() {
        Connection cn = null;

        try {
            Class.forName("org.postgresql.Driver");
            cn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/concesionario", "postgres", "admin");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return cn;
    }
    
}