package br.edu.ifpb.web.jsf;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 20/11/2017, 11:18:42
 */
@Named
@SessionScoped
public class ControladorDeLogin implements Serializable {

    private String usuario;
    private String senha;

    public String logar() {

        if (usuarioValido()) {
            return "cadastro.xhtml";
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("senha", new FacesMessage("usuario/senha não confere"));
        }
        return null;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    private boolean usuarioValido() {
        return "jose".equalsIgnoreCase(usuario) && "123".equalsIgnoreCase(senha);
    }

}
