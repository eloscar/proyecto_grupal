package ar.jujuy.pov.dao;
// Generated 27/11/2015 23:48:37 by Hibernate Tools 4.3.1

import ar.jujuy.pov.modelo.dominio.Usuario;
import java.util.List;

public interface UsuarioDAO {

    public List<Usuario> getAll();
    
    public List<Usuario> getUsuariosByRol(short id);

    public void alta();

    public void modificar(Usuario usuario);

    public void eliminar();
    
    public Usuario validarAcceso(String u , String p);
    
    public void estado(Usuario u,boolean estado);
    
}
