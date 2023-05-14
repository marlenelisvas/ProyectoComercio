package com.panels;

import com.jdbc.ConexionJDBC;
import com.models.Detalle;
import com.models.Factura;
import com.models.Producto;
import com.models.Usuario;
import com.utils.Utils;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author developer
 */
enum Tipo {
    EXPORTACION, IMPORTACION
}
public class Factura_Exportacion extends javax.swing.JPanel {

    ConexionJDBC con;
    private static final Logger log = LogManager.getLogger(Factura_Exportacion.class.getName());
    Factura factura;
    Usuario cliente, vendedor;
    Producto productoSeleccionado;
    List<Producto> productos;
    Utils util;
    Detalle nuevoDetalle;

    /**
     * Creates new form Factura
     */
    public Factura_Exportacion() {

        initComponents();

        this.inicializar();

    }

    public void inicializar() {
        factura = new Factura();
        util = new Utils();
        this.btn_addCliente_.setVisible(false);
        this.btn_addVendedor_.setVisible(false);

        //cargar_datos_productos();
        this.cargarDetalles();
    }

    public void mostrar_ocultar_cliente(boolean x, Usuario user) {

        this.txt_apellidos_empresa.setVisible(x);
        this.txt_dni_empresa.setVisible(x);
        this.txt_nombre_empresa.setVisible(x);

        this.lbl_dni_empresa.setVisible(x);
        this.lbl_nombre_empresa.setVisible(x);
        this.lbl_apellidos_empresa.setVisible(x);

        txt_dni_cliente.setEditable(false);
        txt_nombre_cliente.setEditable(false);
        txt_apellidos_cliente.setEditable(false);

        if (x) {
            txt_dni_cliente.setText(user.getDni());
            txt_nombre_cliente.setText(user.getNombre());
            txt_apellidos_cliente.setText(user.getApellidos());

        } else {

            txt_dni_cliente.setText("");
            txt_nombre_cliente.setText("");
            txt_apellidos_cliente.setText("");
        }
    }

    public void mostrar_ocultar_vendedor(boolean x, Usuario user) {
       
        this.btn_addVendedor_.setVisible(x);

    }

    public String calcularTotal() {
        return "";
    }

    public void addVendedor() {

    }

    public void addCliente() {

    }

