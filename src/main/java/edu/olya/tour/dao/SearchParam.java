package edu.olya.tour.dao;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

public class SearchParam {
    private String country;
    private String tour_type;
    private Date start_date;
    private int adults;
    private int children;
    private int nights;
    private String meal_type;
    private String hotel;
    private int stars;
    private BigDecimal price;
    private BigDecimal price_from;
    private BigDecimal price_to;

    public static SearchParam parse(Map<String, Object> searchParameters) {
        SearchParam searchParam = new SearchParam();

        for (Map.Entry<String, Object> param : searchParameters.entrySet()) {
            String paramName = param.getKey();

            String paramValue = Arrays.toString((String[]) param.getValue());
            //System.out.println(paramName + " /" + paramValue + "/");
            paramValue = paramValue.substring(1, paramValue.length() - 1);


            Field field = null;
            try {
                //field gets all information about field "paramName"
                field = SearchParam.class.getDeclaredField(paramName);
                //System.out.println(field);
                //After gerDeclaredField()  field = private java.lang.String main.java.edu.olya.mytour.dao.SearchParam.meal_type
            } catch (NoSuchFieldException e) {
                //throw new Exception("Unknown argument name: " + paramName);
                //System.out.println("Unknown argument name: " + paramName); // for submit button
                continue;
            }
            Class fieldType = field.getType(); //fieldType =  public class java.io.File

            try {
                PropertyEditor pe = PropertyEditorManager.findEditor(fieldType); //Class fieldType = field.getType()
                if (pe == null) { //if editor can't be found
                    //If the specified object argument is an instance of the class or interface
                    //declaring the underlying field
                    if (fieldType == Date.class && paramValue.length() > 0) {
                        //@param obj - the object whose field should be modified
                        //@param value - the new value for the field
                        field.set(searchParam, new SimpleDateFormat("yyyy-MM-dd").parse(paramValue)); //field == reference to the field
                    }
                    if (fieldType == BigDecimal.class && (paramValue.length() > 0)) {
                        field.set(searchParam, new BigDecimal(paramValue));
                    }
                    // if there is an editor
                } else if(paramValue.length() > 0) {
                        pe.setAsText(paramValue); //Set the property value by parsing a given String
                        field.set(searchParam, pe.getValue());
                }
            } catch (NumberFormatException | IllegalAccessException | ParseException e) {
                //throw new ValidationException("Invalid data type " + paramName + " = " + paramValue + ", but required " + fieldType.getCanonicalName());
            }
        }

        return searchParam;
    }

    public String getCountry() {
        return country;
    }

    public String getTour_type() {
        return tour_type;
    }

    public Date getStart_date() {
        return start_date;
    }

    public int getAdults() {
        return adults;
    }

    public int getChildren() {
        return children;
    }

    public int getNights() {
        return nights;
    }

    public String getMeal_type() {
        return meal_type;
    }

    public String getHotel() { return hotel; }

    public int getStars() {
        return stars;
    }

    public BigDecimal getPrice() { return price; }

    public BigDecimal getPrice_from() {
        return price_from;
    }

    public BigDecimal getPrice_to() {
        return price_to;
    }

    @Override
    public String toString() {
        return "SearchParam{" +
                "country='" + country + '\'' +
                ", tour_type='" + tour_type + '\'' +
                ", start_date=" + start_date +
                ", adults=" + adults +
                ", children=" + children +
                ", nights=" + nights +
                ", meal_type='" + meal_type + '\'' +
                ", hotel='" + hotel + '\'' +
                ", stars=" + stars +
                ", price=" + price +
                ", price_from=" + price_from +
                ", price_to=" + price_to +
                '}';
    }
}
