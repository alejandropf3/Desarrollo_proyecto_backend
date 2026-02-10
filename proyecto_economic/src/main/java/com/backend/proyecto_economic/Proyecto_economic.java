/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.backend.proyecto_economic;

//import java.sql.Connection;
import com.backend.proyecto_economic.config.configDB;
import com.backend.proyecto_economic.dao.inspeccionarDao;

/**
 *
 * @author Propietario
 */
public class Proyecto_economic {

    public static void main(String[] args) {
        configDB.iniciarConexionDB();
        inspeccionarDao inspector = new inspeccionarDao();
        inspector.mostrarEstructura();
    }
}