    public boolean guardarUsuario(Usuario usuario) {
        //validar campos vacios
        boolean valid = usuario.validarUsuario();
        boolean _res = false;
        System.out.println(usuario.toString() + "\n valid " + valid);

        if (usuario.validarDni()) {
            if (usuario.insert()) {
                this.mostrarMensaje("Se ha creado correctamente", "Válido", JOptionPane.PLAIN_MESSAGE);
                _res = true;
            } else {
                this.mostrarMensaje("No se ha creado correctamente", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } else {
            this.mostrarMensaje("ingrese un dni valido", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return _res;

    }

    public Usuario buscarUsuario(String dni, boolean cliente) {

        Usuario user = null;

        if (util.validarDni(dni)) {
            user = new Usuario();
            user.setDni(dni);
            user.consultar(dni);
            System.out.println(user.toString());
            if (user.getNombre() != null) {

                if (cliente) {
                    this.btn_addCliente_.setVisible(false);
                    txt_dni_cliente.setText(user.getDni());
                    txt_nombre_cliente.setText(user.getNombre());
                    txt_apellidos_cliente.setText(user.getApellidos());
                    txt_dir1_cliente.setText(user.getDireccion1());
                    txt_dir2_cliente.setText(user.getDireccion2());

                } else {//vendedor labels-inputs vendedor
                    this.btn_addVendedor_.setVisible(false);
                    txt_dni_empresa.setText(user.getDni());
                    txt_nombre_empresa.setText(user.getNombre());
                    txt_apellidos_empresa.setText(user.getApellidos());
                    txt_direccion_empresa.setText(user.getDireccion1());
                    
                }
            } else {
                if (cliente) {
                    this.btn_addCliente_.setVisible(true);
                } else {
                    // this.mostrar_ocultar_vendedor(true, user); 
                    this.btn_addVendedor_.setVisible(true);
                }
            }
        } else {
            this.mostrarMensaje("ingrese un dni valido", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return user;
    }

    private void cargarDetalles() {

        DefaultTableModel model;
        factura.imprimir();
        factura.imprimirDetalles();
        if (factura.cargarDetalles()) {
            System.out.println(factura.getDetalles().size());
            if (factura.getDetalles().size() > 0) {
                model = new DefaultTableModel(new String[]{"Descripción", "Unidades", "Precio", "Precio Total"}, factura.getDetalles().size());
                this.tb_detalles.setModel(model);
                System.out.println(factura.getDetalles().size());

                TableModel t_model = this.tb_detalles.getModel();

                for (int i = 0; i < factura.getDetalles().size(); i++) {
                    t_model.setValueAt(factura.getDetalles().get(i).getPRODUCTO().getDESCRIPCION(), i, 0);
                    t_model.setValueAt(factura.getDetalles().get(i).getUNIDADES(), i, 1);
                    t_model.setValueAt(factura.getDetalles().get(i).getPRODUCTO().getPRECIO(), i, 2);
                    t_model.setValueAt(factura.getDetalles().get(i).getTOTAL(), i, 3);
                }
            }
            this.txt_baseImponible.setText(factura.getBase_Imponible() + "");
        }
    }

    public void mostrarMensaje(String mensaje, String tipo, int opcion) {
        JOptionPane.showMessageDialog(null, mensaje,
                tipo, opcion);
        //JOptionPane.ERROR_MESSAGE
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tb_detalles = new javax.swing.JTable();
        jp_impuestos = new javax.swing.JPanel();
        lbl_IVA = new javax.swing.JLabel();
        lbl_baseImponible = new javax.swing.JLabel();
        lbl_IRPF = new javax.swing.JLabel();
        txt_baseImponible = new javax.swing.JTextField();
        txt_IRPF = new javax.swing.JTextField();
        txt_IVA = new javax.swing.JTextField();
        txt_TotalFactura = new javax.swing.JTextField();
        lbl_TotalFactura = new javax.swing.JLabel();
        jp_footer = new javax.swing.JPanel();
        btn_ImprimirPDF_ = new javax.swing.JButton();
        btn_Eliminar_ = new javax.swing.JButton();
        btn_Procesar_ = new javax.swing.JButton();
        btn_ImprimirDOC_ = new javax.swing.JButton();
        jp_cliente = new javax.swing.JPanel();
        lbl_section = new javax.swing.JLabel();
        lbl_dni = new javax.swing.JLabel();
        lbl_nombre = new javax.swing.JLabel();
        lbl_apellidos = new javax.swing.JLabel();
        lbl_direccion_1 = new javax.swing.JLabel();
        lbl_direccion_2 = new javax.swing.JLabel();
        txt_dni_cliente = new javax.swing.JTextField();
        txt_nombre_cliente = new javax.swing.JTextField();
        txt_apellidos_cliente = new javax.swing.JTextField();
        txt_dir1_cliente = new javax.swing.JTextField();
        txt_dir2_cliente = new javax.swing.JTextField();
        lbl_FormaPago = new javax.swing.JLabel();
        cb_formas_de_pago = new javax.swing.JComboBox<>();
        btn_buscarCliente_ = new javax.swing.JButton();
        btn_addCliente_ = new javax.swing.JButton();
        jp_empresa = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        lbl_dni_empresa = new javax.swing.JLabel();
        txt_dni_empresa = new javax.swing.JTextField();
        lbl_nombre_empresa = new javax.swing.JLabel();
        txt_nombre_empresa = new javax.swing.JTextField();
        lbl_apellidos_empresa = new javax.swing.JLabel();
        txt_apellidos_empresa = new javax.swing.JTextField();
        lbl_direccion_empresa = new javax.swing.JLabel();
        txt_direccion_empresa = new javax.swing.JTextField();
        btn_buscarVendedor_ = new javax.swing.JButton();
        btn_addVendedor_ = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        lbl_idProducto = new javax.swing.JLabel();
        txt_IdProducto = new javax.swing.JTextField();
        lbl_descripcion = new javax.swing.JLabel();
        txt_descripcion = new javax.swing.JTextField();
        lbl_unidades = new javax.swing.JLabel();
        txt_unidades = new javax.swing.JTextField();
        lbl_precioUnitario = new javax.swing.JLabel();
        txt_precioUnitario = new javax.swing.JTextField();
        jB_buscarId = new javax.swing.JButton();
        btn_add_Detalle = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        tb_detalles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Descripción", "Unidades", "Precio Unitario", "Precio Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Float.class, java.lang.Float.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tb_detalles);

        lbl_IVA.setText("IVA");

        lbl_baseImponible.setText("Base Imponible:");

        lbl_IRPF.setText("IRPF");

        lbl_TotalFactura.setText("Total");

        javax.swing.GroupLayout jp_impuestosLayout = new javax.swing.GroupLayout(jp_impuestos);
        jp_impuestos.setLayout(jp_impuestosLayout);
        jp_impuestosLayout.setHorizontalGroup(
            jp_impuestosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_impuestosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jp_impuestosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp_impuestosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lbl_IVA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbl_IRPF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbl_baseImponible, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))
                    .addComponent(lbl_TotalFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jp_impuestosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_IRPF, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_IVA, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_TotalFactura, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
                    .addComponent(txt_baseImponible)))
        );
        jp_impuestosLayout.setVerticalGroup(
            jp_impuestosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_impuestosLayout.createSequentialGroup()
                .addGroup(jp_impuestosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_baseImponible, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jp_impuestosLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(txt_baseImponible, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jp_impuestosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_IRPF, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_IRPF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jp_impuestosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jp_impuestosLayout.createSequentialGroup()
                        .addComponent(lbl_IVA, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24))
                    .addGroup(jp_impuestosLayout.createSequentialGroup()
                        .addComponent(txt_IVA)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jp_impuestosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_TotalFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_TotalFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        btn_ImprimirPDF_.setText("IMPRIMIR PDF");
        btn_ImprimirPDF_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ImprimirPDF_ActionPerformed(evt);
            }
        });

        btn_Eliminar_.setText("ELIMINAR");
        btn_Eliminar_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Eliminar_ActionPerformed(evt);
            }
        });

