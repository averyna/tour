package edu.olya.tour.model;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

public class Tour {
    private int id;
    private int countryId;
    private int tourTypeId;
    private Date startDate;
    private int adults;
    private int children;
    private int nights;
    private int hotelId;
    private int mealTypeId;
    private BigDecimal price;

    public Tour() {
    }

    public Tour( int countryId, int tourTypeId, Date startDate,
                int adults, int children, int nights, int hotelId,
                int mealTypeId, BigDecimal price) {
        this.countryId = countryId;
        this.tourTypeId = tourTypeId;
        this.startDate = startDate;
        this.adults = adults;
        this.children = children;
        this.nights = nights;
        this.hotelId = hotelId;
        this.mealTypeId = mealTypeId;
        this.price = price;
    }

    public static Tour parse(Map<String, String[]> parameterMap)
            throws NumberFormatException, IllegalAccessException, ParseException{

        Tour tour = new Tour();

        for (Map.Entry<String, String []> param : parameterMap.entrySet()) {
            String paramName = param.getKey();

            String paramValue = Arrays.toString( param.getValue());
            //System.out.println(paramName + " /" + paramValue + "/");
            paramValue = paramValue.substring(1, paramValue.length() - 1);

            Field field = null;
            try {
                //field gets all information about field "paramName"
                field = Tour.class.getDeclaredField(paramName);
                //System.out.println(field);
                //After gerDeclaredField()  field = private java.lang.String main.java.edu.olya.mytour.dao.SearchParam.meal_type
            } catch (NoSuchFieldException e) {
                //throw new Exception("Unknown argument name: " + paramName);
                //System.out.println("Unknown argument name: " + paramName); // for submit button
                continue;
            }
            Class fieldType = field.getType(); //fieldType =  public class java.io.File

      //      try {
                PropertyEditor pe = PropertyEditorManager.findEditor(fieldType); //Class fieldType = field.getType()
                if (pe == null) { //if editor can't be found
                    //If the specified object argument is an instance of the class or interface
                    //declaring the underlying field
                    if (fieldType == Date.class && paramValue.length() > 0) {
                        //@param obj - the object whose field should be modified
                        //@param value - the new value for the field
                        field.set(tour, new SimpleDateFormat("yyyy-MM-dd").parse(paramValue)); //field == reference to the field
                    }
                    if (fieldType == BigDecimal.class && (paramValue.length() > 0)) {
                        field.set(tour, new BigDecimal(paramValue));
                    }
                    // if there is an editor
                } else if(paramValue.length() > 0) {
                    pe.setAsText(paramValue); //Set the property value by parsing a given String
                    field.set(tour, pe.getValue());
                }
//            } catch (NumberFormatException | IllegalAccessException | ParseException e) {
//                //throw new ValidationException("Invalid data type " + paramName + " = " + paramValue + ", but required " + fieldType.getCanonicalName());
//            }
        }
        return tour;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public int getTourTypeId() {
        return tourTypeId;
    }

    public void setTourTypeId(int tourTypeId) {
        this.tourTypeId = tourTypeId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getAdults() {
        return adults;
    }

    public void setAdults(int adults) {
        this.adults = adults;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public int getNights() {
        return nights;
    }

    public void setNights(int nights) {
        this.nights = nights;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public int getMealTypeId() {
        return mealTypeId;
    }

    public void setMealTypeId(int mealTypeId) {
        this.mealTypeId = mealTypeId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Tour{" +
                "id=" + id +
                ", countryId=" + countryId +
                ", tourTypeId=" + tourTypeId +
                ", startDate=" + startDate +
                ", adults=" + adults +
                ", children=" + children +
                ", nights=" + nights +
                ", hotelId=" + hotelId +
                ", mealTypeId=" + mealTypeId +
                ", price=" + price +
                '}';
    }
}
