/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.diogoleite.todolist.dao;

import br.com.diogoleite.todolist.util.JPAUtil;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.persistence.EntityManager;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

/**
 *
 * @author Diogo Leite
 */
public class DAOGeneric <T> implements IDAOGeneric<T>{
    
    private Class classe;
    public DAOGeneric() {
        classe = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public T save(T t) throws Exception {
        EntityManager manager = JPAUtil.getEntityManager();

        try {
                manager.getTransaction().begin();
                
                manager.merge(t);

                manager.getTransaction().commit();
                return t;
        } catch (Exception e) {
                manager.getTransaction().rollback();
                e.printStackTrace();
                throw new Exception("Erro ao salvar.");
        }finally {
                manager.close();
        }
    }

    @Override
    public void update(T t) throws Exception {
        EntityManager manager = JPAUtil.getEntityManager();
        try {

            manager.getTransaction().begin();

            manager.merge(t);
            manager.getTransaction().commit();

        } catch (Exception e) {
            manager.getTransaction().rollback();
            e.printStackTrace();
            throw new Exception("Erro ao atualizar.");
        }finally {
             manager.close();
        }

    }

    public T findById(Object id) throws Exception {
        EntityManager manager = JPAUtil.getEntityManager();
        T t = (T) manager.find(this.classe, id);
       manager.close();
        return t;
    }

    @Override
    public void remove(T t) throws Exception {

        EntityManager manager = JPAUtil.getEntityManager();
        try {
            manager.getTransaction().begin();
            t = manager.merge(t);
            manager.remove(t);
            manager.getTransaction().commit();
     

        } catch (Exception e) {
            manager.getTransaction().rollback();
            e.printStackTrace();

            throw new Exception("Erro ao remover.");
        }finally {
            manager.close();
        }

    }

    @Override
    public List<T> get() throws Exception {
        EntityManager manager = JPAUtil.getEntityManager();
        try {
            Criteria criteria = getCriteria(manager);
            criteria.addOrder(Order.asc("id"));
            List<T> dados = criteria.list();
            return dados;

        } catch (Exception e) {
            e.printStackTrace();

            throw new Exception("Erro ao buscar.");
        }finally {
            manager.close();
        }
    }

    private Criteria getCriteria(EntityManager manager) {

        Session session = ((Session) manager.getDelegate());
        return  session.createCriteria(this.classe);
    }

}
