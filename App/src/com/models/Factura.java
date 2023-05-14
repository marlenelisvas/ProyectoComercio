package com.models;

import com.panels.Factura_Importacion;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.json.JSONArray;
import org.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.util.IOUtils;
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
    float Base_Imponible;
    Pago COND_PAGO;
    float IRPF;
    float IVA;
    List<Detalle> detalles;
    public Factura() {
    }

    public Factura(int Id_factura, Tipo Identificacion, Date Fecha, Usuario cliente, Usuario vendedor, float Base_Imponible, Pago COND_PAGO, float IRPF, float IVA, List<Detalle> detalles) {
        this.Id_factura = Id_factura;
        this.tipo_factura = Identificacion;
        this.Fecha = Fecha;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.Base_Imponible = Base_Imponible;
        this.COND_PAGO = COND_PAGO;
        this.IRPF = IRPF;
        this.IVA = IVA;
        this.detalles = detalles;
    }

    public Tipo getTipo() {
        return tipo_factura;
    }

    public void setTipo(Tipo tipo_factura) {
        this.tipo_factura = tipo_factura;
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

    public List<Detalle> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<Detalle> detalles) {
        this.detalles = detalles;
    }

    public boolean ImprimirFacturaImportacion(boolean inPDF) {
        boolean resp = false;
        Template tempImp = new Template();
        tempImp.setId("COD_IMPORT");
        // traer template
        try {
           
            InputStream in = tempImp.getTemplate("COD_IMPORT");
            Random random = new Random(); // to generate a random fileName
            int randomNumber = random.nextInt(987656554);
            if (in != null) {
                XWPFDocument doc = new XWPFDocument(in);
                this.replaceTags(doc, this.getTags()); // Replace Tags in the doc
                FileOutputStream fileOutputStream = new FileOutputStream("Factura" + randomNumber); // Temp location
                if (!inPDF) {
                    doc.write(fileOutputStream);// writing the updated Template to FileOutputStream // to save file
                    byte[] encoded = Files.readAllBytes(Paths.get("Factura" + randomNumber)); // reading the file
                    InputStream convertedInputStream = new ByteArrayInputStream(encoded);
                    IOUtils.copy(convertedInputStream, new FileOutputStream("Factura" + randomNumber + ".docx"));
                    resp = true;
                }
            }
            else {
                //PdfOptions options = PdfOptions.create().fontEncoding("windows-1250");
               // PdfConverter.getInstance().convert(doc, fileOutputStream, options);
                byte[] encoded = Files.readAllBytes(Paths.get("GeneratedDoc_" + randomNumber)); // reading the file*/
         
                InputStream convertedInputStream = new ByteArrayInputStream(encoded);
                IOUtils.copy(convertedInputStream, new FileOutputStream("Factura" + randomNumber + ".pdf"));
             }
            //response.flushBuffer();
        } catch (IOException ex) {
            throw new RuntimeException(ex);

        } catch (XmlException ex) {
            java.util.logging.Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resp;
    }

    private void replaceTags(XWPFDocument doc, JSONArray requestTagsArray) throws IOException, XmlException {
        // To replace Tags
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
        objToPlace.put("value", this.cliente.getNombre()+" "+ this.cliente.getApellidos());
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
        objToPlace.put("value", this.vendedor.getNombre()+" "+ this.vendedor.getApellidos());
        tagsArray.put(objToPlace);
         objToPlace = new JSONObject();
        objToPlace.put("tag", "@{direccion_vendedor}");
        objToPlace.put("value", this.vendedor.getDireccion1());
        tagsArray.put(objToPlace);
        return tagsArray;
    }

    private void replaceParagraphTags(List<XWPFParagraph> paragraphs, JSONArray requestTagsArray) {
        System.out.println("replaceParagraphTags: ");
        // To replace Tags in Paragraphs
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

    private void replaceTableTags(List<XWPFTable> tables, JSONArray requestTagsArray) {
        // To replace Tags in Tables
        for (XWPFTable xwpfTable : tables) {
            List<XWPFTableRow> row = xwpfTable.getRows();
            for (XWPFTableRow xwpfTableRow : row) {
                List<XWPFTableCell> cell = xwpfTableRow.getTableCells();
                for (XWPFTableCell xwpfTableCell : cell) {
                    if (xwpfTableCell != null) {
                        replaceParagraphTags(xwpfTableCell.getParagraphs(), requestTagsArray);
                        List<XWPFTable> internalTables = xwpfTableCell.getTables();
                        if (internalTables.size() != 0) {
                            replaceTableTags(internalTables, requestTagsArray);
                        }
                    }
                }
            }
        }
    }
    public static void createTable(XWPFDocument doc){
        XWPFTable table = doc.createTable(10, 4);
        int i=0;
        table.getRow(i).getCell(0).setText("sdfsdf");
        
    }

    private void replaceHeaderFooterTags(XWPFDocument doc, JSONArray requestTagsArray)
            throws IOException, XmlException {
        // To replace Header and Footer Tags
        XWPFHeaderFooterPolicy policy = new XWPFHeaderFooterPolicy(doc);
        // processing default Header
        XWPFHeader header = policy.getDefaultHeader();
        if (header != null) {
            replaceParagraphTags(header.getParagraphs(), requestTagsArray);
            replaceTableTags(header.getTables(), requestTagsArray);
        }
        // processing default footer
        XWPFFooter footer = policy.getDefaultFooter();
        if (footer != null) {
            replaceParagraphTags(footer.getParagraphs(), requestTagsArray);
            replaceTableTags(footer.getTables(), requestTagsArray);
        }
        // Processing Header and Footer of each page (In case there is of different
        // Header and Footer are set for each page)
        int numberOfPages = doc.getProperties().getExtendedProperties().getUnderlyingProperties().getPages();
        for (int i = 0; i < numberOfPages; i++) {
            // processing headers
            header = policy.getHeader(i);
            if (header != null) {
                replaceParagraphTags(header.getParagraphs(), requestTagsArray);
                replaceTableTags(header.getTables(), requestTagsArray);
            }
            // processing footers
            footer = policy.getFooter(i);
            if (footer != null) {
                replaceParagraphTags(footer.getParagraphs(), requestTagsArray);
                replaceTableTags(footer.getTables(), requestTagsArray);
            }
        }
    }
}
