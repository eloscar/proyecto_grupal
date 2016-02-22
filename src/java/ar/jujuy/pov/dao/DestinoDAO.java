package ar.jujuy.pov.dao;
// Generated 27/11/2015 23:48:37 by Hibernate Tools 4.3.1

import java.util.List;
import ar.jujuy.pov.modelo.dominio.Destino;

public interface DestinoDAO {

    public List<Destino> getAll();

    public void alta();

    public void modificar();

    public void eliminar();

}
