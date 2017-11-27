package br.edu.ifpb.web.jsf.convert;

import br.edu.ifpb.domain.model.banda.CPF;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 22/11/2017, 09:32:13
 */
@FacesConverter("converter.CpfConverter")
public class CPFConverter implements Converter {

    @Override
    public Object getAsObject(
            FacesContext context,
            UIComponent component,
            String value) {
        if (value == null) {
            return new CPF("");
        }
        return new CPF(value);
    }

    @Override
    public String getAsString(
            FacesContext context,
            UIComponent component,
            Object value) {
        if (value == null) {
            return "";
        }
        CPF cpf = (CPF) value;
        return cpf.value();
    }

}
