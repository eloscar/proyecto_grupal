/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.jujuy.pov.dao.impl;

import ar.jujuy.pov.dao.DetalleUnidadDAO;
import ar.jujuy.pov.hibernate.configuracion.HibernateUtil;
import ar.jujuy.pov.modelo.dominio.DetalleUnidad;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author FAMILIA
 */
public class DetalleUnidadDAOImpl implements DetalleUnidadDAO,Serializable{
    
    
    @Override
    public List<DetalleUnidad> getAll() {
        List<DetalleUnidad> listaDetalleUnidad = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(DetalleUnidad.class);
            listaDetalleUnidad=criteria.list();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        }finally{
            if (session != null) {
                session.close();
            }
        }
        return listaDetalleUnidad;
    }
    
    @Override
    public void alta(DetalleUnidad du) {
        Session session=null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(du);
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
    public void modificar(DetalleUnidad du) {
        Session session=null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(du);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        }finally{
            if (session!=null) {
                session.close();
            }
        }    }

    @Override
    public void eliminar(DetalleUnidad du) {
        Session session=null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(du);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage()+"hola");
            session.getTransaction().rollback();
        }finally{
            if (session!=null) {
                session.close();
            }
        }   
    }
    
}
