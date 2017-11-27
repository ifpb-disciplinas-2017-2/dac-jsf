package br.edu.ifpb.domain.model.album;

import br.edu.ifpb.domain.model.banda.Integrante;
import java.time.LocalDate;
import java.util.Objects;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 20/11/2017, 09:37:05
 */
public class Album {

    private String descricao;
//    private String dataDeLancamento;
    private LocalDate dataDeLancamento;
    private Integrante integrante = new Integrante();

    public Album() {
    }

//    public Album(String descricao, String dataDeLancamento) {
//        this.descricao = descricao;
//        this.dataDeLancamento = dataDeLancamento;
//    }
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

//    public String getDataDeLancamento() {
//        return dataDeLancamento;
//    }
//
//    public void setDataDeLancamento(String dataDeLancamento) {
//        this.dataDeLancamento = dataDeLancamento;
//    }
    public LocalDate getDataDeLancamento() {
        return dataDeLancamento;
    }

    public void setDataDeLancamento(LocalDate dataDeLancamento) {
        this.dataDeLancamento = dataDeLancamento;
    }

    public Integrante getIntegrante() {
        return integrante;
    }

    public void setIntegrante(Integrante integrante) {
        this.integrante = integrante;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.descricao);
        hash = 53 * hash + Objects.hashCode(this.dataDeLancamento);
        hash = 53 * hash + Objects.hashCode(this.integrante);
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
        if (!Objects.equals(this.integrante, other.integrante)) {
            return false;
        }
        return true;
    }

    
    

}
