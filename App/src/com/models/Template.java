/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models;

import com.jdbc.ConexionJDBC;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author developer
 */
public class Template {

    String Id;
    String Nombre;
    File file;
    InputStream _file;

    public Template() {
    }

    public Template(String Id, String Nombre, File file) {
        this.Id = Id;
        this.Nombre = Nombre;
        this.file = file;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    //******************* operaciones con la BBDD *************************
//=====================================================================
    public boolean insertImportacion() {
//=====================================================================
        ConexionJDBC _con = new ConexionJDBC();
        boolean _res = false;
        long tamanio = this.file.length();
        try {
            FileInputStream inputStream = new FileInputStream(this.file);
            PreparedStatement ps = _con.getConexion().prepareCall("INSERT INTO template (ID, NOMBRE, TEMPLATE) VALUES(?,?,?)");
            ps.setString(1, "COD_IMPORT");
            ps.setString(2, this.Nombre);
            ps.setBlob(3, inputStream, tamanio);
            ps.execute();
            _res = true;
            _con.getConexion().close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Template.class.getName()).log(Level.SEVERE, null, ex);
        }
        return _res;
    }

    //=====================================================================
    public boolean insertExportacion() {
//=====================================================================
        ConexionJDBC _con = new ConexionJDBC();
        boolean _res = false;
        long tamanio = this.file.length();
        try {
            FileInputStream inputStream = new FileInputStream(this.file);
            PreparedStatement ps = _con.getConexion().prepareCall("INSERT INTO template (ID, NOMBRE, TEMPLATE) VALUES(?,?,?)");
            ps.setString(1, "COD_EXPORT");
            ps.setString(2, this.Nombre);
            ps.setBlob(3, inputStream, tamanio);
            ps.execute();
            _res = true;
            _con.getConexion().close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Template.class.getName()).log(Level.SEVERE, null, ex);
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
            int res = query.executeUpdate("delete from template where ID='" + this.Id + "'");

            _res = true;
            _con.getConexion().close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return _res;
    }

    public InputStream getTemplate(String id) {
        ConexionJDBC _con = new ConexionJDBC();
        boolean _res = false;

        try {

            Statement query = _con.getConexion().createStatement();
            ResultSet res = query.executeQuery("select * from template where ID='" + id + "'");

            while (res.next()) {

                this.setNombre(res.getString("Nombre"));
                this.setId(res.getString("ID"));
                Blob blob = res.getBlob("Template");
                this._file = blob.getBinaryStream();

            }
           
            _res = true;
            _con.getConexion().close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return this._file;
    }

    @Override
    public String toString() {
           return "ID: "+ this.Id+"\nNombre: "+this.Nombre;
    }

}
