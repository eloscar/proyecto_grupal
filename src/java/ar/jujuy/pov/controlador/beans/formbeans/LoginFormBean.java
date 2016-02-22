package ar.jujuy.pov.controlador.beans.formbeans;

import ar.jujuy.pov.dao.UsuarioDAO;
import ar.jujuy.pov.dao.impl.UsuarioDAOImpl;
import ar.jujuy.pov.modelo.dominio.Usuario;
import java.io.File;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped

public class LoginFormBean implements java.io.Serializable {

    private String user;
    private String password;

    private final UsuarioDAO usuarioDAO;

    public LoginFormBean() {
        super();
        usuarioDAO = new UsuarioDAOImpl();
    }
//    Getter y Setter de los atributos

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    metodos del beans
    public String validarAcceso() {
        Usuario unUsuario = usuarioDAO.validarAcceso(user, password);
        if (unUsuario != null) {
            if (getUsuarioLogueado() != null) {
                FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            }
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioLogueado", unUsuario);
            return "/index?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Acceso Invalido."));
        }
        return null;
    }

    public Usuario getUsuarioLogueado() {
        return (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogueado");
    }

    public boolean vereficarSesion() {
        Usuario logeado = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogueado");
        return logeado != null;
    }

    public String cerrarSesion() {
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sesión Cerrada", "Sesión Cerrada");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
//        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("usuarioValidado");
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/login.jsf/?faces-redirect=true";
    }

    public String imagenUsuario() {
        if (vereficarSesion()) {
            Usuario u = getUsuarioLogueado();
            if (u.getImagen() != null) {
                File f = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("") + File.separator + "galeria" + File.separator + "usuario" + File.separator + u.getImagen());
                if (f.exists()) {
                    return "/galeria/usuario/" + u.getImagen();
                }
            }
        }
        return "/resources/img/Icon-user.png";

    }
}
