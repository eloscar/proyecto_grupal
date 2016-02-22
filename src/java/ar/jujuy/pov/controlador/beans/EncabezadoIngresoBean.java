package ar.jujuy.pov.controlador.beans;

import ar.jujuy.pov.modelo.dominio.EncabezadoIngreso;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean
@ViewScoped
public class EncabezadoIngresoBean implements java.io.Serializable {
    
    private EncabezadoIngreso encabezadoIngreso;

    public EncabezadoIngresoBean() {
        super();
        encabezadoIngreso=new EncabezadoIngreso();
    }

    public EncabezadoIngreso getEncabezadoIngreso() {
        return encabezadoIngreso;
    }

    public void setEncabezadoIngreso(EncabezadoIngreso encabezadoIngreso) {
        this.encabezadoIngreso = encabezadoIngreso;
    }
    
    
}
