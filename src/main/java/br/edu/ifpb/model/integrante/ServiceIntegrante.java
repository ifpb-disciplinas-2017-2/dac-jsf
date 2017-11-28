package br.edu.ifpb.model.integrante;

import br.edu.ifpb.infra.persistence.memory.IntegrantesEmMemoria;
import java.util.List;

public class ServiceIntegrante {

    private final Integrantes integranteDAO = new IntegrantesEmMemoria();

    public boolean salvarIntegrante(Integrante i) {
        return integranteDAO.salvarIntegrante(i);
    }

    public List<Integrante> listarIntegrantes() {
        return integranteDAO.listarIntegrantes();
    }

    public void removerIntegrante(int id) {
       this.integranteDAO.excluirIntegrante(id);
    }
    
    public Integrante buscarIntegrante(int id){
        return integranteDAO.buscarIntegrante(id);
    }
            
    public boolean editarIntegrante(int id, Integrante i){
        return integranteDAO.editarIntegrante(id, i);
    }

}