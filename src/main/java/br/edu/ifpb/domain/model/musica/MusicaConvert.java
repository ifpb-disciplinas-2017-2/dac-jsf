package br.edu.ifpb.domain.model.musica;

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
@FacesConverter(value = "convert.Musica")
public class MusicaConvert implements Converter {

    private final ServiceMusica albuns = new ServiceMusica();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component,
            String value) {
        if (value == null) {
            return null;
        }
        int id = Integer.parseInt(value);
        return albuns.localizarPor(id);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component,
            Object value) {
        if (value == null) {
            return null;
        }
        Musica album = (Musica) value;
        return String.valueOf(album.getId());
    }

}
