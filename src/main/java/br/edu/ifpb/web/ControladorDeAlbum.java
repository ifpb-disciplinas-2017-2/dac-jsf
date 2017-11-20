package br.edu.ifpb.web;

import br.edu.ifpb.domain.Album;
import java.util.Arrays;
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

//    private String descricao;
    private Album album = new Album();

    public String salvar() {
//        return null;
        return "home.xhtml";
    }

    public String nome() {
        return "Kiko";
    }
    

//    public String getDescricao() {
//        return descricao;
//    }
//
//    public void setDescricao(String descricao) {
//        this.descricao = descricao;
//    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
    
    public List<Album> albuns(){
        return Arrays.asList(new Album("RC", "12/10/1967"),
                new Album("Amado Batista", "12/10/1987"));
                
    }
}
