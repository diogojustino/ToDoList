/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.diogoleite.todolist.dao;

import java.util.List;

/**
 *
 * @author Diogo Leite
 */
interface IDAOGeneric<T> {
    
    public T save(T t) throws Exception;

    public void update(T t) throws Exception;

    public void remove(T t) throws Exception;

    public T findById(Object id) throws Exception;

    public List<T> get() throws Exception;
}
