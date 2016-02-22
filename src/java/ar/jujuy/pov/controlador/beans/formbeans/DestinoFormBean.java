/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.jujuy.pov.controlador.beans.formbeans;

import ar.jujuy.pov.controlador.beans.DestinoBean;
import ar.jujuy.pov.dao.DestinoDAO;
import ar.jujuy.pov.dao.impl.DestinoDAOImpl;
import ar.jujuy.pov.modelo.dominio.Destino;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class DestinoFormBean implements java.io.Serializable{

    @ManagedProperty(value = "#{destinoBean}")
    private DestinoBean destinoBean;
    private Destino destino;
    private List<Destino> tablaDestino;
    private List<Destino> tablaDestinoFiltro;
    private final DestinoDAO destinoDAO;
    
    public DestinoFormBean() {
        super();
        destinoDAO=new DestinoDAOImpl();
        tablaDestino=destinoDAO.getAll();
    }
//    Getter y Setter

    public List<Destino> getTablaDestino() {
        return tablaDestino;
    }

    public void setTablaDestino(List<Destino> tablaDestino) {
        this.tablaDestino = tablaDestino;
    }

    public DestinoBean getDestinoBean() {
        return destinoBean;
    }

    public void setDestinoBean(DestinoBean destinoBean) {
        this.destinoBean = destinoBean;
    }
    
    public List<Destino> getTablaDestinoFiltro() {
        return tablaDestinoFiltro;
    }

    public void setTablaDestinoFiltro(List<Destino> tablaDestinoFiltro) {
        this.tablaDestinoFiltro = tablaDestinoFiltro;
    }

    public Destino getDestino() {
        return destino;
    }

    public void setDestino(Destino destino) {
        this.destino = destino;
    }
    
}
