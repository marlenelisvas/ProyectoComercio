/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @Jenny 
 */
public class ConexionJDBC {
     Connection con;
    public Connection getConexion() {
        {
            try {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/aeropuerto", "root", "");
                System.out.println("OK");
            } catch (SQLException e) {
                System.out.println("Error");
                throw new RuntimeException(e);

            }
        }
        return con;
    }
    
}
