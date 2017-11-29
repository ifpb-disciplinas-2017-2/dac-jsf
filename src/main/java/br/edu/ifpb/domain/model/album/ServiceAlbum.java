package br.edu.ifpb.domain.model.album;

import br.edu.ifpb.domain.model.banda.Banda;
import br.edu.ifpb.domain.model.banda.Bandas;
import br.edu.ifpb.infra.persistence.jdbc.AlbunsEmJDBC;
import br.edu.ifpb.infra.persistence.jdbc.BandasEmJDBC;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 20/11/2017, 10:21:06
 */
public class ServiceAlbum {
    
//    private final Albuns dao = new AlbunsEmMemoria();
//    private final Bandas bandas = new BandasEmMemoria();
     private  Albuns dao = new AlbunsEmJDBC();
    private  Bandas bandas = new BandasEmJDBC();
    
    public boolean salvar(Album album){
        //validar os dados
        if(naoEhAlbumValido(album)){
            return false;
        }
       // fluxo para persistência     
        return dao.salvar(album);
    } 
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

    public List<Banda> todosAsBandas() {
        return this.bandas.listarTodos();
    }
}
