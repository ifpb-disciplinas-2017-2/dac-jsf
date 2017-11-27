package br.edu.ifpb.infra.persistence.memory;

import br.edu.ifpb.domain.model.album.Album;
import br.edu.ifpb.domain.model.album.Albuns;
import br.edu.ifpb.domain.model.banda.Banda;
import br.edu.ifpb.domain.model.banda.Integrante;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 20/11/2017, 10:27:39
 */
public class AlbunsEmMemoria implements Albuns {

    private static final List<Album> albuns = new CopyOnWriteArrayList<>();
    private static final List<Integrante> integrantes = Arrays
            .asList(new Integrante("Kiko"),
                    new Integrante("Chaves"));

    @Override
    public boolean salvar(Album album) {
        return albuns.add(album);
    }

    @Override
    public List<Album> listarTodos() {
        return Collections.unmodifiableList(albuns);
    }

    @Override
    public void excluir(Album albumParaExcluir) {
        albuns.remove(albumParaExcluir);
    }

    @Override
    public Album localizarPor(String descricao) {
        return albuns.stream()
                .filter(a -> a.getDescricao().equalsIgnoreCase(descricao))
                .findFirst()
                .get();
    }

//    @Override
//    public Integrante localizarIntegrantePor(String nome) {
//        return integrantes.stream()
//                .filter(a -> a.getNome().equalsIgnoreCase(nome))
//                .findFirst()
//                .get();
//    }
//
//    @Override
//    public List<Integrante> listarOsIntegrantes() {
//        return integrantes;
//    }
//
//    @Override
//    public List<Banda> listarAsBandas() {
//        
//    }
}
