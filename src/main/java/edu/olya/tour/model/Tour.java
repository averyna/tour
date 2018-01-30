package edu.olya.tour.model;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

public class Tour implements Serializable {
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

    public Tour() {}

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tour tour = (Tour) o;

        if (adults != tour.adults) return false;
        if (children != tour.children) return false;
        if (countryId != tour.countryId) return false;
        if (hotelId != tour.hotelId) return false;
        if (id != tour.id) return false;
        if (mealTypeId != tour.mealTypeId) return false;
        if (nights != tour.nights) return false;
        if (tourTypeId != tour.tourTypeId) return false;
        if (price != null ? !price.equals(tour.price) : tour.price != null) return false;
        if (startDate != null ? !startDate.equals(tour.startDate) : tour.startDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + countryId;
        result = 31 * result + tourTypeId;
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + adults;
        result = 31 * result + children;
        result = 31 * result + nights;
        result = 31 * result + hotelId;
        result = 31 * result + mealTypeId;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
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
