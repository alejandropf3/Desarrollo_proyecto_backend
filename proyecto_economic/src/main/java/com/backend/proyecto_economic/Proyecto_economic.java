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
        inspeccionarDao inspector = new inspeccionarDao(); //Creamos el objeto inspector y datosDB
        
        configDB.iniciarConexionDB(); //Inicializador de conexion con la base de datos
        inspector.mostrarEstructura(); //Mostramos la estructura de la base de datos
        inspector.consultarDatos(); //Mostramos un dato ingresado en la base de datos
    }
}
