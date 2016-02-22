/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.jujuy.pov.controlador.beans.formbeans;

import ar.jujuy.pov.controlador.beans.ProductoBean;
import ar.jujuy.pov.dao.ProductoDAO;
import ar.jujuy.pov.dao.impl.ProductoDAOImpl;
import ar.jujuy.pov.modelo.dominio.Marca;
import ar.jujuy.pov.modelo.dominio.Producto;
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
public class ProductoFormBean implements java.io.Serializable {

    @ManagedProperty(value = "#{productoBean}")
    private ProductoBean pb;
    private List<Producto> tabla;
    private List<Producto> tablaProductoHabilitado;
    private Producto producto;
    private ProductoDAO pdao;

    private boolean alta;
    private Integer tipoId;
    private Integer marcaId;
    private Integer productoId;
    private List<Producto> tablaFiltrada;
    private Integer idUnidad;

    public ProductoFormBean() {
        super();
        pdao = new ProductoDAOImpl();
        tabla = pdao.getAll();
        tablaProductoHabilitado=pdao.getAllTrue();
    }

     //    Getter y Setter de los atributos
    public ProductoBean getPb() {
        return pb;
    }

    public void setPb(ProductoBean pb) {
        this.pb = pb;
    }

    public List<Producto> getTabla() {
        return tabla;
    }

    public void setTabla(List<Producto> tabla) {
        this.tabla = tabla;
    }

    public boolean isAlta() {
        return alta;
    }

    public void setAlta(boolean alta) {
        this.alta = alta;
    }

    public Integer getTipoId() {
        return tipoId;
    }

    public void setTipoId(Integer tipoId) {
        this.tipoId = tipoId;
    }

    public Integer getMarcaId() {
        return marcaId;
    }

    public void setMarcaId(Integer marcaId) {
        this.marcaId = marcaId;
    }

    public Integer getProductoId() {
        return productoId;
    }

    public void setProductoId(Integer productoId) {
        this.productoId = productoId;
    }

    public List<Producto> getTablaFiltrada() {
        return tablaFiltrada;
    }

    public void setTablaFiltrada(List<Producto> tablaFiltrada) {
        this.tablaFiltrada = tablaFiltrada;
    }

    public Integer getIdUnidad() {
        return idUnidad;
    }

    public void setIdUnidad(Integer idUnidad) {
        this.idUnidad = idUnidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public List<Producto> getTablaProductoHabilitado() {
        return tablaProductoHabilitado;
    }

    public void setTablaProductoHabilitado(List<Producto> tablaProductoHabilitado) {
        this.tablaProductoHabilitado = tablaProductoHabilitado;
    }
    
    //    Metodos de la clase
    
    public void limpiarNuevo() {
        pb.setProducto(new Producto());
        RequestContext.getCurrentInstance().execute("PF('widNuevoProducto').show()");
    }

    public void onRowEdit(RowEditEvent event) {
        pdao.modificar(pb.getProducto());
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Editado con exito.", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edicion cancelada.", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void guardar() {

        pdao.alta(pb.getProducto());
        tabla = pdao.getAll();

        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Producto guardado con exito.", "");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        RequestContext.getCurrentInstance().execute("PF('widNuevoProducto').hide()");

    }

    public void eliminar() {

        pdao.eliminar(pb.getProducto());
        tabla = pdao.getAll();
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Producto eliminado con exito.", "");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        RequestContext.getCurrentInstance().execute("PF('confirmarBajaProducto').hide()");
    }

    public void guardarTipo() {
        FacesContext context = FacesContext.getCurrentInstance();
        TipoProducto tp = context.getApplication().evaluateExpressionGet(context, "#{tipoProductoFormBean.tpb.tipoProducto}", TipoProducto.class);
        System.out.println(tp);
        pb.getProducto().setTipoProducto(tp);
        RequestContext.getCurrentInstance().execute("PF('widTipo').hide()");
    }

    public void guardarMarca() {
        FacesContext context = FacesContext.getCurrentInstance();
        Marca m = context.getApplication().evaluateExpressionGet(context, "#{marcaFormBean.mb.marca}", Marca.class);
        System.out.println(m);
        pb.getProducto().setMarca(m);
        RequestContext.getCurrentInstance().execute("PF('widMarca').hide()");
    }

    public void buscar() {

    }

    //Con estos metos cargo un nuevo producto
    public String preCargarProducto() {

        alta = true;
        pb.setProducto(new Producto());

        return "productoAyM.xhtml?faces-redirect=true";
    }

    public String cargarProducto() {

        String resultado = null;

        if (pb.getProducto() != null) {

            pdao.alta(pb.getProducto());
            tabla = pdao.getAll();
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Producto guardado con exito.", "");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            RequestContext.getCurrentInstance().execute("PF('confirmarAltaProducto').hide()");

            resultado = "producto.xhtml?faces-redirect=true";

        } else {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error.", "");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
        return resultado;
    }

    //Este metodo nos permite nos permite preferir el cuadro de dialogo entre alta y modificacion de usuario
    public void visualizarVentanaConfirmacion() {
        if (isAlta() == true) {
            RequestContext.getCurrentInstance().execute("PF('confirmarAltaProducto').hide()");
        } else {
            RequestContext.getCurrentInstance().execute("PF('confirmarModificacionProducto').hide()");
        }
    }
    
}