package edu.olya.tour.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TourView implements Serializable {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TourView tourView = (TourView) o;

        if (adults != tourView.adults) return false;
        if (children != tourView.children) return false;
        if (id != tourView.id) return false;
        if (nights != tourView.nights) return false;
        if (country != null ? !country.equals(tourView.country) : tourView.country != null) return false;
        if (hotel != null ? !hotel.equals(tourView.hotel) : tourView.hotel != null) return false;
        if (mealType != null ? !mealType.equals(tourView.mealType) : tourView.mealType != null) return false;
        if (price != null ? !price.equals(tourView.price) : tourView.price != null) return false;
        if (priceFrom != null ? !priceFrom.equals(tourView.priceFrom) : tourView.priceFrom != null) return false;
        if (priceTo != null ? !priceTo.equals(tourView.priceTo) : tourView.priceTo != null) return false;
        if (startDate != null ? !startDate.equals(tourView.startDate) : tourView.startDate != null) return false;
        if (tourType != null ? !tourType.equals(tourView.tourType) : tourView.tourType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (tourType != null ? tourType.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + adults;
        result = 31 * result + children;
        result = 31 * result + nights;
        result = 31 * result + (hotel != null ? hotel.hashCode() : 0);
        result = 31 * result + (mealType != null ? mealType.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (priceFrom != null ? priceFrom.hashCode() : 0);
        result = 31 * result + (priceTo != null ? priceTo.hashCode() : 0);
        return result;
    }

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
