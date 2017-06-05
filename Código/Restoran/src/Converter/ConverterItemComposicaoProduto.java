package Converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import Basica.Categoria;
import Basica.ItemComposicaoProduto;
import Basica.Mesa;

//@FacesConverter(value = "classeConverter")    
@FacesConverter(forClass = ItemComposicaoProduto.class)
public class ConverterItemComposicaoProduto implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value != null && !value.isEmpty()) {
            return (ItemComposicaoProduto) uiComponent.getAttributes().get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value instanceof ItemComposicaoProduto) {
        	ItemComposicaoProduto entity= (ItemComposicaoProduto) value;
            if (entity != null && entity instanceof ItemComposicaoProduto && entity.getId() != null) {
                uiComponent.getAttributes().put( entity.getId().toString(), entity);
                return entity.getId().toString();
            }
        }
        return "";
    }
}