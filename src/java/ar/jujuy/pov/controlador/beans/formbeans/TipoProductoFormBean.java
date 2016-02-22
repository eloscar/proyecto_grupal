/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.jujuy.pov.controlador.beans.formbeans;

import ar.jujuy.pov.controlador.beans.TipoProductoBean;
import ar.jujuy.pov.dao.TipoProductoDAO;
import ar.jujuy.pov.dao.impl.TipoProductoDAOImpl;
import ar.jujuy.pov.modelo.dominio.TipoProducto;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author FAMILIA
 */
@ManagedBean
@ViewScoped
public class TipoProductoFormBean implements java.io.Serializable{
    
    @ManagedProperty(value = "#{tipoProductoBean}")
    private TipoProductoBean tpb;
    private List<TipoProducto> tabla;
    private TipoProductoDAO tpdao;
    

    public TipoProductoFormBean() {
        super();
        tpdao= new TipoProductoDAOImpl();
        tabla = tpdao.getAll();
    }
    
    //    Getter y Setter de los atributos

    public TipoProductoBean getTpb() {
        return tpb;
    }

    public void setTpb(TipoProductoBean tpb) {
        this.tpb = tpb;
    }

    public List<TipoProducto> getTabla() {
        return tabla;
    }

    public void setTabla(List<TipoProducto> tabla) {
        this.tabla = tabla;
    }

    
    //    Metodos de la clase
    
    public void  limpiarNuevo(){
        tpb.setTipoProducto(new TipoProducto());
        RequestContext.getCurrentInstance().execute("PF('widNuevoTipoProducto').show()");
    }
    
    public void onRowEdit(RowEditEvent event) {
        tpdao.modificar((TipoProducto) event.getObject());
        //tabla=tpdao.getAll();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Editado con exito.","");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edicion cancelada.", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void guardar (){
    
    tpdao.alta(tpb.getTipoProducto());
    tabla=tpdao.getAll();
    FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Tipo de Producto guardado con exito.", "");	
    FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    RequestContext.getCurrentInstance().execute("PF('widNuevoTipoProducto').hide()");
    

    }
    
    public void eliminar(){
    
        tpdao.eliminar(tpb.getTipoProducto());
        tabla=tpdao.getAll();
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Tipo de Producto eliminado con exito.", "");	
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        RequestContext.getCurrentInstance().execute("PF('confirmarBajaTipoProducto').hide()");
    }
}
