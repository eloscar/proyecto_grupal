package ar.jujuy.pov.controlador.beans.formbeans;

import ar.jujuy.pov.controlador.beans.UsuarioBean;
import ar.jujuy.pov.dao.UsuarioDAO;
import ar.jujuy.pov.dao.impl.UsuarioDAOImpl;
import ar.jujuy.pov.modelo.dominio.Usuario;
import java.io.File;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class UsuarioFormBean implements java.io.Serializable {

    @ManagedProperty(value = "#{usuarioBean}")
    private UsuarioBean usuarioBean;
    private UsuarioDAO udao;
    private List<Usuario> tablaUsuario;

    public UsuarioFormBean() {
        super();
        udao = new UsuarioDAOImpl();
        tablaUsuario = udao.getAll();
    }
//  Getter y Setter de los atributos

    public UsuarioBean getUsuarioBean() {
        return usuarioBean;
    }

    public void setUsuarioBean(UsuarioBean usuarioBean) {
        this.usuarioBean = usuarioBean;
    }

    public List<Usuario> getUsuarioSupervisores() {
        return udao.getUsuariosByRol((short) 2);
    }

    public List<Usuario> getEncargadoDepositos() {
        return udao.getUsuariosByRol((short) 4);
    }

    public List<Usuario> getTablaUsuario() {
        return tablaUsuario;
    }

    public void setTablaUsuario(List<Usuario> tablaUsuario) {
        this.tablaUsuario = tablaUsuario;
    }

//    metodos
    public String imagen(Usuario u) {
        if (u.getImagen() != null) {
            File f = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("") + File.separator + "galeria" + File.separator + "usuario" + File.separator + u.getImagen());
            if (f.exists()) {
                return "/galeria/usuario/" + u.getImagen();
            }
        }
        return "/resources/img/Icon-user.png";
    }
    
    public void habilitar(Usuario u){
        udao.estado(u, true);
    }

    public void deshabilitar(Usuario u){
        udao.estado(u, false);
    }
}
