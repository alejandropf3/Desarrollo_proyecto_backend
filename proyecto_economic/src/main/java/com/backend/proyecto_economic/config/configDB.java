/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.backend.proyecto_economic.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Propietario
 */
public class configDB
{
     private static String URL = "jdbc:mysql://localhost:3306/economic";
    private static String USER = "root";
    private static String PASSWD = "#Aprendiz2024";

    public static Connection iniciarConexionDB(){
        
        try
        {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWD); 
            System.out.println("Conexion establecida con exito.");
            return connection;

        } catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