        btn_Procesar_.setText("PROCESAR ");
        btn_Procesar_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Procesar_ActionPerformed(evt);
            }
        });

        btn_ImprimirDOC_.setText("IMPRIMIR WORD");
        btn_ImprimirDOC_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ImprimirDOC_ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jp_footerLayout = new javax.swing.GroupLayout(jp_footer);
        jp_footer.setLayout(jp_footerLayout);
        jp_footerLayout.setHorizontalGroup(
            jp_footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_footerLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(btn_ImprimirDOC_, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_ImprimirPDF_, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_Eliminar_, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_Procesar_, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jp_footerLayout.setVerticalGroup(
            jp_footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_footerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jp_footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_Procesar_, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_Eliminar_, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_ImprimirPDF_, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_footerLayout.createSequentialGroup()
                        .addComponent(btn_ImprimirDOC_, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(4, 4, 4)))
                .addContainerGap())
        );

        lbl_section.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lbl_section.setText("Datos del cliente");

        lbl_dni.setText("DNI");

        lbl_nombre.setText("Nombre");

        lbl_apellidos.setText("Apellidos");

        lbl_direccion_1.setText("Dirección ");

        lbl_direccion_2.setText("Dirección de envio");

        lbl_FormaPago.setText("Forma de Pago");

        cb_formas_de_pago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cb_formas_de_pago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_formas_de_pagoActionPerformed(evt);
            }
        });

        btn_buscarCliente_.setText("Buscar");
        btn_buscarCliente_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscarCliente_ActionPerformed(evt);
            }
        });

        btn_addCliente_.setText("+");
        btn_addCliente_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addCliente_ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jp_clienteLayout = new javax.swing.GroupLayout(jp_cliente);
        jp_cliente.setLayout(jp_clienteLayout);
        jp_clienteLayout.setHorizontalGroup(
            jp_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_clienteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jp_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp_clienteLayout.createSequentialGroup()
                        .addGroup(jp_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jp_clienteLayout.createSequentialGroup()
                                .addComponent(lbl_dni, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_buscarCliente_)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_addCliente_))
                            .addComponent(txt_dni_cliente))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jp_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_nombre, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                            .addComponent(txt_nombre_cliente))
                        .addGap(18, 18, 18)
                        .addGroup(jp_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_apellidos, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                            .addComponent(txt_apellidos_cliente))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jp_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_direccion_1, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                            .addComponent(txt_dir1_cliente))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jp_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jp_clienteLayout.createSequentialGroup()
                                .addComponent(lbl_direccion_2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(jp_clienteLayout.createSequentialGroup()
                                .addComponent(txt_dir2_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)))
                        .addGroup(jp_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_FormaPago, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cb_formas_de_pago, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25))
                    .addGroup(jp_clienteLayout.createSequentialGroup()
                        .addComponent(lbl_section)
                        .addGap(0, 1058, Short.MAX_VALUE))))
        );
        jp_clienteLayout.setVerticalGroup(
            jp_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_clienteLayout.createSequentialGroup()
                .addComponent(lbl_section, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(jp_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbl_nombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbl_dni, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_buscarCliente_)
                        .addComponent(btn_addCliente_))
                    .addComponent(lbl_apellidos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_direccion_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jp_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbl_direccion_2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbl_FormaPago, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jp_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_dni_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_nombre_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_apellidos_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_dir1_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_dir2_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_formas_de_pago, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );

        jp_empresa.setMaximumSize(new java.awt.Dimension(32767, 800));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel14.setText("Datos de la empresa");

        lbl_dni_empresa.setText("DNI");

        txt_dni_empresa.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txt_dni_empresaPropertyChange(evt);
            }
        });

        lbl_nombre_empresa.setText("Nombre");

        lbl_apellidos_empresa.setText("Apellidos");

        lbl_direccion_empresa.setText("Dirección ");

        btn_buscarVendedor_.setText("Buscar");
        btn_buscarVendedor_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscarVendedor_ActionPerformed(evt);
            }
        });

        btn_addVendedor_.setText("+");
        btn_addVendedor_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addVendedor_ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jp_empresaLayout = new javax.swing.GroupLayout(jp_empresa);
        jp_empresa.setLayout(jp_empresaLayout);
        jp_empresaLayout.setHorizontalGroup(
            jp_empresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_empresaLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jp_empresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel14)
                    .addGroup(jp_empresaLayout.createSequentialGroup()
                        .addComponent(lbl_dni_empresa, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_buscarVendedor_)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_addVendedor_))
                    .addComponent(txt_dni_empresa))
                .addGap(18, 18, 18)
                .addGroup(jp_empresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_nombre_empresa, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_nombre_empresa, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jp_empresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_apellidos_empresa, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                    .addComponent(txt_apellidos_empresa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jp_empresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp_empresaLayout.createSequentialGroup()
                        .addComponent(lbl_direccion_empresa, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
                        .addGap(97, 97, 97))
                    .addGroup(jp_empresaLayout.createSequentialGroup()
                        .addComponent(txt_direccion_empresa, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jp_empresaLayout.setVerticalGroup(
            jp_empresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_empresaLayout.createSequentialGroup()
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jp_empresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jp_empresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbl_apellidos_empresa)
                        .addComponent(lbl_direccion_empresa))
                    .addGroup(jp_empresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_buscarVendedor_)
                        .addComponent(btn_addVendedor_)
                        .addComponent(lbl_nombre_empresa)
                        .addComponent(lbl_dni_empresa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jp_empresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_dni_empresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_nombre_empresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_apellidos_empresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_direccion_empresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel6.setText("Producto");

        lbl_idProducto.setText("CODIGO");

        lbl_descripcion.setText("Descripción");

        lbl_unidades.setText("Unidades");

        txt_unidades.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_unidadesKeyPressed(evt);
            }
        });

        lbl_precioUnitario.setText("Precio Unitario");

        jB_buscarId.setText("Buscar");
        jB_buscarId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_buscarIdActionPerformed(evt);
            }
        });

        btn_add_Detalle.setText("+");
        btn_add_Detalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_add_DetalleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lbl_idProducto, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jB_buscarId))
                            .addComponent(txt_IdProducto, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE))
                        .addGap(93, 93, 93)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_descripcion)
                            .addComponent(lbl_descripcion, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE))
                        .addGap(45, 45, 45)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_unidades, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_unidades, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txt_precioUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_precioUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_add_Detalle)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel6)
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_idProducto)
                            .addComponent(lbl_descripcion)
                            .addComponent(jB_buscarId))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_IdProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_unidades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_precioUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_add_Detalle, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lbl_precioUnitario)
                                .addComponent(lbl_unidades)))))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("FACTURA EXPORTACIÓN");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jp_empresa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jp_cliente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jp_impuestos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jp_footer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(424, 424, 424)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jp_empresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jp_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jp_impuestos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jp_footer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cb_formas_de_pagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_formas_de_pagoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_formas_de_pagoActionPerformed

    private void btn_Procesar_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Procesar_ActionPerformed
       this.factura.setIRPF(Double.parseDouble(this.txt_IRPF.getText()));
       this.factura.setIVA(Double.parseDouble(this.txt_IVA.getText()));
       this.txt_TotalFactura.setText(factura.getTotal()+"");
       this.factura.setTipo(false);
       this.factura.update();
       this.factura.imprimir();
    }//GEN-LAST:event_btn_Procesar_ActionPerformed

    private void btn_Eliminar_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Eliminar_ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_Eliminar_ActionPerformed

    private void btn_ImprimirPDF_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ImprimirPDF_ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_ImprimirPDF_ActionPerformed

    private void btn_ImprimirDOC_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ImprimirDOC_ActionPerformed
        //this.factura.
        this.factura.setCliente(this.cliente);
        this.factura.setVendedor(this.vendedor);
        System.out.println(this.factura.getCliente().toString());
        if (!this.factura.ImprimirFacturaImportacion(false)) {
            JOptionPane.showMessageDialog(null, "No tiene cargado el template.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_ImprimirDOC_ActionPerformed

    private void txt_dni_empresaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txt_dni_empresaPropertyChange
        System.out.println("aasdasfas");        // TODO add your handling code here:
    }//GEN-LAST:event_txt_dni_empresaPropertyChange

    private void btn_addVendedor_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addVendedor_ActionPerformed
     this.vendedor=new Usuario(this.txt_dni_empresa.getText(), txt_nombre_empresa.getText(),txt_apellidos_empresa.getText(),txt_direccion_empresa.getText()  ,""        );

        this.guardarUsuario(vendedor);
    }//GEN-LAST:event_btn_addVendedor_ActionPerformed

    private void btn_addCliente_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addCliente_ActionPerformed
        this.cliente=new Usuario(this.txt_dni_cliente.getText(), txt_nombre_cliente.getText(),txt_apellidos_cliente.getText(),this.txt_dir1_cliente.getText()  ,this.txt_dir2_cliente.getText()       );

        this.guardarUsuario(cliente);
    }//GEN-LAST:event_btn_addCliente_ActionPerformed

    private void btn_buscarVendedor_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscarVendedor_ActionPerformed

        // texto del dni
        this.vendedor = this.buscarUsuario(this.txt_dni_empresa.getText(), false);
    }//GEN-LAST:event_btn_buscarVendedor_ActionPerformed

    private void btn_buscarCliente_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscarCliente_ActionPerformed
        this.cliente = this.buscarUsuario(this.txt_dni_cliente.getText(), true);
    }//GEN-LAST:event_btn_buscarCliente_ActionPerformed

    private void jB_buscarIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_buscarIdActionPerformed
        if (this.txt_IdProducto.getText() != "") {
            this.productoSeleccionado = new Producto();
            this.nuevoDetalle = new Detalle();

            this.productoSeleccionado.consultar(Integer.parseInt(this.txt_IdProducto.getText()));
            this.productoSeleccionado.imprimir();
            if (this.productoSeleccionado.getDESCRIPCION() == null || this.productoSeleccionado.getDESCRIPCION().equals("")) {

            } else {
                this.nuevoDetalle.setPRODUCTO(this.productoSeleccionado);
                this.txt_IdProducto.setText(String.valueOf(this.productoSeleccionado.getID_PRODUCTO()));
                this.txt_precioUnitario.setText(String.valueOf(this.productoSeleccionado.getPRECIO()));
                this.txt_descripcion.setText(this.productoSeleccionado.getDESCRIPCION());
            }
        } else {
            this.mostrarMensaje("Ingrese el CODIGO del producto", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jB_buscarIdActionPerformed

    private void btn_add_DetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_add_DetalleActionPerformed
           
        if (this.txt_unidades.getText().equals("")) {
            this.mostrarMensaje("Ingrese las unidades", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            //añadir tipo
            
            if (factura.getDetalles().size() == 0) {
                factura = new Factura();
                factura.setCliente(this.cliente);
                factura.setVendedor(this.vendedor);
                if (!factura.insert()) {
                    factura.imprimir();
                }
            }
            this.nuevoDetalle.setFACTURA(this.factura);
            this.nuevoDetalle.setUNIDADES(Integer.parseInt(this.txt_unidades.getText()));
            
            if (this.nuevoDetalle.insert()) {
                this.cargarDetalles();
            } else {
                this.mostrarMensaje("No se ha guardado el detalla", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btn_add_DetalleActionPerformed

    private void txt_unidadesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_unidadesKeyPressed
        
    }//GEN-LAST:event_txt_unidadesKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Eliminar_;
    private javax.swing.JButton btn_ImprimirDOC_;
    private javax.swing.JButton btn_ImprimirPDF_;
    private javax.swing.JButton btn_Procesar_;
    private javax.swing.JButton btn_addCliente_;
    private javax.swing.JButton btn_addVendedor_;
    private javax.swing.JButton btn_add_Detalle;
    private javax.swing.JButton btn_buscarCliente_;
    private javax.swing.JButton btn_buscarVendedor_;
    private javax.swing.JComboBox<String> cb_formas_de_pago;
    private javax.swing.JButton jB_buscarId;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jp_cliente;
    private javax.swing.JPanel jp_empresa;
    private javax.swing.JPanel jp_footer;
    private javax.swing.JPanel jp_impuestos;
    private javax.swing.JLabel lbl_FormaPago;
    private javax.swing.JLabel lbl_IRPF;
    private javax.swing.JLabel lbl_IVA;
    private javax.swing.JLabel lbl_TotalFactura;
    private javax.swing.JLabel lbl_apellidos;
    private javax.swing.JLabel lbl_apellidos_empresa;
    private javax.swing.JLabel lbl_baseImponible;
    private javax.swing.JLabel lbl_descripcion;
    private javax.swing.JLabel lbl_direccion_1;
    private javax.swing.JLabel lbl_direccion_2;
    private javax.swing.JLabel lbl_direccion_empresa;
    private javax.swing.JLabel lbl_dni;
    private javax.swing.JLabel lbl_dni_empresa;
    private javax.swing.JLabel lbl_idProducto;
    private javax.swing.JLabel lbl_nombre;
    private javax.swing.JLabel lbl_nombre_empresa;
    private javax.swing.JLabel lbl_precioUnitario;
    private javax.swing.JLabel lbl_section;
    private javax.swing.JLabel lbl_unidades;
    private javax.swing.JTable tb_detalles;
    private javax.swing.JTextField txt_IRPF;
    private javax.swing.JTextField txt_IVA;
    private javax.swing.JTextField txt_IdProducto;
    private javax.swing.JTextField txt_TotalFactura;
    private javax.swing.JTextField txt_apellidos_cliente;
    private javax.swing.JTextField txt_apellidos_empresa;
    private javax.swing.JTextField txt_baseImponible;
    private javax.swing.JTextField txt_descripcion;
    private javax.swing.JTextField txt_dir1_cliente;
    private javax.swing.JTextField txt_dir2_cliente;
    private javax.swing.JTextField txt_direccion_empresa;
    private javax.swing.JTextField txt_dni_cliente;
    private javax.swing.JTextField txt_dni_empresa;
    private javax.swing.JTextField txt_nombre_cliente;
    private javax.swing.JTextField txt_nombre_empresa;
    private javax.swing.JTextField txt_precioUnitario;
    private javax.swing.JTextField txt_unidades;
    // End of variables declaration//GEN-END:variables
}
