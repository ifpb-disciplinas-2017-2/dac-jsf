package br.edu.ifpb.infra.persistence.memory;

import br.edu.ifpb.domain.model.banda.Banda;
import br.edu.ifpb.domain.model.banda.Bandas;
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
public class BandasEmMemoria implements Bandas {

    private static final List<Banda> albuns = new CopyOnWriteArrayList<>();
    private static final List<Integrante> integrantes = Arrays
            .asList(new Integrante("Kiko"),
                    new Integrante("Chaves"));

    @Override
    public boolean salvar(Banda album) {
        return albuns.add(album);
    }

    @Override
    public List<Banda> listarTodos() {
        return Collections.unmodifiableList(albuns);
    }

    @Override
    public void excluir(Banda albumParaExcluir) {
        albuns.remove(albumParaExcluir);
    }

    @Override
    public Banda localizarPor(String descricao) {
        return albuns.stream()
                .filter(a -> a.getNomeFantasia().equalsIgnoreCase(descricao))
                .findFirst()
                .get();
    }

    @Override
    public Integrante localizarIntegrantePor(String nome) {
        return integrantes.stream()
                .filter(a -> a.getNome().equalsIgnoreCase(nome))
                .findFirst()
                .get();
    }

    @Override
    public List<Integrante> listarOsIntegrantes(int id) {
        return integrantes;
    }
}
