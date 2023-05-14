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

    public int getUNIDADES() {
        return UNIDADES;
    }

    public void setUNIDADES(int UNIDADES) {
        this.UNIDADES = UNIDADES;
    }

    public double getTOTAL() {
        
        return this.PRODUCTO.getPRECIO()*this.UNIDADES;
    }

    public void setTOTAL(int TOTAL) {
        this.TOTAL = TOTAL;
    }

 
    public int getID_DETALLE() {
        return ID_DETALLE;
    }

    public void setID_DETALLE(int ID_DETALLE) {
        this.ID_DETALLE = ID_DETALLE;
    }

}
