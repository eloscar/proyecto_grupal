package ar.jujuy.pov.controlador.beans;

import ar.jujuy.pov.modelo.dominio.Proveedor;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class ProveedorBean implements java.io.Serializable {

    private Proveedor proveedor;
    
    public ProveedorBean() {
        super();
        proveedor=new Proveedor();
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
    
}
