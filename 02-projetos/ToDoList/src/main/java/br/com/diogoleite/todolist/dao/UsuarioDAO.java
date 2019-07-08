/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.diogoleite.todolist.dao;

import br.com.diogoleite.todolist.model.Tarefa;
import br.com.diogoleite.todolist.model.Usuario;
import br.com.diogoleite.todolist.util.JPAUtil;
import br.com.diogoleite.todolist.util.Util;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;


/**
 *
 * @author Diogo Leite
 */
public class UsuarioDAO extends DAOGeneric<Usuario> implements IDAOGeneric<Usuario>{
    public Usuario login(String login, String senha){
        EntityManager manager = JPAUtil.getEntityManager();
        try {
            Usuario usuario = (Usuario) manager.createQuery("SELECT u from Usuario u where u.login = :login and u.senha = :senha")
            .setParameter("login", login)
            .setParameter("senha", Util.convertStringToMd5(senha)).getSingleResult();

            return usuario;
        } catch (NoResultException e) {
            return null;
        }
    }
}
