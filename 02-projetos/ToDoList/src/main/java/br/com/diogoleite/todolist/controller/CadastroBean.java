/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.diogoleite.todolist.controller;

import br.com.diogoleite.todolist.dao.UsuarioDAO;
import br.com.diogoleite.todolist.model.Usuario;
import br.com.diogoleite.todolist.util.FacesUtil;
import br.com.diogoleite.todolist.util.Util;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


/**
 *
 * @author Diogo Leite
 */
@ManagedBean
@SessionScoped
public class CadastroBean implements Serializable {

    private Usuario usuario = new Usuario();
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
   
    
    
    public String cadastrar(){
        usuario.setNome(usuario.getNome().trim());
        usuario.setLogin(usuario.getLogin().trim());
        usuario.setSenha(usuario.getSenha().trim());
        
        if(usuario.getNome().equals("")){
            FacesUtil.addErrorMessage("Nome está vazio.");
            return null;
        }
        
        if(usuario.getLogin().equals("")){
            FacesUtil.addErrorMessage("Login está vazio.");
            return null;
        }
        
        if(usuario.getSenha().equals("")){
            FacesUtil.addErrorMessage("Senha está vazio.");
            return null;
        }
        usuario.setSenha(Util.convertStringToMd5(usuario.getSenha()));
        try {
            usuarioDAO.save(usuario);
            return "login.xhtml?faces-redirect=true";
        } catch (Exception ex) {
            FacesUtil.addErrorMessage("Algo deu errado.");
            return null;
        }finally{
            usuario = new Usuario();
        }
    }
    
}
