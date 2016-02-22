package ar.jujuy.pov.dao.impl;

import ar.jujuy.pov.dao.UsuarioDAO;
import ar.jujuy.pov.hibernate.configuracion.HibernateUtil;
import ar.jujuy.pov.modelo.dominio.Usuario;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class UsuarioDAOImpl extends HibernateUtil implements UsuarioDAO,Serializable {

    @Override
    public List<Usuario> getAll() {
        List<Usuario> listaUsuarios = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(Usuario.class);
            criteria.setMaxResults(100);
            criteria.createAlias("rol", "r");
            criteria.addOrder(Order.asc("r.descripcion"));
            listaUsuarios = criteria.list();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return listaUsuarios;
    }

    @Override
    public void alta() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificar(Usuario usuario) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(usuario);
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
    public void eliminar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuario validarAcceso(String u, String p) {

        Session session = getSessionFactory().openSession();
        Usuario encontrado = null;
        try {
            Criteria criteria = session.createCriteria(Usuario.class);
            criteria.add(Restrictions.eq("userName", u));
            criteria.add(Restrictions.eq("password", p));
            criteria.add(Restrictions.eq("estado", true));
            encontrado = (Usuario) criteria.uniqueResult();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return encontrado;
    }

    @Override
    public List<Usuario> getUsuariosByRol(short id) {
        List<Usuario> listaUsuarios = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from Usuario u where u.rol.id = :id");
            query.setParameter("id", id);
            listaUsuarios = query.list();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return listaUsuarios;
    }

    @Override
    public void estado(Usuario u,boolean estado){
        u.setEstado(estado);
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(u);
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
}
