package edu.olya.tour.model;

import java.math.BigDecimal;
import java.util.Date;

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

    public TourView() {
    }


    public TourView(int id, String country, String tourType, Date startDate, int adults, int children, int nights, String hotel, String mealType, BigDecimal price) {
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
