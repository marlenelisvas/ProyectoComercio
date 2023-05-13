/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.json.JSONArray;
import org.json.JSONObject;
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
    public void ImprimirFactura(){
        Template tempImp = new Template();
        
        // traer template
        try {
            XWPFDocument doc = new XWPFDocument(  tempImp.getTemplate("COD_IMPORT"));
            
            
        } catch (IOException ex) {
            Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    private void replaceParagraphTags(List<XWPFParagraph> paragraphs, JSONArray requestTagsArray) {
		// To replace Tags in Paragraphs
		List<XWPFRun> runs;
		String text;
		JSONObject tagObject;
		for (XWPFParagraph p : paragraphs) {
			runs = p.getRuns();
			if (runs != null) {
				for (XWPFRun r : runs) {
					text = r.getText(0);
					System.out.println(text);
					for (int i = 0; i < requestTagsArray.length(); i++) {
						tagObject = requestTagsArray.getJSONObject(i);
						if (text != null && text.contains(tagObject.getString("tag"))) {
							text = text.replace(tagObject.getString("tag"), tagObject.getString("value"));// replacing
																											// tag
																											// key
																											// with
																											// tag
																											// value
							r.setText(text, 0); // setting The text to 'run' in the same document
						}
					}
				}
			}
		}
	}
}

