package br.edu.ifpb.domain.model.album;

import br.edu.ifpb.domain.model.banda.Banda;
import java.time.LocalDate;
import java.util.Objects;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 20/11/2017, 09:37:05
 */
public class Album {

    private String descricao;
    private LocalDate dataDeLancamento;

    private Banda banda = new Banda();

    public Album() {
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataDeLancamento() {
        return dataDeLancamento;
    }

    public void setDataDeLancamento(LocalDate dataDeLancamento) {
        this.dataDeLancamento = dataDeLancamento;
    }

    public Banda getBanda() {
        return banda;
    }

    public void setBanda(Banda banda) {
        this.banda = banda;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.descricao);
        hash = 53 * hash + Objects.hashCode(this.dataDeLancamento);
        hash = 53 * hash + Objects.hashCode(this.banda);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Album other = (Album) obj;
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        if (!Objects.equals(this.dataDeLancamento, other.dataDeLancamento)) {
            return false;
        }
        if (!Objects.equals(this.banda, other.banda)) {
            return false;
        }
        return true;
    }

}
