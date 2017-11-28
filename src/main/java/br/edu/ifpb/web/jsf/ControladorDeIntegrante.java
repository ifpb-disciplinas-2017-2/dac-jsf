
package br.edu.ifpb.web.jsf;

import br.edu.ifpb.model.integrante.Integrante;
import br.edu.ifpb.model.integrante.ServiceIntegrante;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class ControladorDeIntegrante {
    
    private Integrante integrante = new Integrante();
    private final ServiceIntegrante serviceIntegrante = new ServiceIntegrante();
    
    public String salvarIntegrante(){
        if(serviceIntegrante.salvarIntegrante(integrante)){
            return "cadastroBanda.xhtml";
        }
        return null;
    }
    
    public List<Integrante> listarIntegrantes(){
        return serviceIntegrante.listarIntegrantes();
    }
    
    public String excluirIntegrante(int id){
        serviceIntegrante.removerIntegrante(id);
        return null;
    }
    
     public Integrante buscarIntegrante(int id) {
        return serviceIntegrante.buscarIntegrante(id);
    }

    
    public boolean editarIntegrante(int id, Integrante i) {
        serviceIntegrante.removerIntegrante(id);
        return serviceIntegrante.salvarIntegrante(i);
    }
    
    public Integrante getIntegrante() {
        return integrante;
    }

    public void setIntegrante(Integrante integrante) {
        this.integrante = integrante;
    }
    
}