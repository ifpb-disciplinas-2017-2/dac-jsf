package br.edu.ifpb.domain.model.banda;

import java.util.List;

public interface Integrantes {

    void excluir(Integrante integranteParaExcluir);

    List<Integrante> listarTodos();

    boolean salvar(Integrante integrante);

    Integrante localizarPor(int id);

}
