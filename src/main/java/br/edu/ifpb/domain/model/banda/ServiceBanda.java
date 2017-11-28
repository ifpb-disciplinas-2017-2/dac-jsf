package br.edu.ifpb.domain.model.banda;

import br.edu.ifpb.infra.persistence.jdbc.BandasEmJDBD;
import java.util.List;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 20/11/2017, 10:21:06
 */
public class ServiceBanda {

    private final Bandas dao = new BandasEmJDBD();

    public boolean salvar(Banda album) {
        return dao.salvar(album);
    }

    public List<Banda> todosOsAlbuns() {
        return dao.listarTodas();
    }

    public void excluirAlbum(Banda albumParaExcluir) {
        dao.excluir(albumParaExcluir);
    }

    public List<Integrante> listarOsIntegrantes(){
        return dao.listarOsIntegrantes();
    }

    public Integrante localizarIntegrantePor(int id){
        return dao.localizarIntegrantePor(id);
    }
}
