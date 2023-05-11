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
public class Detalle {
    int ID_DETALLE;
    int ID_FACTURA;
    int ID_PRODUCTO;

    public Detalle(int ID_DETALLE, int ID_FACTURA, int ID_PRODUCTO) {
        this.ID_DETALLE = ID_DETALLE;
        this.ID_FACTURA = ID_FACTURA;
        this.ID_PRODUCTO = ID_PRODUCTO;
    }

    public int getID_DETALLE() {
        return ID_DETALLE;
    }

    public void setID_DETALLE(int ID_DETALLE) {
        this.ID_DETALLE = ID_DETALLE;
    }

    public int getID_FACTURA() {
        return ID_FACTURA;
    }

    public void setID_FACTURA(int ID_FACTURA) {
        this.ID_FACTURA = ID_FACTURA;
    }

    public int getID_PRODUCTO() {
        return ID_PRODUCTO;
    }

    public void setID_PRODUCTO(int ID_PRODUCTO) {
        this.ID_PRODUCTO = ID_PRODUCTO;
    }

    
}
