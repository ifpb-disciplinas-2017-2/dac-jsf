
package br.edu.ifpb.model.integrante;

public class CPF {
    
    private String numero;
    
    public CPF(){
        
    }

    public CPF(String numero) {
        this.numero = numero;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
    
}