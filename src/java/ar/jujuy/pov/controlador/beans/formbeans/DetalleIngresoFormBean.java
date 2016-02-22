package ar.jujuy.pov.controlador.beans.formbeans;

import ar.jujuy.pov.controlador.beans.DetalleIngresoBean;
import ar.jujuy.pov.dao.DetalleIngresoDAO;
import ar.jujuy.pov.dao.impl.DetalleIngresoDAOImpl;
import ar.jujuy.pov.modelo.dominio.DetalleIngreso;
import ar.jujuy.pov.modelo.dominio.DetalleUnidad;
import ar.jujuy.pov.modelo.dominio.EncabezadoIngreso;
import ar.jujuy.pov.modelo.dominio.Producto;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.tomcat.jni.Time;
import org.primefaces.context.RequestContext;

@ManagedBean
@ViewScoped
public class DetalleIngresoFormBean implements java.io.Serializable {

    @ManagedProperty(value = "#{detalleIngresoBean}")
    private DetalleIngresoBean detalleIngresoBean;
    private List<DetalleIngreso> tablaDetalleIngreso;
    private List<DetalleIngreso> tablaDetalleIngresoFiltro;
    private final DetalleIngresoDAO didao;
    private int posicion;

    public DetalleIngresoFormBean() {
        super();
        didao = new DetalleIngresoDAOImpl();
        EncabezadoIngreso ei = (EncabezadoIngreso) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("encabezadoIngresoActual");
        tablaDetalleIngreso = new ArrayList<>();
    }
//    Getter y Setter de los atributos

    public DetalleIngresoBean getDetalleIngresoBean() {
        return detalleIngresoBean;
    }

    public void setDetalleIngresoBean(DetalleIngresoBean detalleIngresoBean) {
        this.detalleIngresoBean = detalleIngresoBean;
    }

    public List<DetalleIngreso> getTablaDetalleIngreso() {
        return tablaDetalleIngreso;
    }

    public void setTablaDetalleIngreso(List<DetalleIngreso> tablaDetalleIngreso) {
        this.tablaDetalleIngreso = tablaDetalleIngreso;
    }

    public List<DetalleIngreso> getTablaDetalleIngresoFiltro() {
        return tablaDetalleIngresoFiltro;
    }

    public void setTablaDetalleIngresoFiltro(List<DetalleIngreso> tablaDetalleIngresoFiltro) {
        this.tablaDetalleIngresoFiltro = tablaDetalleIngresoFiltro;
    }

//    Metodos del form beans
    public BigDecimal subtotal(DetalleIngreso din) {
        return din.getCantidad().multiply(din.getPrecioUnitario());
    }

    public BigDecimal total() {
        BigDecimal total = new BigDecimal(0);

        for (DetalleIngreso detalleIngreso : tablaDetalleIngreso) {
            total = total.add(subtotal(detalleIngreso));
        }
        return total;
    }

//    Metodos de la clase
    public void guardarProducto() {
        FacesContext context = FacesContext.getCurrentInstance();
        Producto producto = context.getApplication().evaluateExpressionGet(context, "#{productoFormBean.producto}", Producto.class);
        if (producto != null) {
            detalleIngresoBean.getDetalleIngreso().setProducto(producto);
            RequestContext.getCurrentInstance().execute("PF('widProducto').hide()");
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Debe seleccionar un producto."));
        }
    }

    public void modificarDetalle() {
        boolean banderaProducto = false;
        for (int i = 0; i < tablaDetalleIngreso.size(); i++) {
            if (tablaDetalleIngreso.get(i).getProducto().getIdProducto() == detalleIngresoBean.getDetalleIngreso().getProducto().getIdProducto() && (i != posicion)) {
                banderaProducto = true;
                break;
            }
        }
        if (banderaProducto) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Existe", "Producto ya se encuentra agregado."));
        } else {
            tablaDetalleIngreso.set(posicion, detalleIngresoBean.getDetalleIngreso());
            RequestContext.getCurrentInstance().execute("PF('widEditar').hide()");
        }
    }

    public void guardarDetalle() {
        boolean banderaProducto = false;
        for (DetalleIngreso dt : tablaDetalleIngreso) {
            if (dt.getProducto().getIdProducto() == detalleIngresoBean.getDetalleIngreso().getProducto().getIdProducto()) {
                banderaProducto = true;
                break;
            }
        }
        if (banderaProducto) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Existe", "Producto ya se encuentra agregado."));
        } else {
            tablaDetalleIngreso.add(detalleIngresoBean.getDetalleIngreso());
            RequestContext.getCurrentInstance().execute("PF('widNuevo').hide()");
        }
    }

    public void eliminarDetalle(DetalleIngreso di) {
        tablaDetalleIngreso.remove(di);
    }

    public void limpiar() {
        detalleIngresoBean = new DetalleIngresoBean();
        detalleIngresoBean.getDetalleIngreso().setEncabezadoIngreso((EncabezadoIngreso) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("encabezadoIngresoActual"));
        RequestContext.getCurrentInstance().execute("PF('widNuevo').show()");
    }

    public void cargarDetalle(DetalleIngreso di) {
        posicion = tablaDetalleIngreso.indexOf(di);
        detalleIngresoBean = new DetalleIngresoBean();
        detalleIngresoBean.setDetalleIngreso(new DetalleIngreso(di.getEncabezadoIngreso(), di.getProducto(), di.getCantidad(), di.getPrecioUnitario()));
        RequestContext.getCurrentInstance().execute("PF('widEditar').show()");
    }

    public boolean abreviatura() {
        if (detalleIngresoBean.getDetalleIngreso().getProducto().getDetalleUnidad() != null) {
            switch (detalleIngresoBean.getDetalleIngreso().getProducto().getDetalleUnidad().getAbreviatura()) {
                case "L":
                    return false;
                case "unid.":
                    return true;
                case "m":
                    return false;
                case "m2":
                    return true;
                case "Kg":
                    return false;
                default:
                    return false;
            }
        } else {
            return false;
        }
    }
    
    public boolean abreviaturaTabla(DetalleUnidad du) {
        
        if (du != null) {
            switch (du.getAbreviatura()) {
                case "L":
                    return false;
                case "unid.":
                    return true;
                case "m":
                    return false;
                case "m2":
                    return true;
                case "Kg":
                    return false;
                default:
                    return false;
            }
        } else {
            return false;
        }
    }

    public String guardarDetalleIngreso() {
        if (!tablaDetalleIngreso.isEmpty()) {
            didao.altaArray(tablaDetalleIngreso);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Operación concretada."));
            Time.sleep(2000);
            return "/sitio/ingreso/ingreso.xhtml";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Debe cargar detalles."));
            return null;
        }
    }
    
    public void cargarListaDetalles(EncabezadoIngreso encabezadoIngreso){
        tablaDetalleIngreso=didao.getAllbyEncabenzadoIngreso(encabezadoIngreso.getIdIngreso());
    }
}
