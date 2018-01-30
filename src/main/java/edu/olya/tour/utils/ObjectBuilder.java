package edu.olya.tour.utils;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

public class ObjectBuilder {
    public static <T> T parse(Map<String, String[]> parameterMap, T classObject) {

        for (Map.Entry<String, String []> param : parameterMap.entrySet()) {
            String paramName = param.getKey();

            String paramValue = Arrays.toString(param.getValue());
            paramValue = paramValue.substring(1, paramValue.length() - 1);

            Field field = null;
            try {
                field = classObject.getClass().getDeclaredField(paramName);
            } catch (NoSuchFieldException e) {
                //ok, let's proceed to the next parameter from the map
                continue;//will bring us to to the next loop of the cycle
            }
            field.setAccessible(true);
            Class fieldType = field.getType();

            PropertyEditor pe = PropertyEditorManager.findEditor(fieldType);
            try {
                if (pe == null) {
                    if (fieldType == Date.class && paramValue.length() > 0) {
                        try {
                            field.set(classObject, new SimpleDateFormat("yyyy-MM-dd").parse(paramValue));
                        }catch (ParseException e){
                            field.set(classObject, null);
                        }
                    }
                    if (fieldType == BigDecimal.class && (paramValue.length() > 0)) {
                        field.set(classObject, new BigDecimal(paramValue));
                    }
                } else if (paramValue.length() > 0) {
                    pe.setAsText(paramValue);
                    field.set(classObject, pe.getValue());
                }
            }catch (IllegalAccessException e) {
                throw new RuntimeException("field " + field.getName() + " is either inaccessible or final" + e);
            }
        }
        return classObject;
    }
}
