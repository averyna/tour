package edu.olya.tour.model;

import java.io.Serializable;

public class MealType implements Serializable {
    private int id;
    private String name;

    public MealType(int id, String name) {

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

        MealType mealType = (MealType) o;

        if (id != mealType.id) return false;
        if (name != null ? !name.equals(mealType.name) : mealType.name != null) return false;

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
        return "MealType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
