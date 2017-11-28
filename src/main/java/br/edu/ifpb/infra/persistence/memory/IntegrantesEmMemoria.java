package br.edu.ifpb.infra.persistence.memory;

import br.edu.ifpb.model.integrante.Integrante;
import br.edu.ifpb.model.integrante.Integrantes;
import java.util.ArrayList;
import java.util.List;

public class IntegrantesEmMemoria implements Integrantes {

    public static List<Integrante> integrantes = new ArrayList<>();

    @Override
    public boolean salvarIntegrante(Integrante i) {
        return integrantes.add(i);
    }

    @Override
    public List<Integrante> listarIntegrantes() {
        return integrantes;
    }

    @Override
    public void excluirIntegrante(int id) {
        this.integrantes.remove(id);
    }

    @Override
    public Integrante buscarIntegrante(int id) {
        return integrantes.get(id);
    }

    @Override
    public boolean editarIntegrante(int id, Integrante i) {
        this.integrantes.remove(id);
        return integrantes.add(i);
    }

}
