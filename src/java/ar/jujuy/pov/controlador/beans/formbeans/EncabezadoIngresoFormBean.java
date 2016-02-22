package ar.jujuy.pov.controlador.beans.formbeans;

import ar.jujuy.pov.controlador.beans.EncabezadoIngresoBean;
import ar.jujuy.pov.dao.EncabezadoIngresoDAO;
import ar.jujuy.pov.dao.impl.EncabezadoIngresoDAOImpl;
import ar.jujuy.pov.modelo.dominio.Destino;
import ar.jujuy.pov.modelo.dominio.EncabezadoIngreso;
import ar.jujuy.pov.modelo.dominio.Proveedor;
import ar.jujuy.pov.modelo.dominio.Usuario;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

@ViewScoped
@ManagedBean
public class EncabezadoIngresoFormBean implements java.io.Serializable {

    @ManagedProperty(value = "#{encabezadoIngresoBean}")
    private EncabezadoIngresoBean eib;
    private List<EncabezadoIngreso> tabla;
    private EncabezadoIngresoDAO eidao;
    private EncabezadoIngreso seleccionado;
    private Usuario encargado;
    private short deposito;
    private short supervisor;
    private Date hoy;
    private boolean orden;
    private boolean recibo;
    private boolean proveedor;
//    Variables para la busqueda
    private EncabezadoIngreso eiBuscado;
    private Date desde;
    private Date hasta;
    private int cantidad;

    public EncabezadoIngresoFormBean() {
        super();
        eiBuscado = new EncabezadoIngreso();
        eiBuscado.setProveedor(new Proveedor());
        eidao = new EncabezadoIngresoDAOImpl();
        cantidad=100;
        tabla = eidao.getAll(cantidad);
        hoy = new Date();
    }

//    Getter y Setter de los atributos
    public EncabezadoIngresoBean getEib() {
        return eib;
    }

    public void setEib(EncabezadoIngresoBean eib) {
        this.eib = eib;
    }

    public List<EncabezadoIngreso> getTabla() {
        return tabla;
    }

    public void setTabla(List<EncabezadoIngreso> tabla) {
        this.tabla = tabla;
    }

    public short getDeposito() {
        return deposito;
    }

    public void setDeposito(short deposito) {
        this.deposito = deposito;
    }

    public short getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(short supervisor) {
        this.supervisor = supervisor;
    }

    public Usuario getEncargado() {
        return encargado;
    }

    public void setEncargado(Usuario encargado) {
        this.encargado = encargado;
    }

    public Date getHoy() {
        return hoy;
    }

    public void setHoy(Date hoy) {
        this.hoy = hoy;
    }

    public boolean isOrden() {
        return orden;
    }

    public void setOrden(boolean orden) {
        this.orden = orden;
    }

    public boolean isRecibo() {
        return recibo;
    }

    public void setRecibo(boolean recibo) {
        this.recibo = recibo;
    }

    public EncabezadoIngreso getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(EncabezadoIngreso seleccionado) {
        this.seleccionado = seleccionado;
    }

    public EncabezadoIngreso getEiBuscado() {
        return eiBuscado;
    }

    public void setEiBuscado(EncabezadoIngreso eiBuscado) {
        this.eiBuscado = eiBuscado;
    }

    public boolean isProveedor() {
        return proveedor;
    }

    public void setProveedor(boolean proveedor) {
        this.proveedor = proveedor;
    }

    public Date getDesde() {
        return desde;
    }

    public void setDesde(Date desde) {
        this.desde = desde;
    }

    public Date getHasta() {
        return hasta;
    }

    public void setHasta(Date hasta) {
        this.hasta = hasta;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    

//    Metodos de la clase
    public void limpiarNuevo() {
        eib.setEncabezadoIngreso(new EncabezadoIngreso());
        eib.getEncabezadoIngreso().setDestino(new Destino());
        eib.getEncabezadoIngreso().setProveedor(new Proveedor());
        orden = false;
        cantidad=100;
        recibo = false;
        RequestContext.getCurrentInstance().execute("PF('widNuevoIngreso').show()");
    }

    public void verDestino() {
        RequestContext.getCurrentInstance().execute("PF('widDestino').show()");
    }

    public void guardarDestino() {
        FacesContext context = FacesContext.getCurrentInstance();
        Destino d = context.getApplication().evaluateExpressionGet(context, "#{destinoFormBean.destino}", Destino.class);
        eib.getEncabezadoIngreso().setDestino(d);
        RequestContext.getCurrentInstance().execute("PF('widDestino').hide()");
    }

    public void guardarProveedor() {
        FacesContext context = FacesContext.getCurrentInstance();
        Proveedor prov = context.getApplication().evaluateExpressionGet(context, "#{proveedorFormBean.proveedor}", Proveedor.class);
        eib.getEncabezadoIngreso().setProveedor(prov);
        RequestContext.getCurrentInstance().execute("PF('widProveedor').hide()");
    }

    public void guardarProveedor2() {
        FacesContext context = FacesContext.getCurrentInstance();
        Proveedor prov = context.getApplication().evaluateExpressionGet(context, "#{proveedorFormBean.proveedor}", Proveedor.class);
        eiBuscado.setProveedor(prov);
        RequestContext.getCurrentInstance().execute("PF('widProveedor2').hide()");
    }

    public String guardarEncabezado() {
        try {
            Usuario u = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogueado");
            if (u != null) {
                if (!recibo) {
                    eib.getEncabezadoIngreso().setNumeroRecibo(null);
                }
                if (!orden) {
                    eib.getEncabezadoIngreso().setNumeroOrden(null);
                }
                eib.getEncabezadoIngreso().setFechaCreacion(new Date());
                eib.getEncabezadoIngreso().setUsuario(u);
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("encabezadoIngresoActual", eib.getEncabezadoIngreso());
                return "/sitio/ingreso/detalle.jsf?faces-redirect=true";
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Esta operación necesita usuario registrado."));
                return null;
            }

        } catch (Exception e) {

        }
        return null;

    }

    public String mostrarDetalles() {
        if (seleccionado != null) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("encabezadoIngresoActual", seleccionado);
            return "/sitio/ingreso/detalle.jsf?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Debe Seleccionar un Encabezado."));
            return null;
        }
    }

    public EncabezadoIngreso getEncabezadoIngreso() {
        return (EncabezadoIngreso) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("encabezadoIngresoActual");
    }

    public boolean vereficarSesion() {
        EncabezadoIngreso encabezadoIngreso = (EncabezadoIngreso) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("encabezadoIngresoActual");
        return encabezadoIngreso != null;
    }

    public String removerEncabezadoActual() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("encabezadoIngresoActual");
        return "/sitio/ingreso/ingreso.jsf?faces-redirect=true";
    }

    public void filtrarTabla() {
        tabla = eidao.filtrar(cantidad,eiBuscado,desde,hasta);
    }
    
    public void limpiarFiltro(){
        eiBuscado=new EncabezadoIngreso();
        eiBuscado.setProveedor(new Proveedor());
        desde=null;
        hasta=null;
                
    }

    public void vistaProveedor(){
        System.out.println("Ejecutado");
    }
}
