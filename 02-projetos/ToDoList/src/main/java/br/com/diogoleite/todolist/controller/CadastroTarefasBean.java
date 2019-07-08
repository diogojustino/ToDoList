/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.diogoleite.todolist.controller;

import br.com.diogoleite.todolist.dao.TarefaDAO;
import br.com.diogoleite.todolist.dao.UsuarioDAO;
import br.com.diogoleite.todolist.model.Tarefa;
import br.com.diogoleite.todolist.model.Usuario;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Diogo Leite
 */
@ManagedBean
@ViewScoped
public class CadastroTarefasBean implements Serializable{
    private Tarefa tarefa = new Tarefa();
    private Usuario usuarioLogado;
   
    
    
    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }
    
    
    
    public CadastroTarefasBean(){
        FacesContext fc = FacesContext.getCurrentInstance();

            //colocar valor em sessão 
        usuarioLogado = (Usuario) fc.getExternalContext().getSessionMap().get("usuarioLogado");
    }
    
    public Tarefa getTarefa() {
        return tarefa;
    }

    public void setTarefa(Tarefa tarefa) {
        this.tarefa = tarefa;
    }
    
    
    
    public String salvar(){
       
        try {
            usuarioLogado.addTarefa(tarefa);
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioDAO.save(usuarioLogado);
        } catch (Exception ex) {
            Logger.getLogger(CadastroTarefasBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "/privado/tarefas.xhtml?faces-redirect=true";
    }
    
    
    
}
