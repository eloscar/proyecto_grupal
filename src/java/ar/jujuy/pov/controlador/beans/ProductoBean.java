package ar.jujuy.pov.controlador.beans;

import ar.jujuy.pov.modelo.dominio.Producto;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class ProductoBean implements java.io.Serializable {
    
    private Producto producto;

    public ProductoBean() {
    
        producto= new Producto();
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
    
   
    
}
