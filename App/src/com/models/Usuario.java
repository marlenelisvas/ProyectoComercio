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

/**
 *
 * @author Jenny
 */
public class Usuario {

    private String Dni;
    private String Nombre;
    private String Apellidos;
    private String Direccion1;
    private String Direccion2;

    public Usuario(){}
    public Usuario(String Dni, String Nombre, String Apellidos, String Direccion1, String Direccion2) {
        this.Dni = Dni;
        this.Nombre = Nombre;
        this.Apellidos = Apellidos;
        this.Direccion1 = Direccion1;
        this.Direccion2 = Direccion2;
    }

    public String getDni() {
        return Dni;
    }

    public void setDni(String Dni) {
        this.Dni = Dni;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String Apellidos) {
        this.Apellidos = Apellidos;
    }

    public String getDireccion1() {
        return Direccion1;
    }

    public void setDireccion1(String Direccion1) {
        this.Direccion1 = Direccion1;
    }

    public String getDireccion2() {
        return Direccion2;
    }

    public void setDireccion2(String Direccion2) {
        this.Direccion2 = Direccion2;
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
             
            int res = query.executeUpdate("INSERT INTO usuario (DNI, Nombre, Apellidos, Direccion_1,Direccion_2)"
                    + "VALUES('" + this.Dni + "', '" + this.Nombre + "', '" + this.Apellidos + "','" + this.Direccion1 + "','" + this.Direccion2 + "')");
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
            int res = query.executeUpdate("UPDATE  usuario SET Nombre ='"+ this.Nombre 
                                                    +"', Apellidos= '"+this.Apellidos
                                                    +"', Direccion_1='"+ this.Direccion1
                                                    +"', Direccion_2='"+ this.Direccion2
                                                    +"' where DNI='"+this.Dni+"'");
            System.out.println("response: "+res);
            _res = true;
            _con.getConexion().close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return _res;
    }

//=====================================================================    
    public void consultar(String dni) {
//=====================================================================        
         ConexionJDBC _con = new ConexionJDBC();   
          this.Dni = dni;    
         try {
                Statement query = _con.getConexion().createStatement();
                ResultSet res = query.executeQuery("Select * from usuario where DNI='" + dni +"'");
               
                while (res.next()){
                    System.out.println("DNI "+res.getString("DNI"));
                    this.Dni = res.getString("DNI");           
                    this.Nombre = res.getString("Nombre");
                    this.Apellidos = res.getString("Apellidos");
                    this.Direccion1 = res.getString("Direccion_1");
                    this.Direccion2 = res.getString("Direccion_2");
                }
                _con.getConexion().close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }       
    }
    //------------------------------------------------------------------
    public boolean validarDni(){
        Utils util = new Utils();
        return util.validarDni(this.Dni);
    }
    public boolean validarUsuario(){
      
        return (this.Nombre.equals("") || this.Dni.equals(""));
                //(this.Dni=="" && this.Nombre!=""&&this.Apellidos!=""&&this.Direccion1 ==""&& this.Direccion2!="");
    }
    @Override
    public String toString() {
        return "\nDNI: " + this.Dni + "\nNombre: " + this.Nombre + "\nApellido: " + this.Apellidos + "\nDireccion 1:" + this.Direccion1 + "\nDireccion 2: " + this.Direccion2;
    }
}
