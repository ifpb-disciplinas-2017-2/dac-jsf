package br.edu.ifpb.web;

import br.edu.ifpb.album.ServiceAlbum;
import br.edu.ifpb.album.Album;
import java.util.List;
import javax.enterprise.context.RequestScoped; //CDI
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.RequestScoped; //JSF
import javax.inject.Named;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 20/11/2017, 09:11:44
 */
@Named
@RequestScoped
//@ManagedBean
//@RequestScoped
public class ControladorDeAlbum {

    private Album album = new Album();

    private ServiceAlbum service = new ServiceAlbum();

    public String salvar() {
        if (this.service.salvar(album)) {
            return "listar.xhtml";
        }
        return null;
    }
    public String excluir(Album albumParaExcluir){
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
}
