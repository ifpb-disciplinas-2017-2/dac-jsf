package br.edu.ifpb.web.jsf.convert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 21/11/2017, 08:53:05
 */
@FacesConverter(value = "convert.LocalDate", forClass = LocalDate.class)
public class LocalDateConvert implements Converter {

    DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public Object getAsObject(FacesContext context, UIComponent component,
            String value) {
        if (value == null) {
            return null;
        }

        return LocalDate.parse(value, ofPattern);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component,
            Object value) {
        if (value == null) {
            return null;
        }
        LocalDate date = (LocalDate) value;

        return date.format(ofPattern);//toString();
    }

}
