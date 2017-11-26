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

public class TourView {
    private int id;
    private String country;
    private String tourType;
    private Date startDate;
    private int adults;
    private int children;
    private int nights;
    private String hotel;
    private String mealType;
    private BigDecimal price;
    private BigDecimal priceFrom;
    private BigDecimal priceTo;

    public TourView() {
    }


    public TourView(int id, String country, String tourType,
                    Date startDate, int adults, int children,
                    int nights, String hotel, String mealType, BigDecimal price) {
        this.id = id;
        this.country = country;
        this.tourType = tourType;
        this.startDate = startDate;
        this.adults = adults;
        this.children = children;
        this.nights = nights;
        this.hotel = hotel;
        this.mealType = mealType;
        this.price = price;
    }

    public static TourView parse(Map<String, String[]> searchParameters) {
        TourView tourView = new TourView();

        for (Map.Entry<String, String[]> param : searchParameters.entrySet()) {
            String paramName = param.getKey();

            String paramValue = Arrays.toString( param.getValue());
            paramValue = paramValue.substring(1, paramValue.length() - 1);

            Field field = null;
            try {
                //field gets all information about field "paramName"
                field = TourView.class.getDeclaredField(paramName);
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
                        field.set(tourView, new SimpleDateFormat("yyyy-MM-dd").parse(paramValue)); //field == reference to the field
                    }
                    if (fieldType == BigDecimal.class && (paramValue.length() > 0)) {
                        field.set(tourView, new BigDecimal(paramValue));
                    }
                    // if there is an editor
                } else if(paramValue.length() > 0) {
                    pe.setAsText(paramValue); //Set the property value by parsing a given String
                    field.set(tourView, pe.getValue());
                }
            } catch (NumberFormatException | IllegalAccessException | ParseException e) {
                //throw new ValidationException("Invalid data type " + paramName + " = " + paramValue + ", but required " + fieldType.getCanonicalName());
            }
        }

        return tourView;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTourType() {
        return tourType;
    }

    public void setTourType(String tourType) {
        this.tourType = tourType;
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

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPriceFrom() {return priceFrom;}

    public void setPriceFrom(BigDecimal priceFrom) { this.priceFrom = priceFrom; }

    public BigDecimal getPriceTo() { return priceTo; }

    public void setPriceTo(BigDecimal priceTo) { this.priceTo = priceTo; }

    @Override
    public String toString() {
        return "TourView{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", tourType='" + tourType + '\'' +
                ", startDate=" + startDate +
                ", adults=" + adults +
                ", children=" + children +
                ", nights=" + nights +
                ", hotel='" + hotel + '\'' +
                ", mealType='" + mealType + '\'' +
                ", price=" + price +
                '}';
    }
}
