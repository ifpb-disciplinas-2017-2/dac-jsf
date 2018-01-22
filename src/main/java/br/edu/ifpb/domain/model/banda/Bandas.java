package br.edu.ifpb.domain.model.banda;

import java.util.List;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 20/11/2017, 11:09:53
 */
public interface Bandas {

    void excluir(Banda bandaParaExcluir);

    List<Banda> listarTodos();

    boolean salvar(Banda album);

    Banda localizarPor(String descricao);
     Banda localizarPor(int id);

    public List<Integrante> listarOsIntegrantes(int id_banda);

    public Integrante localizarIntegrantePor(String nome);

}
