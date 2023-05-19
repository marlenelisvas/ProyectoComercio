package com.models;

import com.jdbc.ConexionJDBC;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.json.JSONArray;
import org.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.XWPFFooter;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.xmlbeans.XmlException;

enum Pago {
    EFECTIVO, DEBITO, CREDITO, CHEQUE
}

enum Tipo {
    EXPORTACION, IMPORTACION
}

/**
 *
 * @author Jenny
 */
public class Factura {

    private static final Logger log = LogManager.getLogger(Factura.class.getName());
    int Id_factura;
    Tipo tipo_factura;
    Date Fecha;
    Usuario cliente, vendedor;
    Double Base_Imponible;
    Pago COND_PAGO;
    Double IRPF;
    Double IVA;
    List<Detalle> detalles;
    Double total;

    public Factura() {
        this.Fecha = new Date();
        this.tipo_factura = Tipo.EXPORTACION;
        this.Base_Imponible = 0.0;
        this.COND_PAGO = Pago.EFECTIVO;
        this.IRPF = 0.0;
        this.IVA = 0.0;
        this.detalles = new ArrayList<>();
    }

    public Factura(int Id_factura, Tipo tipo_factura, Date Fecha, Usuario cliente, Usuario vendedor, Double Base_Imponible, Pago COND_PAGO, Double IRPF, Double IVA, List<Detalle> detalles) {
        this.Id_factura = Id_factura;
        this.Fecha = Fecha;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.Base_Imponible = Base_Imponible;
        this.COND_PAGO = COND_PAGO;
        this.IRPF = IRPF;
        this.IVA = IVA;
        this.detalles = detalles;
        this.tipo_factura = Tipo.EXPORTACION;
    }

    public Tipo getTipo() {
        return tipo_factura;
    }

    public Tipo getTipo_factura() {
        return tipo_factura;
    }

    public void setTipo_factura(Tipo tipo_factura) {
        this.tipo_factura = tipo_factura;
    }

    public void setIRPF(Double IRPF) {
        this.IRPF = IRPF;
    }

    public Double getTotal() {
        this.total = this.Base_Imponible + ((this.Base_Imponible * this.IVA) + (this.Base_Imponible * this.IRPF));
        return total;
    }

    public void setIVA(Double IVA) {
        this.IVA = IVA;
    }

    public void setTipo(boolean tipo) {
        this.tipo_factura = (tipo) ? Tipo.IMPORTACION : Tipo.EXPORTACION;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public Usuario getVendedor() {
        return vendedor;
    }

    public void setVendedor(Usuario vendedor) {
        this.vendedor = vendedor;
    }

    public int getId_factura() {
        return Id_factura;
    }

    public void setId_factura(int Id_factura) {
        this.Id_factura = Id_factura;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date Fecha) {
        this.Fecha = Fecha;
    }

    public Pago getCOND_PAGO() {
        return COND_PAGO;
    }

    public void setCOND_PAGO(String COND_PAGO) {
        switch (COND_PAGO) {
            case "CHEQUE":
                this.COND_PAGO = Pago.CHEQUE;
                break;
            case "DEBITO":
                this.COND_PAGO = Pago.DEBITO;
                break;
            case "CREDITO":
                this.COND_PAGO = Pago.CREDITO;
                break;
            default:
                this.COND_PAGO = Pago.EFECTIVO;
                break;
        }

    }

    public List<Detalle> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<Detalle> detalles) {
        this.detalles = detalles;
    }

    public Double getBase_Imponible() {
        return Base_Imponible;
    }

    public void setBase_Imponible(Double Base_Imponible) {
        this.Base_Imponible = Base_Imponible;
    }

//=====================================================================
    public boolean insert() {
//=====================================================================
        ConexionJDBC _con = new ConexionJDBC();
        boolean _res = false;
        try {
            String f = this.Fecha.getYear() + "-" + this.Fecha.getMonth() + "-" + this.Fecha.getDay();
            String q = "INSERT INTO factura (ID_FACTURA ,ID_IMPORTADOR,ID_EXPORTADOR , FECHA, BASE_IMPONIBLE, COND_PAGO, IRPF, IVA, TIPO) VALUES(null,'"
                    + this.cliente.getDni() + "','" + this.vendedor.getDni() + "','" + new java.sql.Date(this.Fecha.getTime()) + "','" + this.Base_Imponible
                    + "','" + this.COND_PAGO.ordinal() + "','" + this.IRPF + "','" + this.IVA + "','" + this.tipo_factura.ordinal() + "')";

            Statement statement = _con.getConexion().prepareStatement(q);
            int affectedRows = statement.executeUpdate(q, Statement.RETURN_GENERATED_KEYS);
            if (affectedRows == 0) {
                throw new SQLException("No se pudo guardar");
            }

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                this.Id_factura = generatedKeys.getInt(1);
            }
            _res = true;
            _con.getConexion().close();
        } catch (SQLException ex) {

            throw new RuntimeException(ex);

        }
        return _res;
    }
