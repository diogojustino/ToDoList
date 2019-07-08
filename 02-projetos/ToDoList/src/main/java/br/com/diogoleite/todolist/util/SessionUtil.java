/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.diogoleite.todolist.util;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Diogo Leite
 */
public class SessionUtil {
    private static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
       
    }

    public static void setParam(String key, Object value) {
        getSession().setAttribute(key, value);
    }

    public static Object getParam(String key) {
        return getSession().getAttribute(key);
    }

    public static void remove(String key) {
        getSession().removeAttribute(key);
    }

    public static void invalidate() {
        getSession().invalidate();
    }
}
