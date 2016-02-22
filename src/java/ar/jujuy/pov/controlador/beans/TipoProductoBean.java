/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.jujuy.pov.controlador.beans;

import ar.jujuy.pov.modelo.dominio.TipoProducto;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class TipoProductoBean implements java.io.Serializable {
    
    private TipoProducto tipoProducto;

    public TipoProductoBean() {
       tipoProducto = new TipoProducto();
    }

    public TipoProducto getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(TipoProducto tipoProducto) {
        this.tipoProducto = tipoProducto;
    }
    
    
    
    
}
