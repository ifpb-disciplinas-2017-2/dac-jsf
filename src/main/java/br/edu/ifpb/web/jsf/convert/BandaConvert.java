package br.edu.ifpb.web.jsf.convert;

import br.edu.ifpb.domain.model.album.Album;
import br.edu.ifpb.domain.model.banda.Banda;
import br.edu.ifpb.domain.model.banda.Bandas;
import br.edu.ifpb.infra.persistence.memory.BandasEmMemoria;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 26/11/2017, 18:53:05
 */
@FacesConverter(value = "convert.Banda", forClass = Banda.class)
public class BandaConvert implements Converter {

    private final Bandas albuns = new BandasEmMemoria();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component,
            String value) {
        if (value == null) {
            return null;
        }
        return albuns.localizarPor(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component,
            Object value) {
        if (value == null) {
            return null;
        }
        Banda album = (Banda) value;
        
        return album.getNomeFantasia();
    }

}
