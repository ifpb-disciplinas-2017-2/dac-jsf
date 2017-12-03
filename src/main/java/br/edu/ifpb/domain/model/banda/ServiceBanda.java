package br.edu.ifpb.domain.model.banda;

import br.edu.ifpb.infra.persistence.jdbc.BandasEmJDBC;
import br.edu.ifpb.infra.persistence.memory.BandasEmMemoria;
import java.util.List;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 20/11/2017, 10:21:06
 */
public class ServiceBanda {

    // ; private final Bandas dao = new BandasEmMemoria();
    private Bandas dao = new BandasEmJDBC();

    public boolean salvar(Banda album) {
        return dao.salvar(album);
    }

    public List<Banda> todosOsAlbuns() {
        return dao.listarTodos();
    }

    public void excluirAlbum(Banda albumParaExcluir) {
        dao.excluir(albumParaExcluir);
    }

    public List<Integrante> todosOsIntegrantes(int id) {
        return this.dao.listarOsIntegrantes(id);
    }

    public Banda localizarPor(String nome) {
        return dao.localizarPor(nome);
    }

    public Integrante localizarIntegrantePor(String nome) {
        return dao.localizarIntegrantePor(nome);
    }

    public Banda localizarPor(int id) {
     return dao.localizarPor(id);
    }
}
