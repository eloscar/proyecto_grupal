package ar.jujuy.pov.controlador.beans.formbeans;

import ar.jujuy.pov.controlador.beans.ProveedorBean;
import ar.jujuy.pov.dao.ProveedorDAO;
import ar.jujuy.pov.dao.impl.ProveedorDAOImpl;
import ar.jujuy.pov.modelo.dominio.Proveedor;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class ProveedorFormBean implements java.io.Serializable{
    
    @ManagedProperty(value = "#{proveedorBean}")
    private ProveedorBean proveedorBean;
    private Proveedor proveedor;
    private List<Proveedor> tablaProveedro;
    private final ProveedorDAO pdao;

    public ProveedorFormBean() {
        super();
        pdao=new ProveedorDAOImpl();
        tablaProveedro=pdao.getAll();
    }
//    Getter y Setter
    public ProveedorBean getProveedorBean() {
        return proveedorBean;
    }

    public void setProveedorBean(ProveedorBean proveedorBean) {
        this.proveedorBean = proveedorBean;
    }

    public List<Proveedor> getTablaProveedro() {
        return tablaProveedro;
    }

    public void setTablaProveedro(List<Proveedor> tablaProveedro) {
        this.tablaProveedro = tablaProveedro;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
    
}
