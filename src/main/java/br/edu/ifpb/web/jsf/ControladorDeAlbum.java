package br.edu.ifpb.web.jsf;

import br.edu.ifpb.domain.model.album.ServiceAlbum;
import br.edu.ifpb.domain.model.album.Album;
import br.edu.ifpb.domain.model.banda.Banda;
import java.util.List;
import javax.enterprise.context.RequestScoped; 
import javax.inject.Named;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 20/11/2017, 09:11:44
 */
@Named
@RequestScoped
public class ControladorDeAlbum {

    private Album album = new Album();
    private ServiceAlbum service = new ServiceAlbum();

    public String salvar() {
        if (this.service.salvar(album)) {
            return "albuns.xhtml";
        }
        return null;
    }

    public String excluir(Album albumParaExcluir) {
        this.service.excluirAlbum(albumParaExcluir);
        return null;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
    
    public List<Album> albuns() {
        return this.service.todosOsAlbuns();

    }

    public List<Banda> bandas() {
        return this.service.todosAsBandas();

    }
}
