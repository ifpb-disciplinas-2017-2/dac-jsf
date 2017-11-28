
package br.edu.ifpb.model.integrante;

import java.util.List;

public interface Integrantes {
    
    public boolean salvarIntegrante(Integrante i);
    public List<Integrante> listarIntegrantes();
    public void excluirIntegrante(int id);
    public Integrante buscarIntegrante(int id);
    public boolean editarIntegrante(int id, Integrante i);
}
