package org.bduelo.controller.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.bduelo.model.Time;
import org.bduelo.persistence.DaoGenerica;
import org.bduelo.persistence.TimeDao;

/**
 *
 * @author Caio
 */

@FacesConverter("timeConverter")
public class TimeConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String valor) {
        
        DaoGenerica<Time, Integer> dao = new TimeDao();
        return dao.select( Integer.parseInt(valor) );
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object valor) {
        
        Time time = null;
        
        if (valor instanceof Time) {
            
            time = (Time) valor;
            return Integer.toString( time.getIdTime() );
        }
        
        return "";
    }
    
}
