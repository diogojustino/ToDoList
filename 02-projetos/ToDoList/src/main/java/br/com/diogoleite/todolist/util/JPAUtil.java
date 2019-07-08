/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.diogoleite.todolist.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Diogo Leite
 */
public class JPAUtil {
    private static final EntityManagerFactory emf;
    private static final String PERSISTENCE_UNIT_NAME = "default";
    static {
        emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    public static EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
}
