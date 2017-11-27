package org.bduelo.controller.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.bduelo.model.Torneio;
import org.bduelo.persistence.DaoGenerica;
import org.bduelo.persistence.TorneioDao;

/**
 *
 * @author Caio
 */
@FacesConverter("torneioConverter")
public class TorneioConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String valor) {
        
        DaoGenerica<Torneio, Integer> dao = new TorneioDao();
        return dao.select( Integer.parseInt(valor) );
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object valor) {
        
        Torneio torneio = null;
        
        if (valor instanceof Torneio) {
            
            torneio = (Torneio) valor;
            return Integer.toString( torneio.getIdTorneio() );
        }
        
        return "";
    }
    
}
