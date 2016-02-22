package ar.jujuy.pov.modelo.dominio;
// Generated 03/12/2015 04:50:55 by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * EncabezadoEgreso generated by hbm2java
 */
public class EncabezadoEgreso  implements java.io.Serializable {


     private long idEgreso;
     private Destino destino;
     private Usuario usuario;
     private Date fechaCreacion;
     private Date fechaEgreso;
     private Integer ordenEntrega;
     private Integer expendiente;
     private String ordenRecepcion;
     private int encargadoDepositoId;
     private int encargadoResponableId;

    public EncabezadoEgreso() {
    }

    public long getIdEgreso() {
        return this.idEgreso;
    }
    
    public void setIdEgreso(long idEgreso) {
        this.idEgreso = idEgreso;
    }
    public Destino getDestino() {
        return this.destino;
    }
    
    public void setDestino(Destino destino) {
        this.destino = destino;
    }
    public Usuario getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }
    
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    public Date getFechaEgreso() {
        return this.fechaEgreso;
    }
    
    public void setFechaEgreso(Date fechaEgreso) {
        this.fechaEgreso = fechaEgreso;
    }
    public Integer getOrdenEntrega() {
        return this.ordenEntrega;
    }
    
    public void setOrdenEntrega(Integer ordenEntrega) {
        this.ordenEntrega = ordenEntrega;
    }
    public Integer getExpendiente() {
        return this.expendiente;
    }
    
    public void setExpendiente(Integer expendiente) {
        this.expendiente = expendiente;
    }
    public String getOrdenRecepcion() {
        return this.ordenRecepcion;
    }
    
    public void setOrdenRecepcion(String ordenRecepcion) {
        this.ordenRecepcion = ordenRecepcion;
    }
    public int getEncargadoDepositoId() {
        return this.encargadoDepositoId;
    }
    
    public void setEncargadoDepositoId(int encargadoDepositoId) {
        this.encargadoDepositoId = encargadoDepositoId;
    }
    public int getEncargadoResponableId() {
        return this.encargadoResponableId;
    }
    
    public void setEncargadoResponableId(int encargadoResponableId) {
        this.encargadoResponableId = encargadoResponableId;
    }

}


