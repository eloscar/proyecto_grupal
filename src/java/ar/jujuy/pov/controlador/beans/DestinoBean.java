/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.jujuy.pov.controlador.beans;

import ar.jujuy.pov.modelo.dominio.Destino;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Darkmarus
 */
@ManagedBean
@ViewScoped
public class DestinoBean implements java.io.Serializable {
    private Destino destino;

    public DestinoBean() {
        super();
        destino=new Destino();
    }

    public Destino getDestino() {
        return destino;
    }

    public void setDestino(Destino destino) {
        this.destino = destino;
    }

}
