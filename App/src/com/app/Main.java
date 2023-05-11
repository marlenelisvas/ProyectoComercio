/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app;

import com.jdbc.ConexionJDBC;
import com.panels.Factura_Importacion;
import com.panels.Productos;
import com.panels.Tiles;
import com.utils.Utils;
import java.awt.Frame;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 
 */
public class Main extends javax.swing.JFrame {
    ConexionJDBC con;
    Tiles tiles = new Tiles();
    Factura_Importacion factura= new Factura_Importacion();
    Productos productos= new Productos();
    Utils util = new Utils();
    /**
     * Creates new form frm_Main
     */
    public Main() {
        initComponents();
        setBounds(100,100,450,300);
       setTitle("Sistema de Gestión de Facturas");
       this.setExtendedState(Frame.MAXIMIZED_BOTH);
        util =  new Utils();
        
        this.setContentPane(tiles);
   
        //this.jp_principal= new Tiles();
       // this.jp_principal.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jp_principal = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jmItem_importacion = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jmItem_salir = new javax.swing.JMenuItem();
        jmenu_productos = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Check Connection");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jp_principalLayout = new javax.swing.GroupLayout(jp_principal);
        jp_principal.setLayout(jp_principalLayout);
        jp_principalLayout.setHorizontalGroup(
            jp_principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jp_principalLayout.setVerticalGroup(
            jp_principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 184, Short.MAX_VALUE)
        );

        jMenu1.setText("Facturas");

        jmItem_importacion.setText("Importación");
        jmItem_importacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmItem_importacionActionPerformed(evt);
            }
        });
        jMenu1.add(jmItem_importacion);

        jMenuItem2.setText("Exportación");
        jMenu1.add(jMenuItem2);
        jMenu1.add(jSeparator1);

        jmItem_salir.setText("Salir");
        jmItem_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmItem_salirActionPerformed(evt);
            }
        });
        jMenu1.add(jmItem_salir);

        jMenuBar1.add(jMenu1);

        jmenu_productos.setText("Productos");
        jmenu_productos.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
            }
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                jmenu_productosMenuSelected(evt);
            }
        });
        jmenu_productos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmenu_productosActionPerformed(evt);
            }
        });
        jMenuBar1.add(jmenu_productos);

        jMenu3.setText("Usuarios");
        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jp_principal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 1048, Short.MAX_VALUE)
                .addComponent(jButton1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jp_principal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 238, Short.MAX_VALUE)
                .addComponent(jButton1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         con = new ConexionJDBC();
        String dni= "77391475V";
        System.out.println("Event: "+evt.toString()) ;
       try {
                Statement query = con.getConexion().createStatement();
                ResultSet res = query.executeQuery("Select * from usuario where DNI='"+dni+"'");
                
                while (res.next()){
                    System.out.println(res.getString("DNI"));
                }

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jmItem_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmItem_salirActionPerformed
        try {
            this.dispose();
        } catch (Throwable ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jmItem_salirActionPerformed

    private void jmItem_importacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmItem_importacionActionPerformed
      System.out.println("jmItem_importacionActionPerformed");
        this.setContentPane(factura);
        this.setVisible(true);
    }//GEN-LAST:event_jmItem_importacionActionPerformed

    private void jmenu_productosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmenu_productosActionPerformed
        this.setContentPane(productos);
        this.setVisible(true);
         System.out.println("jmenu_productosActionPerformed");
    }//GEN-LAST:event_jmenu_productosActionPerformed

    private void jmenu_productosMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_jmenu_productosMenuSelected
        this.setContentPane(productos);
        this.setVisible(true);
         System.out.println("jmenu_productosActionPerformed");
    }//GEN-LAST:event_jmenu_productosMenuSelected

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JMenuItem jmItem_importacion;
    private javax.swing.JMenuItem jmItem_salir;
    private javax.swing.JMenu jmenu_productos;
    private javax.swing.JPanel jp_principal;
    // End of variables declaration//GEN-END:variables
}
