/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.panels;

import com.jdbc.ConexionJDBC;
import com.models.Template;
import com.models.Usuario;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Jenny
 */
public class CargarPantillaImportacion extends javax.swing.JPanel {
   ConexionJDBC con =  new ConexionJDBC();
   Template template ;
    /**
     * Creates new form CargarPantillaImportacion
     */
    public CargarPantillaImportacion() {
        initComponents();
        this.cargarDatos();
        
        
    }
    public void cargarDatos(){
     //=====================================================================    
  
        ConexionJDBC _con = new ConexionJDBC();  
         this.lbl_nombreTemplate.setText("");
         try {
                Statement query = _con.getConexion().createStatement();
                ResultSet res = query.executeQuery("Select * from template WHERE ID='COD_IMPORT'");
              
               
                while (res.next()){     
                    this.template = new Template();
                    template.setNombre(res.getString("Nombre"));
                    this.template.setId(res.getString("ID"));
                    this.lbl_nombreTemplate.setText(res.getString("Nombre"));                           
                   
                }
                if(this.lbl_nombreTemplate.getText().equals("")){
                    this.btn_cargarPlantillaImportacion.setVisible(true);
                    this.btn_eliminarTemplate.setVisible(false);
                    this.lbl_nombreTemplate.setText("");
                }
                else{
                    this.btn_cargarPlantillaImportacion.setVisible(false);
                    this.btn_eliminarTemplate.setVisible(true);
                }
                    
                _con.getConexion().close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }      
          
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_cargarPlantillaImportacion = new javax.swing.JButton();
        lbl_nombreTemplate = new javax.swing.JLabel();
        btn_eliminarTemplate = new javax.swing.JButton();

        btn_cargarPlantillaImportacion.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_cargarPlantillaImportacion.setText("Cargar Plantilla Importación");
        btn_cargarPlantillaImportacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cargarPlantillaImportacionActionPerformed(evt);
            }
        });

        lbl_nombreTemplate.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lbl_nombreTemplate.setForeground(new java.awt.Color(0, 51, 255));

        btn_eliminarTemplate.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_eliminarTemplate.setForeground(new java.awt.Color(255, 0, 51));
        btn_eliminarTemplate.setText("Eliminar Template");
        btn_eliminarTemplate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarTemplateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_nombreTemplate, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_eliminarTemplate, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_cargarPlantillaImportacion))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbl_nombreTemplate, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_eliminarTemplate, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(btn_cargarPlantillaImportacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(188, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cargarPlantillaImportacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cargarPlantillaImportacionActionPerformed
           
       String nombre = JOptionPane.showInputDialog(null, "Nombre", "");
        
        JFileChooser seleccionar= new JFileChooser();
        seleccionar.setFileSelectionMode(JFileChooser.FILES_ONLY);
        
        int estado = seleccionar.showOpenDialog(null);
        if(estado == JFileChooser.APPROVE_OPTION){
            File archivo = seleccionar.getSelectedFile();
            Template temp = new Template("COD_IMPORT", nombre, archivo);
            if(temp.insertImportacion()){
                this.cargarDatos();
                JOptionPane.showMessageDialog(null, "Se ha cargado el archivo correctamente.",
                "Válido", JOptionPane.PLAIN_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, "No se ha cargado el archivo.",
                "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btn_cargarPlantillaImportacionActionPerformed

    private void btn_eliminarTemplateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarTemplateActionPerformed
       
        
        if(template.delete()){
            this.cargarDatos();
            JOptionPane.showMessageDialog(null, "Se ha eliminado el archivo correctamente.",
                "Válido", JOptionPane.PLAIN_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, "No se ha eliminado el archivo.",
                "Error", JOptionPane.ERROR_MESSAGE);
            }
    }//GEN-LAST:event_btn_eliminarTemplateActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cargarPlantillaImportacion;
    private javax.swing.JButton btn_eliminarTemplate;
    private javax.swing.JLabel lbl_nombreTemplate;
    // End of variables declaration//GEN-END:variables
}
