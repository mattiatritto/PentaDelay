package pentadelay;

/*
 * Questa classe genera un file ODT dal template.
 */



import java.io.InputStream;
import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author Mattia Tritto
 */

public class Report {
    
    /*Attributi*/
    
    private Alunno alunno;
    
    
    
    /**
     * Costruttore con parametri.
     * 
     * @param alunno 
     */
    
    public Report (Alunno alunno){
        this.alunno = alunno;
    }
    
    
    
    /**
     * Metodo che genera il file XML necessario per il report.
     */
    
    public void generateXMLFiles(){
        
        FieldsMetadata fieldsMetadata = new FieldsMetadata(TemplateEngineKind.Velocity.name());

        try {
            fieldsMetadata.load("alunno", Alunno.class);
        } catch (XDocReportException ex) {}
        
        File xmlFieldsFile = new File("project.fields.xml");
        
        try {
            fieldsMetadata.saveXML(new FileOutputStream(xmlFieldsFile), true);
        } catch (FileNotFoundException  ex) {} catch (IOException ex) {}
    }
    
    /**
     * Metodo che genera il report ODT.
     */
    
    public void generateReport(){
        
        InputStream in = null;
        
        try {
            in = new FileInputStream(new File ("..//..//..//template.odt"));
        } catch (FileNotFoundException ex) {}
        
	IXDocReport report = null;
        try {
            report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Velocity);
        } catch (IOException ex) {} catch (XDocReportException ex) {}

        IContext context = null;
        try {
            context = report.createContext();
        } catch (XDocReportException ex) {}
        
        context.put("alunno", alunno);
        
        OutputStream out = null;
        try {
            out = new FileOutputStream(new File("..//..//..//delay.odt"));
        } catch (FileNotFoundException ex) {}
        
        try {
            report.process(context, out);
        } catch (XDocReportException ex) {} catch (IOException ex) {}
    }
}