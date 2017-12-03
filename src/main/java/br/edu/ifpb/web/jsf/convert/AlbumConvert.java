package br.edu.ifpb.web.jsf.convert;

import br.edu.ifpb.domain.model.album.Album;
import br.edu.ifpb.domain.model.album.Albuns;
import br.edu.ifpb.domain.model.album.ServiceAlbum;
import br.edu.ifpb.infra.persistence.jdbc.AlbunsEmJDBC;
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
@FacesConverter(value = "convert.Album", forClass = Album.class)
public class AlbumConvert implements Converter {

  //  private final Albuns albuns = new AlbunsEmMemoria();
     private final ServiceAlbum albuns = new ServiceAlbum();

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
        Album album = (Album) value;
        
        return album.getDescricao();
    }

}
