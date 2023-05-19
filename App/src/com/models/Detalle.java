/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models;

import com.jdbc.ConexionJDBC;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Jenny
 */
public class Detalle {
    int ID_DETALLE;
    Factura FACTURA;
    Producto PRODUCTO;
    int UNIDADES;
    double TOTAL;

    public Detalle(int ID_DETALLE, Factura FACTURA, Producto PRODUCTO, int UNIDADES, double TOTAL) {
        this.ID_DETALLE = ID_DETALLE;
        this.FACTURA = FACTURA;
        this.PRODUCTO = PRODUCTO;
        this.UNIDADES = UNIDADES;
        this.TOTAL = TOTAL;
    }

    
    public Detalle() {
    }

    public Factura getFACTURA() {
        return FACTURA;
    }

    public void setFACTURA(Factura FACTURA) {
        this.FACTURA = FACTURA;
    }

    public Producto getPRODUCTO() {
        return PRODUCTO;
    }

    public void setPRODUCTO(Producto PRODUCTO) {
        this.PRODUCTO = PRODUCTO;
    }

    public int getUNIDADES() {
        return UNIDADES;
    }

    public void setUNIDADES(int UNIDADES) {
       this.TOTAL = this.PRODUCTO.getPRECIO()*UNIDADES;
        this.UNIDADES = UNIDADES;
    }

    public double getTOTAL() {
        
        return this.PRODUCTO.getPRECIO()*this.UNIDADES;
    }

    public void setTOTAL(double TOTAL) {
        this.TOTAL = TOTAL;
    }

 
    public int getID_DETALLE() {
        return ID_DETALLE;
    }

    public void setID_DETALLE(int ID_DETALLE) {
        this.ID_DETALLE = ID_DETALLE;
    }
    //=====================================================================
    public boolean insert() {
    //=====================================================================
        ConexionJDBC _con = new ConexionJDBC();
        boolean _res = false;
        try {
            Statement query = _con.getConexion().createStatement();
            //ResultSet no vale para el insert y executeQuery no sirve para actualizar base de datos
             
            int res = query.executeUpdate("INSERT INTO detalles (ID_DETALLES,ID_FACTURA, ID_PRODUCTO,UNIDADES,TOTAL)"
                    + "VALUES(null, '" + this.FACTURA.getId_factura() + "', '" + this.PRODUCTO.getID_PRODUCTO() + "', " + this.UNIDADES + ",'"+this.TOTAL+"')");
           
            _res = true;
            _con.getConexion().close();
        } catch (SQLException ex) {
            
            throw new RuntimeException(ex);
         
        }
        return _res;
    }
//=====================================================================
    public boolean delete() {
//=====================================================================
         ConexionJDBC _con = new ConexionJDBC();
        boolean _res = false;
        try {
            Statement query = _con.getConexion().createStatement();
            //ResultSet no vale para el insert y executeQuery no sirve para actualizar base de datos
            int res = query.executeUpdate("Delete from detalles WHERE ID_DETALLES="+this.ID_DETALLE);
           
            _res = true;
            _con.getConexion().close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
             
            //throw new RuntimeException(ex.getMessage());
        }
        return _res;
    }


    public void imprimir(){
        System.out.println("ID_DETALLE: "+ this.ID_DETALLE +
        "\nFACTURA: "+this.FACTURA +
        "\nPRODUCTO: "+this.PRODUCTO  +
        "\nUNIDADES: "+this.UNIDADES +
        "\nTOTAL: "+ this.TOTAL );
    }
   
}
