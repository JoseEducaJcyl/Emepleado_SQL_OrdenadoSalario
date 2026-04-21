// Importes necesarios para el programa
import java.sql.*;

// Clase Main para la ejecucion del programa
public class Main {
    // El método main lanza SQLException (delega la gestión de errores)
    public static void main(String[] args) throws SQLException {
        // Datos para conectarse a la base de datos
        String url = "jdbc:oracle:thin:@//localhost:1521/xe"; // Cambia según tu BD
        String usuario = "JAVA";
        String contraseña = "12345";

        // Try-catch con recursos para intentar conectarse
        try (Connection conn = DriverManager.getConnection(url, usuario, contraseña);
             // Objeto Statement para ejecutar consultas SQL
             Statement stmt = conn.createStatement()) {

            // Consulta SQL que obtiene todos los empleados ordenados por salario de mayor a menor
            String sql = "SELECT ID, NOMBRE, SALARIO, DEPARTAMENTO_ID FROM EMPLEADO ORDER BY SALARIO DESC";

            // Ejecutar la consulta y obtener los resultados
            ResultSet rs = stmt.executeQuery(sql);

            // Recorrer todos los empleados y mostrarlos ordenados por salario
            while (rs.next()) {
                int id = rs.getInt("ID");
                String nombre = rs.getString("NOMBRE");
                int salario = rs.getInt("SALARIO");
                int departamento_id = rs.getInt("DEPARTAMENTO_ID");

                // Mostrar los datos de cada empleado
                System.out.println("Datos: ");
                System.out.println("ID: " + id);
                System.out.println("Nombre: " + nombre);
                System.out.println("Salario: " + salario);
                System.out.println("Departamento: " + departamento_id);
            }

        } catch(SQLException e){
            // Capturar y mostrar cualquier error de base de datos
            System.out.println("Error al mostrar la tabla: " + e.getMessage());
        }
    }
}