//=====================================================================

    public boolean update() {
//=====================================================================
        ConexionJDBC _con = new ConexionJDBC();
        boolean _res = false;
        String f = this.Fecha.getYear() + "-" + this.Fecha.getMonth() + "-" + this.Fecha.getDay();
        try {
            Statement query = _con.getConexion().createStatement();
            //ResultSet no vale para el insert y executeQuery no sirve para actualizar base de datos
            //ID_FACTURA ,ID_IMPORTADOR,ID_EXPORTADOR , FECHA, BASE_IMPONIBLE, COND_PAGO, IRPF, IVA, TIPO
            // date año,mes,día '1995-01-29'
            int res = query.executeUpdate("UPDATE  factura SET FECHA ='"
                    + new java.sql.Date(this.Fecha.getTime()) + "', BASE_IMPONIBLE= " + this.Base_Imponible + ",IRPF=" + this.IRPF + ",IVA=" + this.IVA + ", TIPO='" + this.tipo_factura.ordinal() + "' WHERE ID_FACTURA=" + this.Id_factura);

            _res = true;
            _con.getConexion().close();
        } catch (SQLException ex) {

            throw new RuntimeException(ex.getMessage());
        }
        return _res;
    }
//=====================================================================

    public boolean delete() {
//=====================================================================

        for (int i = 0; i < this.detalles.size(); i++) {
            this.detalles.get(i).delete();
        }
        ConexionJDBC _con = new ConexionJDBC();
        boolean _res = false;
        try {
            Statement query = _con.getConexion().createStatement();
            //ResultSet no vale para el insert y executeQuery no sirve para actualizar base de datos
            int res = query.executeUpdate("Delete from factura WHERE ID_FACTURA=" + this.Id_factura);

            _res = true;
            _con.getConexion().close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

            //throw new RuntimeException(ex.getMessage());
        }
        return _res;
    }

    public boolean cargarDetalles() {
        boolean _res = false;
        if (this.Id_factura > 0) {
            ConexionJDBC _con = new ConexionJDBC();
            this.Base_Imponible = 0.0;
            try {
                Statement query = _con.getConexion().createStatement();
                ResultSet res = query.executeQuery("Select * from detalles where ID_FACTURA='" + this.Id_factura + "'");
                this.detalles = new ArrayList<>();
                while (res.next()) {
                    //int ID_DETALLE, Factura FACTURA, Producto PRODUCTO, int UNIDADES, double TOTAL
                    Detalle aux = new Detalle();
                    aux.setID_DETALLE(res.getInt("ID_DETALLES"));
                    aux.setFACTURA(this);

                    Producto x = new Producto();
                    x.consultar(res.getInt("ID_PRODUCTO"));
                    aux.setPRODUCTO(x);

                    aux.setUNIDADES(res.getInt("UNIDADES"));
                    aux.setTOTAL(res.getDouble("TOTAL"));
                    this.Base_Imponible += aux.getTOTAL();
                    aux.imprimir();
                    this.detalles.add(aux);
                }
                _res = true;
                _con.getConexion().close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        return _res;
    }

    public boolean ImprimirFactura(boolean inPDF) {
        boolean resp = false;
        String tipo = "COD_EXPORT";
        Template tempImp = new Template();
        if (this.tipo_factura == Tipo.IMPORTACION) {
            tipo = "COD_IMPORT";
        }

        tempImp.setId(tipo);
        // traer template
        try {

            InputStream in = tempImp.getTemplate(tipo);
            Random random = new Random();
            int randomNumber = random.nextInt(987656554);
            if (in != null) {
                XWPFDocument doc = new XWPFDocument(in);
                this.replaceTags(doc, this.getTags()); // reemplaza los tags
                FileOutputStream fileOutputStream = new FileOutputStream("Factura_" + tipo + "_" + randomNumber); // Temp location
                if (!inPDF) {
                    doc.write(fileOutputStream);// salida del archivo actualizado
                    byte[] encoded = Files.readAllBytes(Paths.get("Factura_" + tipo + "_" + randomNumber)); // lee el archivo
                    InputStream convertedInputStream = new ByteArrayInputStream(encoded);
                    IOUtils.copy(convertedInputStream, new FileOutputStream("Factura_" + tipo + "_" + randomNumber + ".docx"));
                    resp = true;
                } else {
                    //no reconoce la libreria?????????????
                    //  PdfOptions options = PdfOptions.create().fontEncoding("windows-1250");
                    // PdfConverter..getInstance().convert(doc, fileOutputStream, options);
                    byte[] encoded = Files.readAllBytes(Paths.get("Factura_" + tipo + "_" + randomNumber)); // reading the file*/

                    InputStream convertedInputStream = new ByteArrayInputStream(encoded);
                    IOUtils.copy(convertedInputStream, new FileOutputStream("Factura_" + tipo + "_" + randomNumber + ".pdf"));
                }
            }

        } catch (IOException ex) {
            throw new RuntimeException(ex);

        } catch (XmlException ex) {
            java.util.logging.Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resp;
    }

    private void replaceTags(XWPFDocument doc, JSONArray requestTagsArray) throws IOException, XmlException {
        replaceParagraphTags(doc.getParagraphs(), requestTagsArray);
        replaceTableTags(doc.getTables(), requestTagsArray);
        replaceHeaderFooterTags(doc, requestTagsArray);
    }

    public JSONArray getTags() {
        JSONArray tagsArray = new JSONArray();
        JSONObject objToPlace = new JSONObject();
        // cliente
        objToPlace.put("tag", "@{id_cliente}");
        objToPlace.put("value", cliente.getDni());
        tagsArray.put(objToPlace);

        objToPlace = new JSONObject();
        objToPlace.put("tag", "@{nombre_cliente}");
        objToPlace.put("value", this.cliente.getNombre() + " " + this.cliente.getApellidos());
        tagsArray.put(objToPlace);

        objToPlace = new JSONObject();
        objToPlace.put("tag", "@{direccion_cliente}");
        objToPlace.put("value", this.cliente.getDireccion1());
        tagsArray.put(objToPlace);

        // vendedor
        objToPlace = new JSONObject();
        objToPlace.put("tag", "@{id_vendedor}");
        objToPlace.put("value", cliente.getDni());
        tagsArray.put(objToPlace);

        objToPlace = new JSONObject();
        objToPlace.put("tag", "@{nombre_vendedor}");
        objToPlace.put("value", this.vendedor.getNombre() + " " + this.vendedor.getApellidos());
        tagsArray.put(objToPlace);

        objToPlace = new JSONObject();
        objToPlace.put("tag", "@{direccion_vendedor}");
        objToPlace.put("value", this.vendedor.getDireccion1());
        tagsArray.put(objToPlace);

        objToPlace = new JSONObject();
        objToPlace.put("tag", "@{id_factura}");
        objToPlace.put("value", this.Id_factura + "");
        tagsArray.put(objToPlace);

        objToPlace = new JSONObject();
        objToPlace.put("tag", "@{base}");
        objToPlace.put("value", this.Base_Imponible + "");
        tagsArray.put(objToPlace);

        objToPlace = new JSONObject();
        objToPlace.put("tag", "@{IRPF}");
        objToPlace.put("value", this.IRPF + "");
        tagsArray.put(objToPlace);

        objToPlace = new JSONObject();
        objToPlace.put("tag", "@{IVA}");
        objToPlace.put("value", this.IVA + "");
        tagsArray.put(objToPlace);

        objToPlace = new JSONObject();
        objToPlace.put("tag", "@{total}");
        objToPlace.put("value", this.total + "");
        tagsArray.put(objToPlace);

        objToPlace = new JSONObject();
        objToPlace.put("tag", "@{fecha}");
        objToPlace.put("value", new java.sql.Date(this.Fecha.getTime()) + "");
        tagsArray.put(objToPlace);
        return tagsArray;
    }

    private void replaceParagraphTags(List<XWPFParagraph> paragraphs, JSONArray requestTagsArray) {

        List<XWPFRun> runs;
        String text;
        JSONObject tagObject;
        for (XWPFParagraph p : paragraphs) {
            System.out.println("Text: " + p.toString());
            runs = p.getRuns();
            if (runs != null) {
                for (XWPFRun r : runs) {
                    text = r.getText(0);
                    System.out.println("Text: " + text);
                    for (int i = 0; i < requestTagsArray.length(); i++) {
                        tagObject = requestTagsArray.getJSONObject(i);
                        if (text != null && text.contains(tagObject.getString("tag"))) {
                            text = text.replace(tagObject.getString("tag"), tagObject.getString("value"));
                            r.setText(text, 0);
                        }
                    }
                }
            }
        }
    }

    private void replaceTableTags(List<XWPFTable> tables, JSONArray requestTagsArray) {

        // To replace Tags in Tables
        System.out.println("Tables size " + tables.size());
        XWPFTable xwpfTable = tables.get(0);

        XWPFTableRow xwpfTableRow = xwpfTable.getRow(1);
        System.out.println("detalles size " + detalles.size());
        for (int i = 0; i < this.detalles.size(); i++) {
            Detalle d = this.detalles.get(i);
            addRow(xwpfTable, xwpfTableRow,
                    d.getPRODUCTO().getDESCRIPCION(),
                    d.UNIDADES + "",
                    d.getPRODUCTO().getPRECIO() + "",
                    d.TOTAL + "");
        }
        if (this.detalles.size() > 0) {
            xwpfTable.removeRow(1);
        }

    }

    private static void addRow(XWPFTable table, XWPFTableRow row, String... colTexts) {
        for (int col = 0; col < colTexts.length; col++) {
            row.getCell(col).getParagraphArray(0).getRuns().get(0).setText(colTexts[col], 0);
        }
        table.addRow(row);
    }

    private void replaceHeaderFooterTags(XWPFDocument doc, JSONArray requestTagsArray)
            throws IOException, XmlException {

        XWPFHeaderFooterPolicy policy = new XWPFHeaderFooterPolicy(doc);

        XWPFHeader header = policy.getDefaultHeader();
        if (header != null) {
            replaceParagraphTags(header.getParagraphs(), requestTagsArray);
            replaceTableTags(header.getTables(), requestTagsArray);
        }

        XWPFFooter footer = policy.getDefaultFooter();
        if (footer != null) {
            replaceParagraphTags(footer.getParagraphs(), requestTagsArray);
            replaceTableTags(footer.getTables(), requestTagsArray);
        }

        int numberOfPages = doc.getProperties().getExtendedProperties().getUnderlyingProperties().getPages();
        for (int i = 0; i < numberOfPages; i++) {

            header = policy.getHeader(i);
            if (header != null) {
                replaceParagraphTags(header.getParagraphs(), requestTagsArray);
                replaceTableTags(header.getTables(), requestTagsArray);
            }

            footer = policy.getFooter(i);
            if (footer != null) {
                replaceParagraphTags(footer.getParagraphs(), requestTagsArray);
                replaceTableTags(footer.getTables(), requestTagsArray);
            }
        }
    }

    public void imprimir() {
        //ID_FACTURA ,ID_IMPORTADOR,ID_EXPORTADOR , FECHA, BASE_IMPONIBLE, COND_PAGO, IRPF, IVA, TIPO
        System.out.println("====================================\n"
                + "ID FACTURA: " + this.Id_factura
                + "\n ID_IMPORTADOR: " + ((this.cliente != null) ? this.cliente.getDni() : "")
                + "\n ID_EXPORTADOR: " + ((this.vendedor != null) ? this.vendedor.getDni() : "")
                + "\n FECHA: " + this.Fecha
                + "\n BASE_IMPONIBLE: " + this.Base_Imponible
                + "\n COND_PAGO: " + this.COND_PAGO
                + "\n IRPF: " + this.IRPF
                + "\n IVA: " + this.IVA
                + "\n TIPO: " + this.tipo_factura
                + "\n================================");
    }

    public void imprimirDetalles() {
        for (int i = 0; i < this.detalles.size(); i++) {
            this.detalles.get(i).imprimir();
        }
    }
}
