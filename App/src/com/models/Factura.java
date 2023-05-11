/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models;

import java.util.Date;
enum Pago {EFECTIVO,DEBITO,CREDITO, CHEQUE}
/**
 *
 * @author Jenny
 */
public class Factura {
    int Id_factura;
    int Id_Importacion;
    int Id_Exportacion;
    Date Fecha;
    float Base_Imponible;
    Pago COND_PAGO ;
    float IRPF;
    float IVA;

    public Factura(int Id_factura, int Id_Importacion, int Id_Exportacion, Date Fecha, float Base_Imponible, Pago COND_PAGO, float IRPF, float IVA) {
        this.Id_factura = Id_factura;
        this.Id_Importacion = Id_Importacion;
        this.Id_Exportacion = Id_Exportacion;
        this.Fecha = Fecha;
        this.Base_Imponible = Base_Imponible;
        this.COND_PAGO = COND_PAGO;
        this.IRPF = IRPF;
        this.IVA = IVA;
    }

    public int getId_factura() {
        return Id_factura;
    }

    public void setId_factura(int Id_factura) {
        this.Id_factura = Id_factura;
    }

    public int getId_Importacion() {
        return Id_Importacion;
    }

    public void setId_Importacion(int Id_Importacion) {
        this.Id_Importacion = Id_Importacion;
    }

    public int getId_Exportacion() {
        return Id_Exportacion;
    }

    public void setId_Exportacion(int Id_Exportacion) {
        this.Id_Exportacion = Id_Exportacion;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date Fecha) {
        this.Fecha = Fecha;
    }

    public float getBase_Imponible() {
        return Base_Imponible;
    }

    public void setBase_Imponible(float Base_Imponible) {
        this.Base_Imponible = Base_Imponible;
    }

    public Pago getCOND_PAGO() {
        return COND_PAGO;
    }

    public void setCOND_PAGO(Pago COND_PAGO) {
        this.COND_PAGO = COND_PAGO;
    }

    public float getIRPF() {
        return IRPF;
    }

    public void setIRPF(float IRPF) {
        this.IRPF = IRPF;
    }

    public float getIVA() {
        return IVA;
    }

    public void setIVA(float IVA) {
        this.IVA = IVA;
    }
}

