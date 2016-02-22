/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.jujuy.pov.controlador.beans.formbeans;

import ar.jujuy.pov.modelo.dominio.EncabezadoIngreso;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;

/**
 *
 * @author Omar
 */
@ManagedBean
@RequestScoped
public class Report implements Serializable{

    private static Connection conn = null;

    public Report() {
    }

    public void generarReporte_FacturaIN_PDF(EncabezadoIngreso encabezadoIngreso) throws JRException, IOException, ClassNotFoundException {

        // Cargamos el driver JDBC
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/proyecto", "postgres", "alumno");
            conn.setAutoCommit(false);
            // Parametros
            Map parameters = new HashMap();
            parameters.put("ID", encabezadoIngreso.getIdIngreso());
            File file = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/report/FacturaIN.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(file.getPath(), parameters,conn);
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=Factura "+encabezadoIngreso.getNumeroFactura()+".pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found.");
        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        } finally {
            /*
             * Cleanup antes de salir
             */
            try {
                if (conn != null) {
                    conn.rollback();
                    System.out.println("ROLLBACK EJECUTADO");
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
        public void generarReporte_FacturaIN_EXCEL(EncabezadoIngreso encabezadoIngreso) throws JRException, IOException, ClassNotFoundException {

        // Cargamos el driver JDBC
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/proyecto", "postgres", "alumno");
            conn.setAutoCommit(false);
            // Parametros
            Map parameters = new HashMap();
            parameters.put("ID", encabezadoIngreso.getIdIngreso());
            File file = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/report/FacturaIN.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(file.getPath(), parameters,conn);
            
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=Factura "+encabezadoIngreso.getNumeroFactura()+".xlsx");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            
            JRXlsxExporter rXlsExporter=new JRXlsxExporter();
            rXlsExporter.setParameter(JRExporterParameter.INPUT_FILE_NAME, "Factura.xlsx");
            rXlsExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            rXlsExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
            rXlsExporter.exportReport();
            FacesContext.getCurrentInstance().responseComplete();
            
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found.");
        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        } finally {
            /*
             * Cleanup antes de salir
             */
            try {
                if (conn != null) {
                    conn.rollback();
                    System.out.println("ROLLBACK EJECUTADO");
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
