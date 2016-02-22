package ar.jujuy.pov.dao;
// Generated 27/11/2015 23:48:37 by Hibernate Tools 4.3.1

import ar.jujuy.pov.modelo.dominio.Rol;
import java.util.List;

public interface RolDAO {

    public List<Rol> getAll();

    public void alta();

    public void modificar();

    public void eliminar();
}
