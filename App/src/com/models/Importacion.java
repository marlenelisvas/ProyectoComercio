/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models;

/**
 *
 * @author Jenny
 */
public class Importacion  {
    String DNI;
    int ID_IMPORTACION;

    public Importacion(String DNI, int ID_IMPORTACION) {
        this.DNI = DNI;
        this.ID_IMPORTACION = ID_IMPORTACION;
    }

    public String getDNI() {
        
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public int getID_IMPORTACION() {
        return ID_IMPORTACION;
    }

    public void setID_IMPORTACION(int ID_IMPORTACION) {
        this.ID_IMPORTACION = ID_IMPORTACION;
    }
    
}
