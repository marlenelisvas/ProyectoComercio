/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models;

import com.jdbc.ConexionJDBC;
import com.utils.Utils;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Jenny    
 */
public class Producto {
    int ID_PRODUCTO;
    String DESCRIPCION;
    double PRECIO;
    public Producto(){}
    public Producto(int ID_PRODUCTO, String DESCRIPCION, double PRECIO) {
        this.ID_PRODUCTO = ID_PRODUCTO;
        this.DESCRIPCION = DESCRIPCION;
        this.PRECIO = PRECIO;
    }
    public Producto(String DESCRIPCION, double PRECIO) {        
        this.DESCRIPCION = DESCRIPCION;
        this.PRECIO = PRECIO;
    }
    public int getID_PRODUCTO() {
        return ID_PRODUCTO;
    }

    public void setID_PRODUCTO(int ID_PRODUCTO) {
        this.ID_PRODUCTO = ID_PRODUCTO;
    }

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }

    public double getPRECIO() {
        return PRECIO;
    }

    public void setPRECIO(int PRECIO) {
        this.PRECIO = PRECIO;
    }
    
    //******************* operaciones con la BBDD *************************
  
//=====================================================================
    public boolean insert() {
//=====================================================================
        ConexionJDBC _con = new ConexionJDBC();
        boolean _res = false;
        try {
            Statement query = _con.getConexion().createStatement();
            //ResultSet no vale para el insert y executeQuery no sirve para actualizar base de datos
             
            int res = query.executeUpdate("INSERT INTO productos (ID_PRODUCTO,DESCRIPCION, PRECIO)"
                    + "VALUES('" + this.ID_PRODUCTO + "', '" + this.DESCRIPCION + "', " + this.PRECIO + ")");
           System.out.println("response: "+res);
            _res = true;
            _con.getConexion().close();
        } catch (SQLException ex) {
            
            throw new RuntimeException(ex);
         
        }
        return _res;
    }
//=====================================================================
    public boolean update() {
//=====================================================================
         ConexionJDBC _con = new ConexionJDBC();
        boolean _res = false;
        try {
            Statement query = _con.getConexion().createStatement();
            //ResultSet no vale para el insert y executeQuery no sirve para actualizar base de datos
            int res = query.executeUpdate("UPDATE  productos SET DESCRIPCION ='"+ this.DESCRIPCION +"', PRECIO= "+this.PRECIO +" WHERE ID_PRODUCTO="+this.ID_PRODUCTO);
            System.out.println("response: "+ res);
            _res = true;
            _con.getConexion().close();
        } catch (SQLException ex) {
        
            throw new RuntimeException(ex.getMessage());
        }
        return _res;
    }
//=====================================================================
    public boolean delete(String id) {
//=====================================================================
         ConexionJDBC _con = new ConexionJDBC();
        boolean _res = false;
        try {
            Statement query = _con.getConexion().createStatement();
            //ResultSet no vale para el insert y executeQuery no sirve para actualizar base de datos
            int res = query.executeUpdate("Delete from productos WHERE ID_PRODUCTO="+Integer.parseInt(id));
           
            _res = true;
            _con.getConexion().close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
             JOptionPane.showMessageDialog(null, "error",
                "Error", JOptionPane.ERROR);
            //throw new RuntimeException(ex.getMessage());
        }
        return _res;
    }

//=====================================================================    
    public void consultar(int id) {
//=====================================================================        
         ConexionJDBC _con = new ConexionJDBC();   
          this.ID_PRODUCTO = id;    
         try {
                Statement query = _con.getConexion().createStatement();
                ResultSet res = query.executeQuery("Select * from productos where ID_PRODUCTO='" + id +"'");
               
                while (res.next()){
                    this.ID_PRODUCTO = res.getInt("ID_PRODUCTO");       
                    this.DESCRIPCION = res.getString("DESCRIPCION");
                    this.PRECIO = res.getInt("PRECIO");
                }
                _con.getConexion().close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }       
    }
    //------------------------------------------------------------------
 
    @Override
    public String toString() {
        return "\nID_PRODUCTO: " + this.ID_PRODUCTO + "\nDESCRIPCION: " + this.DESCRIPCION + "\nPRECIO: " + this.PRECIO;
    }
}
    

