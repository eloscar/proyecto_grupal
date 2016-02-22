/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.jujuy.pov.controlador.beans.formbeans;

import ar.jujuy.pov.dao.DetalleUnidadDAO;
import ar.jujuy.pov.dao.impl.DetalleUnidadDAOImpl;
import ar.jujuy.pov.modelo.dominio.DetalleUnidad;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author FAMILIA
 */
@ManagedBean
@ViewScoped
public class UnidadMedidaFormBean implements java.io.Serializable{
    
    private List<DetalleUnidad> tablaUnidad; 
    private DetalleUnidad detalleUnidad;
    private DetalleUnidadDAO udao;

    public UnidadMedidaFormBean() {
        super();
        udao = new DetalleUnidadDAOImpl();
        tablaUnidad = udao.getAll();
    }
    
    //    Getter y Setter

    public List<DetalleUnidad> getTablaUnidad() {
        return tablaUnidad;
    }

    public void setTablaUnidad(List<DetalleUnidad> tablaUnidad) {
        this.tablaUnidad = tablaUnidad;
    }

    public DetalleUnidad getDetalleUnidad() {
        return detalleUnidad;
    }

    public void setDetalleUnidad(DetalleUnidad detalleUnidad) {
        this.detalleUnidad = detalleUnidad;
    }
    
    
}
