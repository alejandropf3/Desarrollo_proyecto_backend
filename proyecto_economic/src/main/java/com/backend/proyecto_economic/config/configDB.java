/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.backend.proyecto_economic.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class configDB {
    private static String URL = "jdbc:mysql://localhost:3306/economic";
    private static String USER = "root";
    private static String PASSWD = "#Aprendiz2024";

    /**
    * Clase de configuración para la gestión de la base de datos.
    * Proporciona un punto único de acceso para establecer conexiones con el motor MySQL
    * para el proyecto 'economic'.
    */
    public static Connection iniciarConexionDB() {
        
        try {
            //Registro explícito del Driver (Vital para Tomcat)
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection connection = DriverManager.getConnection(URL, USER, PASSWD);
            System.out.println("Conexion establecida con exito.");
            return connection;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        catch (ClassNotFoundException e) {
            System.err.println("ERROR: No se encontró el conector de MySQL. Verifica tu pom.xml.");
            return null;
        }
    }
}
