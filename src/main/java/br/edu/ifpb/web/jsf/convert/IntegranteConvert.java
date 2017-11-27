package br.edu.ifpb.web.jsf.convert;

import br.edu.ifpb.domain.model.album.Albuns;
import br.edu.ifpb.domain.model.banda.Integrante;
import br.edu.ifpb.infra.persistence.memory.AlbunsEmMemoria;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 26/11/2017, 18:53:05
 */
@FacesConverter(value = "convert.Integrante", forClass = Integrante.class)
public class IntegranteConvert implements Converter {

    private final Albuns albuns = new AlbunsEmMemoria();
    @Override
    public Object getAsObject(FacesContext context, UIComponent component,
            String value) {
        if (value == null) {
            return null;
        }
        return albuns.localizarIntegrantePor(value); 
        
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component,
            Object value) {
        if (value == null) {
            return null;
        }
        Integrante integrante = (Integrante) value;
        return integrante.getNome();
    }

}
