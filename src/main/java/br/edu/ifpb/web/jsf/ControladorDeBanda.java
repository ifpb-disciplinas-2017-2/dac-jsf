package br.edu.ifpb.web.jsf;

import br.edu.ifpb.domain.model.banda.Banda;
import br.edu.ifpb.domain.model.banda.Integrante;
import br.edu.ifpb.domain.model.banda.ServiceBanda;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 27/11/2017, 14:34:32
 */
@Named
@SessionScoped
public class ControladorDeBanda implements Serializable {

    private Banda banda = new Banda();
    private Integrante integrante = new Integrante();
    private final ServiceBanda service = new ServiceBanda();

    public String salvar() {
        if (this.service.salvar(banda)) {
            banda = new Banda();
            return "bandas";
        }
        return null;
    }

    public String excluir(Banda albumParaExcluir) {
        this.service.excluirAlbum(albumParaExcluir);
        return null;
    }

    public String add() {
        banda.novoIntegrante(integrante); 
        return null;
    }

    public List<Banda> bandas() {
        return this.service.todosOsAlbuns();
    }

    public List<Integrante> integrantes() {
        return this.service.todosOsIntegrantes();
    }
    public List<Integrante> integrantesDaBanda() {
        return this.banda.getIntegrantes();
    }

    public Banda getBanda() {
        return banda;
    }

    public void setBanda(Banda banda) {
        this.banda = banda;
    }

    public Integrante getIntegrante() {
        return integrante;
    }

    public void setIntegrante(Integrante integrante) {
        this.integrante = integrante;
    }

}
