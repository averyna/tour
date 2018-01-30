package edu.olya.tour.model;

import java.io.Serializable;

public class TourType implements Serializable {
    private int id;
    private String name;

    public TourType(int id, String name) {

        this.id = id;
        this.name = name;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TourType tourType = (TourType) o;

        if (id != tourType.id) return false;
        if (name != null ? !name.equals(tourType.name) : tourType.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TourType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
