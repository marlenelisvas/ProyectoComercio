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
public class Producto {
    int ID_PRODUCTO;
    String DESCRIPCION;
    int PRECIO;

    public Producto(int ID_PRODUCTO, String DESCRIPCION, int PRECIO) {
        this.ID_PRODUCTO = ID_PRODUCTO;
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

    public int getPRECIO() {
        return PRECIO;
    }

    public void setPRECIO(int PRECIO) {
        this.PRECIO = PRECIO;
    }
    
}
