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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Diogo Leite
 */
@ManagedBean
@SessionScoped
public class TarefasBean  {
    
    private Tarefa tarefaSelecionada;
    private Usuario usuarioLogado;
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private List<Tarefa> tarefasFiltradas = new ArrayList<>();
    
    public TarefasBean(){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        usuarioLogado = (Usuario) facesContext.getExternalContext().getSessionMap().get("usuarioLogado");
        carregarDados();
    }
    
    
    public void carregarDados(){
        tarefasFiltradas.clear();
        for(Tarefa t: usuarioLogado.getTarefas()){
            tarefasFiltradas.add(t);
        }
    }
    
    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public List<Tarefa> getTarefasFiltradas() {
        return tarefasFiltradas;
    }

    

    public void todos(){
        carregarDados();
    }
    
    public void concluidos(){
        tarefasFiltradas.clear();
        
        for(Tarefa tarefa: usuarioLogado.getTarefas()){
            if(tarefa.getStatus()){
                tarefasFiltradas.add(tarefa);
            }
            
        }
    }
    
    public void naoConcluidos(){
        tarefasFiltradas.clear();
       
        for(Tarefa tarefa: usuarioLogado.getTarefas()){

            if(!tarefa.getStatus()){
                tarefasFiltradas.add(tarefa);
            }
            
        }
    }
    
    public Tarefa getTarefaSelecionada() {
        return tarefaSelecionada;
    }

    public void setTarefaSelecionada(Tarefa tarefaSelecionada) {
        this.tarefaSelecionada = tarefaSelecionada;
    }

    
    public void excluir(){
        try {
            for(Tarefa tarefa: usuarioLogado.getTarefas()){

                if(tarefa.equals(tarefaSelecionada)){
                    usuarioLogado.removeTarefa(tarefa);
                    usuarioDAO.save(usuarioLogado);
                    tarefasFiltradas.remove(tarefa);
                    return;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(TarefasBean.class.getName()).log(Level.SEVERE, null, ex);
        }
               
    }
    
    public void mudarStatus(){
        try {

            usuarioDAO.update(usuarioLogado);
        } catch (Exception ex) {
            Logger.getLogger(TarefasBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
