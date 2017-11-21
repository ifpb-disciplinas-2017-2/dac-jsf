package br.edu.ifpb.domain.model.album;

import br.edu.ifpb.infra.AlbunsEmMemoria;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 20/11/2017, 10:21:06
 */
public class ServiceAlbum {
    
    private final Albuns dao = new AlbunsEmMemoria();
    
    public boolean salvar(Album album){
        //validar os dados
        if(naoEhAlbumValido(album)){
            return false;
        }
       // fluxo para persistência     
        return dao.salvar(album);
    }

//    private static boolean naoEhAlbumValido(Album album) {
//        return album.getDataDeLancamento()==null || 
//                "".equalsIgnoreCase(album.getDataDeLancamento().trim());
//    }
    private static boolean naoEhAlbumValido(Album album) {
        return album.getDataDeLancamento()==null || 
               LocalDate.now().isBefore(album.getDataDeLancamento());
    }
    
    public List<Album> todosOsAlbuns(){
        return dao.listarTodos();
    }

    public void excluirAlbum(Album albumParaExcluir) {
        dao.excluir(albumParaExcluir);
    }
}
