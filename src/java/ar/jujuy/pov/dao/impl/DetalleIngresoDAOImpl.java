package ar.jujuy.pov.dao.impl;

import ar.jujuy.pov.dao.DetalleIngresoDAO;
import ar.jujuy.pov.hibernate.configuracion.HibernateUtil;
import ar.jujuy.pov.modelo.dominio.DetalleIngreso;
import ar.jujuy.pov.modelo.dominio.Producto;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

public class DetalleIngresoDAOImpl implements DetalleIngresoDAO,Serializable {

    @Override
    public List<DetalleIngreso> getAll() {
        List<DetalleIngreso> listaDetalleIngresos = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(DetalleIngreso.class);
            criteria.setMaxResults(100);
            listaDetalleIngresos = criteria.list();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return listaDetalleIngresos;
    }

    @Override
    public void alta(DetalleIngreso detalleIngreso) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(detalleIngreso);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void modificar(DetalleIngreso detalleIngreso) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(DetalleIngreso detalleIngreso) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DetalleIngreso> getAllbyEncabenzadoIngreso(long id) {
        List<DetalleIngreso> listaDetalleIngresos = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from DetalleIngreso di where di.encabezadoIngreso.idIngreso = :id");
            query.setParameter("id", id);
            listaDetalleIngresos = query.list();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return listaDetalleIngresos;
    }

    @Override
    public void altaArray(List<DetalleIngreso> list) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(list.get(0).getEncabezadoIngreso());
            session.getTransaction().commit();
            
            for (DetalleIngreso di : list) {
                session.beginTransaction();
                session.save(di);
                session.getTransaction().commit();

                Query query = session.createQuery("from Producto p where p.idProducto = :id");
                query.setParameter("id", di.getProducto().getIdProducto());
                Producto p = (Producto) query.uniqueResult();
                p.setStock(p.getStock().add(di.getCantidad()));
                
                session.beginTransaction();
                session.update(p);
                session.getTransaction().commit();
            }

        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
