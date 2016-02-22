package ar.jujuy.pov.dao;
// Generated 27/11/2015 23:48:37 by Hibernate Tools 4.3.1

import ar.jujuy.pov.modelo.dominio.DetalleIngreso;
import java.util.List;

public interface DetalleIngresoDAO {

    public List<DetalleIngreso> getAll();

    public List<DetalleIngreso> getAllbyEncabenzadoIngreso(long id);

    public void alta(DetalleIngreso detalleIngreso);

    public void modificar(DetalleIngreso detalleIngreso);

    public void eliminar(DetalleIngreso detalleIngreso);
    
    public void altaArray(List<DetalleIngreso> list);
}
