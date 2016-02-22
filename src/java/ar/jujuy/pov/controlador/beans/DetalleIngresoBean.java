package ar.jujuy.pov.controlador.beans;

import ar.jujuy.pov.modelo.dominio.DetalleIngreso;
import ar.jujuy.pov.modelo.dominio.EncabezadoIngreso;
import ar.jujuy.pov.modelo.dominio.Producto;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class DetalleIngresoBean implements java.io.Serializable{
    
    private DetalleIngreso detalleIngreso;
    
    public DetalleIngresoBean() {
        detalleIngreso=new DetalleIngreso();
        detalleIngreso.setProducto(new Producto());
        detalleIngreso.setEncabezadoIngreso(new EncabezadoIngreso());
    }

    public DetalleIngreso getDetalleIngreso() {
        return detalleIngreso;
    }

    public void setDetalleIngreso(DetalleIngreso detalleIngreso) {
        this.detalleIngreso = detalleIngreso;
    }
    
}
