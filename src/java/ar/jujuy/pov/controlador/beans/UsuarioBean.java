package ar.jujuy.pov.controlador.beans;

import ar.jujuy.pov.modelo.dominio.Usuario;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class UsuarioBean implements java.io.Serializable {

    private Usuario usuario;


    public UsuarioBean() {
        super();
        usuario=new Usuario();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
