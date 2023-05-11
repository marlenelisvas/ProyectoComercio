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
public class Exportacion {
    String DNI;
    int ID_Exportacion;

    public Exportacion(String DNI, int ID_Exportacion) {
        this.DNI = DNI;
        this.ID_Exportacion = ID_Exportacion;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public int getID_Exportacion() {
        return ID_Exportacion;
    }

    public void setID_Exportacion(int ID_Exportacion) {
        this.ID_Exportacion = ID_Exportacion;
    }
    
}
