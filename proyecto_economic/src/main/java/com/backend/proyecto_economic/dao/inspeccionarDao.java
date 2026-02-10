package com.backend.proyecto_economic.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.backend.proyecto_economic.config.configDB;
import java.sql.*;

public class inspeccionarDao {
    public void mostrarEstructura() {
        try (Connection con = configDB.iniciarConexionDB()) {
            DatabaseMetaData metaData = con.getMetaData();

            // 1. Ver Tablas y Vistas
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
}
