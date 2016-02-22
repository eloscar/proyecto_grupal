package ar.jujuy.pov.dao;
// Generated 27/11/2015 23:48:37 by Hibernate Tools 4.3.1

import ar.jujuy.pov.modelo.dominio.EncabezadoIngreso;
import java.util.Date;
import java.util.List;

public interface EncabezadoIngresoDAO {

    public List<EncabezadoIngreso> getAll(int cantidad);

    public void alta(EncabezadoIngreso ei);

    public void modificar();

    public void eliminar();
    
    public List<EncabezadoIngreso> filtrar(int cantidad,EncabezadoIngreso ei,Date desde,Date hasta);
}
