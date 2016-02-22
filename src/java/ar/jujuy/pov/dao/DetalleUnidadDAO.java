package ar.jujuy.pov.dao;
// Generated 27/11/2015 23:48:37 by Hibernate Tools 4.3.1

import ar.jujuy.pov.modelo.dominio.DetalleUnidad;
import java.util.List;

public interface DetalleUnidadDAO {

    public List<DetalleUnidad> getAll();

    public void alta(DetalleUnidad du);

    public void modificar(DetalleUnidad du);

    public void eliminar(DetalleUnidad du);
}
