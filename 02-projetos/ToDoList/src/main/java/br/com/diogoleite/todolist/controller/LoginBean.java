/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.diogoleite.todolist.controller;

import br.com.diogoleite.todolist.dao.UsuarioDAO;
import br.com.diogoleite.todolist.model.Usuario;
import br.com.diogoleite.todolist.util.FacesUtil;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Diogo Leite
 */
@ManagedBean
@SessionScoped
public class LoginBean implements Serializable{
    
    private String login;
    private String senha;
    private Usuario usuarioLogado;
    private boolean logado = false;
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isLogado() {
        return logado;
    }

    public void setLogado(boolean logado) {
        this.logado = logado;
    }
    
    
   
    public String logar(){
        login = login.trim();
        senha = senha.trim();
        if(login.equals("")){
            FacesUtil.addErrorMessage("Login está vazio.");
            return null;
        }
        
        if(senha.equals("")){
            FacesUtil.addErrorMessage("Senha está vazio.");
            return null;
        }
       
        usuarioLogado = usuarioDAO.login(login, senha);
        
        if(usuarioLogado != null){
         
            logado = true;
            FacesContext fc = FacesContext.getCurrentInstance();

            //colocar valor em sessão 
            fc.getExternalContext().getSessionMap().put("usuarioLogado", usuarioLogado);
            return "/privado/tarefas.xhtml?faces-redirect=true";
        }else{
            FacesUtil.addErrorMessage("Dados invalidos.");
            return "/login.xhtml";
        }
    }
    
    public String logout(){
        logado = false;
        usuarioLogado = null;
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        session.invalidate();
        return "/login.xhtml?faces-redirect=true";
    }
   
}
