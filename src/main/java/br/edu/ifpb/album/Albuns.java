package br.edu.ifpb.album;

import java.util.List;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 20/11/2017, 11:09:53
 */
public interface Albuns {

    void excluir(Album albumParaExcluir);

    List<Album> listarTodos();

    boolean salvar(Album album);
    
}
