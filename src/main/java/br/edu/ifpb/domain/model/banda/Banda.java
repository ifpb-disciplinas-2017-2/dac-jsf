package br.edu.ifpb.domain.model.banda;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 27/11/2017, 13:39:19
 */
public class Banda {

    private String nomeFantasia;
    private List<Integrante> integrantes = new ArrayList<>();

    public Banda() {
    }
    public void novoIntegrante(Integrante integrante){
        this.integrantes.add(integrante);
    }
    
    public void removerIntegrante(Integrante integrante){
        this.integrantes.remove(integrante);
    }
    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public List<Integrante> getIntegrantes() {
        return integrantes;
    }

    public void setIntegrantes(List<Integrante> integrantes) {
        this.integrantes = integrantes;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.nomeFantasia);
        hash = 43 * hash + Objects.hashCode(this.integrantes);
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
        final Banda other = (Banda) obj;
        if (!Objects.equals(this.nomeFantasia, other.nomeFantasia)) {
            return false;
        }
        if (!Objects.equals(this.integrantes, other.integrantes)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Banda{" + "nome=" + nomeFantasia + ", integrantes=" + integrantes + '}';
    }

}