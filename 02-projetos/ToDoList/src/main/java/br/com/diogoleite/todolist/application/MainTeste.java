/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.diogoleite.todolist.application;


import br.com.diogoleite.todolist.dao.TarefaDAO;
import br.com.diogoleite.todolist.dao.UsuarioDAO;
import br.com.diogoleite.todolist.model.Tarefa;
import br.com.diogoleite.todolist.model.Usuario;
import br.com.diogoleite.todolist.util.Util;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diogo Leite
 */
public class MainTeste {
    public static void main(String[] args){
//        TarefaDAO dao1 = new TarefaDAO();
//        
//        
//        try {
//            //dao1.save(new Usuario("Diogo", "diogo", Util.convertStringToMd5("123")));
//            //Usuario u = dao1.login("diogo", Util.convertStringToMd5("123"));
//            Tarefa t = dao1.findById("aaa");
//            System.out.println(t.getDescricao());
//            dao1.remove(t);
//            //System.out.println(u.getNome());
//        } catch (Exception ex) {
//            Logger.getLogger(MainTeste.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
        UsuarioDAO dao1 = new UsuarioDAO();
        
        
       try {
           //dao1.save(new Usuario("Diogo", "diogo", Util.convertStringToMd5("123")));            Usuario u = dao1.login("diogo", "123");
           Usuario u = dao1.findById(Long.valueOf(1));
           Tarefa t1 = new Tarefa();
           t1.setTitulo("aaa");
           for(Tarefa t: u.getTarefas()){
               if(t.equals(t1)){
                   System.out.println("oi");
                   u.removeTarefa(t);
                   //TarefaDAO dao = new TarefaDAO();
                   //dao.remove(t);
                   dao1.save(u);
                   return;
               }
           }
           
//            System.out.println(t.getDescricao());
//            System.out.println(u.getLogin());
           
           //System.out.println(u.getNome());
       } catch (Exception ex) {
          Logger.getLogger(MainTeste.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
}
