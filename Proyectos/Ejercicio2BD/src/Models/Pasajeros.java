/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Conexion.ConexionJDBC;
import Utils.Utils;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Jenny
 */

     
public class Pasajeros {
   String DNI;
   int idVuelo;
   String Nombre;
   String Apellido1;
   String Apellido2;
   String Nacionalidad;
   int Edad;
   String sexo;

    public Pasajeros() {
    }

    public Pasajeros(String DNI, int idVuelo, String Nombre, String Apellido1, String Apellido2, String Nacionalidad, int Edad, String sexo) {
        this.DNI = DNI;
        this.idVuelo = idVuelo;
        this.Nombre = Nombre;
        this.Apellido1 = Apellido1;
        this.Apellido2 = Apellido2;
        this.Nacionalidad = Nacionalidad;
        this.Edad = Edad;
        this.sexo = sexo;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }





    public int getIdVuelo() {
        return idVuelo;
    }

    public void setIdVuelo(int idVuelo) {
        this.idVuelo = idVuelo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellido1() {
        return Apellido1;
    }

    public void setApellido1(String Apellido1) {
        this.Apellido1 = Apellido1;
    }

    public String getApellido2() {
        return Apellido2;
    }

    public void setApellido2(String Apellido2) {
        this.Apellido2 = Apellido2;
    }

    public String getNacionalidad() {
        return Nacionalidad;
    }

    public void setNacionalidad(String Nacionalidad) {
        this.Nacionalidad = Nacionalidad;
    }

    public int getEdad() {
        return Edad;
    }

    public void setEdad(int Edad) {
        this.Edad = Edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
//===================================================================
    public boolean insert() {
//=====================================================================
        ConexionJDBC _con = new ConexionJDBC();
        boolean _res = false;
        try {
            Statement query = _con.getConexion().createStatement();
            //ResultSet no vale para el insert y executeQuery no sirve para actualizar base de datos
             
            int res = query.executeUpdate("INSERT INTO pasajeros (DNI, Id_vuelo,Nombre,Apellido1,Apellido2,Nacionalidad, Edad, Sexo)"
                    + "VALUES('" + this.DNI + "','" + this.idVuelo + "', '" + this.Nombre + "', '" + this.Apellido1 + "','" + this.Nacionalidad+ "',"
                            + "'" + this.Edad + "', '" + this.sexo + "')");
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
                                                    +"', Apellidos= '"+this.Apellido1
                                                    +"', Apellidos= '"+this.Apellido2    
                                                    +"', Nacionalidad='"+ this.Nacionalidad
                                                    +"', Edad='"+ this.Edad
                                                    +"', Sexo='"+this.sexo
                                                    +"' where DNI='"+this.DNI+"'");
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
          this.DNI = dni;    
         try {
                Statement query = _con.getConexion().createStatement();
                ResultSet res = query.executeQuery("Select * from pasajero where DNI='" + dni +"'");
               
                while (res.next()){
                    System.out.println("DNI "+res.getString("DNI"));
                    this.DNI = res.getString("DNI");     
                    this.idVuelo = res.getInt("id_Vuelo");
                    this.Nombre = res.getString("Nombre");
                    this.Apellido1 = res.getString("Apellido1");
                    this.Apellido2 = res.getString("Apellido2");
                    this.Nacionalidad = res.getString("Nacionalidad");
                    this.Edad = res.getInt("Edad");
                    this.sexo = res.getString("Sexo");
                }
                _con.getConexion().close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }       
    }
    //------------------------------------------------------------------
    public boolean validarDni(){
        Utils util = new Utils();
        return util.validarDni(this.DNI);
    }
    public boolean validarUsuario(){
      
        return (this.Nombre.equals("") || this.DNI.equals(""));
                //(this.Dni=="" && this.Nombre!=""&&this.Apellidos!=""&&this.Direccion1 ==""&& this.Direccion2!="");
    }
    @Override
    public String toString() {
        return "Pasajeros{" + "dni=" + DNI+ ", idVuelo=" + idVuelo + ", Nombre=" + Nombre + ", Apellido1=" + Apellido1 + ", Apellido2=" + Apellido2 + ", Nacionalidad=" + Nacionalidad + ", Edad=" + Edad + ", sexo=" + sexo + '}';
    }


   
      
}
