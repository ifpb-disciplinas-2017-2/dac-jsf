package br.edu.ifpb.domain.model.banda;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 22/11/2017, 09:30:13
 */
public class CPF {

    private final String value; // sem pontos, apenas digitos

    public CPF() {
        this.value = null;
    }

    public CPF(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public String formatted() {
        if (notNullOrEmpty()) {
            return toFormat();
        }
        return this.value;
    }

    private String toFormat() {
        return this.value.substring(0, 3) + "."
                + this.value.substring(3, 6) + "."
                + this.value.substring(6, 9) + "-"
                + this.value.substring(9, 11);
    }

    private boolean notNullOrEmpty() {
        return this.value != null && this.value.length() == 11;
    }

    public boolean isValid() {
        return this.value.length() == 11;
    }

}
