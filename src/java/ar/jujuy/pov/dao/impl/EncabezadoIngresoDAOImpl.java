/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.jujuy.pov.dao.impl;

import ar.jujuy.pov.dao.EncabezadoIngresoDAO;
import ar.jujuy.pov.hibernate.configuracion.HibernateUtil;
import ar.jujuy.pov.modelo.dominio.EncabezadoIngreso;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Darkmarus
 */
public class EncabezadoIngresoDAOImpl implements EncabezadoIngresoDAO,Serializable{

    @Override
    public List<EncabezadoIngreso> getAll(int cantidad) {
        List<EncabezadoIngreso> listaEncabezados = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(EncabezadoIngreso.class);
            criteria.addOrder(Order.desc("fechaCreacion"));
            if (cantidad!=0) {
                criteria.setMaxResults(cantidad);
            }
            listaEncabezados=criteria.list();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        }finally{
            if (session != null) {
                session.close();
            }
        }
        return listaEncabezados;
    }

    @Override
    public void alta(EncabezadoIngreso ei) {
        Session session=null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(ei);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        }finally{
            if (session!=null) {
                session.close();
            }
        }
    }

    @Override
    public void modificar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<EncabezadoIngreso> filtrar(int cantidad,EncabezadoIngreso ei,Date desde,Date hasta) {
        List<EncabezadoIngreso> listaEncabezados = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(EncabezadoIngreso.class);
            criteria.addOrder(Order.desc("fechaCreacion"));
            if (cantidad!=0) {
                criteria.setMaxResults(cantidad);
            }
//            parametros de busqueda
            if (!ei.getNumeroFactura().equals("")) {
                criteria.add(Restrictions.ilike("numeroFactura", "%"+ei.getNumeroFactura()+"%"));
            }
            if (!ei.getNumeroOrden().equals("")) {
                criteria.add(Restrictions.ilike("numeroOrden", "%"+ei.getNumeroOrden()+"%"));
            }
            if(!ei.getNumeroRecibo().equals("")){
                criteria.add(Restrictions.ilike("numeroRecibo","%"+ ei.getNumeroRecibo()+"%"));
            }
            if(!ei.getProveedor().getDescripcion().equals("")){
                criteria.add(Restrictions.eq("proveedor.idProveedor",ei.getProveedor().getIdProveedor()));
            }
            if (desde!=null) {
                criteria.add(Restrictions.ge("fechaCompra", desde));
            }
            if (hasta!=null) {
                criteria.add(Restrictions.le("fechaCompra", hasta));
            }
            
            listaEncabezados=criteria.list();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        }finally{
            if (session != null) {
                session.close();
            }
        }
        return listaEncabezados;
    }
    
}
