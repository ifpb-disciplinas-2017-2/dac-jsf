package br.edu.ifpb.domain.model.banda;

import br.edu.ifpb.infra.persistence.memory.BandasEmMemoria;
import java.util.List;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 20/11/2017, 10:21:06
 */
public class ServiceBanda {
    
    private final Bandas dao = new BandasEmMemoria();
    
    public boolean salvar(Banda album){
        return dao.salvar(album);
    }
 
    public List<Banda> todosOsAlbuns(){
        return dao.listarTodos();
    }

    public void excluirAlbum(Banda albumParaExcluir) {
        dao.excluir(albumParaExcluir);
    }

    public List<Integrante> todosOsIntegrantes() {
        return this.dao.listarOsIntegrantes();
    }
}
