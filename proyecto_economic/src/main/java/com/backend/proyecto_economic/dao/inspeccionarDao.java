package com.backend.proyecto_economic.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.backend.proyecto_economic.config.configDB;
import java.sql.*;

public class inspeccionarDao { //Esta clase nos permite realizar una inspeccion de la conexion con la base de datos.
    public void mostrarEstructura() { //Este metodo muestra las tablas y vistas
        try (Connection con = configDB.iniciarConexionDB()) {
            DatabaseMetaData metaData = con.getMetaData();

            String[] types = { "TABLE", "VIEW" };
            ResultSet tables = metaData.getTables("economic", null, "%", types);

            System.out.println("\n--- TABLAS Y VISTAS DETECTADAS EN 'ECONOMIC' ---");
            while (tables.next()) {
                String name = tables.getString("TABLE_NAME");
                String type = tables.getString("TABLE_TYPE");
                System.out.println("[" + type + "]: " + name);
            }
        } catch (SQLException e) {
            System.err.println("Error al inspeccionar BD: " + e.getMessage());
        }
    }
    
    
    /**
    * Realiza una consulta a la base de datos para obtener la informacion de una tabla.
    * Extrae información específica ejemplo (ID, Nombre y Email) y la muestra por consola.
    * * @throws SQLException Si ocurre un error al acceder a la base de datos o en la consulta SQL.
    */
    public void consultarDatos(){
        String sql = "SELECT ID_categoria, Tipo_transaccion, Nombre_categoria FROM categoria"; //Definimos la consulta 
        // Iniciamos la conexión y los recursos dentro de un try-with-resources para cierre automático
        try (Connection con = configDB.iniciarConexionDB();
        PreparedStatement datos = con.prepareStatement(sql);
        ResultSet rs = datos.executeQuery()) {
            System.out.println("--- Datos obtenidos de la base de datos ---");
            while (rs.next()) {
                //Se toman los resultados y se imprimen
                System.out.println("ID: " + rs.getInt("ID_categoria") + " | tipo: " + rs.getString("Tipo_transaccion") + " | nombre: $" + rs.getString("Nombre_categoria"));
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar datos: " + e.getMessage());
        }
    }
}
