package br.edu.ifpb.domain.model.musica;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 27/11/2017, 16:56:27
 */
@Named
@RequestScoped
public class ControladorDeMusica {

    private Musica musica = new Musica();
    private ServiceMusica service = new ServiceMusica();
    
    public String add(){
        return null;
    }

    public List<Musica> musicas(){
        return service.listarTodos();
    }
    public Musica getMusica() {
        return musica;
    }

    public void setMusica(Musica musica) {
        this.musica = musica;
    }
    
    
    
}
