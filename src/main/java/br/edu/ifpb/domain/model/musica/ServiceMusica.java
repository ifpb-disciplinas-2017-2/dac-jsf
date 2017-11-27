package br.edu.ifpb.domain.model.musica;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 27/11/2017, 16:57:52
 */
public class ServiceMusica {

    private static final List<Musica> musicas
            = Arrays.asList(new Musica(1, "A"),
                    new Musica(2, "B"));

    public List<Musica> listarTodos() {
        return Collections.unmodifiableList(musicas);
    }

    public Musica localizarPor(int id) {
        return musicas.stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .get();
    }
}
