package br.edu.ifpb.web;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 21/11/2017, 08:37:19
 */
@Named //("cMusicas")  //cMusicas
@RequestScoped
public class ControladorDeMusicas { //controladorDeMusicas

    
    public String enviar(){
        
//        return "list.xthml";
        return "list";
    }
}
